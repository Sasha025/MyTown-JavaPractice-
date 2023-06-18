package com.JavaPractice.MyTown.repos;

import com.JavaPractice.MyTown.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepos extends JpaRepository<Student, Long> {
}
