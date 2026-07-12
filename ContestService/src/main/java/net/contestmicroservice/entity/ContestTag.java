package net.contestmicroservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="contest_tags")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContestTag {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne
    @JoinColumn(name="contest_id")
    private Contest contest;



    @ManyToOne
    @JoinColumn(name="tag_id")
    private Tag tag;

}