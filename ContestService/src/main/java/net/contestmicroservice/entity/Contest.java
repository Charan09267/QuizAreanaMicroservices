package net.contestmicroservice.entity;


import jakarta.persistence.*;
import lombok.*;
import net.contestmicroservice.entity.enums.ContestStatus;
import net.contestmicroservice.entity.enums.ContestType;
import net.contestmicroservice.entity.enums.Visibility;


import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name="contests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contest {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;


    @Column(columnDefinition = "TEXT")
    private String description;


    // From auth-service
    private Long createdBy;


    private Visibility visibility;


    private LocalDateTime startTime;


    private LocalDateTime endTime;


    private Integer durationSeconds;


    private Integer maxParticipants;


    private ContestType contestType;


    private ContestStatus status;


    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;



    @OneToMany(
            mappedBy = "contest",
            cascade = CascadeType.ALL
    )
    private List<ContestQuestion> questions;



    @OneToMany(
            mappedBy="contest",
            cascade=CascadeType.ALL
    )
    private List<ContestParticipant> participants;

}