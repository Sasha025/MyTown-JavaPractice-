package com.JavaPractice.MyTown.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(columnDefinition = "DATE")
    private LocalDate date;

    @NonNull
    private String status;

    @NonNull
    private String type;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_id_applicant")
    private Applicant applicant;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_id_institute")
    private Institute institute;
}
