package com.JavaPractice.MyTown.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private int places;

   @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_id_institute")
    private Institute institute;
}
