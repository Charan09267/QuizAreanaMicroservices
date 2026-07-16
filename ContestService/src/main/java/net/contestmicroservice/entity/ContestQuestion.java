package net.contestmicroservice.entity;

import jakarta.persistence.*;
import lombok.*;
import net.contestmicroservice.entity.enums.QuestionType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="contest_questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContestQuestion {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name="contest_id")
    private Contest contest;



    @Column(columnDefinition="TEXT")
    private String questionText;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;


    private Integer marks;


    @Column(columnDefinition="TEXT")
    private String explanation;


    private Integer orderIndex;


    @Builder.Default
    @OneToMany(
            mappedBy="question",
            cascade=CascadeType.ALL
    )
    private List<ContestOption> options = new ArrayList<>();

}
