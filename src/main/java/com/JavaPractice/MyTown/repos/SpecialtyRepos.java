package com.JavaPractice.MyTown.repos;

import com.JavaPractice.MyTown.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepos extends JpaRepository<Specialty, Long> {

}
