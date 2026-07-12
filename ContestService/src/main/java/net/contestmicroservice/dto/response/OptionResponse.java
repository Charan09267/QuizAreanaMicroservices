package net.contestmicroservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OptionResponse {
    private Long id;

    private String optionText;
}
