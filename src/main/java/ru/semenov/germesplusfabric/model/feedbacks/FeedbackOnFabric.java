package ru.semenov.germesplusfabric.model.feedbacks;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.semenov.germesplusfabric.model.Fabric;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class FeedbackOnFabric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fabric_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Fabric fabric;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String text;

    private Double grade;

    private LocalDate date;

}
