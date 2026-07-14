package net.company.apigateway.security;



import net.company.common.JwtUtility.JwtUtil;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;



@Component
//@RequiredArgsConstructor
public class JwtAuthenticationFilter
        implements GlobalFilter, Ordered {


    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        System.out.println("JWT FILTER CREATED");
        this.jwtUtil = jwtUtil;
    }

    private final JwtUtil jwtUtil;

    private static final String[] PUBLIC_PATHS = {
            "/auth/login",
            "/auth/register"
    };


    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            GatewayFilterChain chain
    ) {

        System.out.println("JWT FILTER CALLED: "
                + exchange.getRequest().getURI().getPath());
        String path =
                exchange.getRequest()
                        .getURI()
                        .getPath();

        // Allow public APIs
        for(String publicPath : PUBLIC_PATHS){
            if(path.startsWith(publicPath)){
                return chain.filter(exchange);
            }
        }

        String authHeader =
                exchange.getRequest()
                        .getHeaders()
                        .getFirst(
                                HttpHeaders.AUTHORIZATION
                        );

        if(authHeader == null ||
                !authHeader.startsWith("Bearer ")){

            return unauthorized(exchange);

        }

        String token = authHeader.substring(7);
        try {
            if(!jwtUtil.validateToken(token)){
                return unauthorized(exchange);
            }
            String email =
                    jwtUtil.extractEmail(token);
            Long userId =
                    jwtUtil.extractUserId(token);
            String role =
                    jwtUtil.extractRole(token);

            ServerWebExchange modifiedExchange =
                    exchange.mutate()
                            .request(
                                    request -> request
                                            .header(
                                                    "X-USER-ID",
                                                    String.valueOf(userId)
                                            )
                                            .header(
                                                    "X-USER-EMAIL",
                                                    email
                                            )
                                            .header(
                                                    "X-USER-ROLE",
                                                    role
                                            )
                            )
                            .build();
            return chain.filter(modifiedExchange);
        }
        catch(Exception e){
            return unauthorized(exchange);
        }
    }



    private Mono<Void> unauthorized(
            ServerWebExchange exchange
    ){
        exchange.getResponse()
                .setStatusCode(
                        HttpStatus.UNAUTHORIZED
                );
        return exchange.getResponse()
                .setComplete();

    }

    @Override
    public int getOrder(){
        return -1;
    }

}
