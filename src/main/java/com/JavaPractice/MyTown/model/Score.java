package com.JavaPractice.MyTown.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Score {

    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_id_applicant")
    private Applicant applicant;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_id_discipline")
    private Discipline discipline;

    @NonNull
    @Column(columnDefinition = "DATE")
    private LocalDate date;

    @NonNull
    private int mark;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
