package net.contestmicroservice.entity;

import jakarta.persistence.*;
import lombok.*;
import net.contestmicroservice.entity.enums.ParticipantStatus;

import java.time.LocalDateTime;

@Entity
@Table(
        name="contest_participants",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"contest_id","user_id"}
                )
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContestParticipant {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name="contest_id")
    private Contest contest;


    // auth-service user id
    private Long userId;


    private LocalDateTime joinedAt;


    private ParticipantStatus status;

}
