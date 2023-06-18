package com.JavaPractice.MyTown.repos;


import com.JavaPractice.MyTown.model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRepos extends JpaRepository<Discipline, Long> {
}
