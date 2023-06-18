package com.JavaPractice.MyTown.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    @Column(columnDefinition = "DATE")
    private LocalDate date;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_id_specialty")
    private Specialty specialty;

    @NonNull
    private boolean gold_medal;
}
