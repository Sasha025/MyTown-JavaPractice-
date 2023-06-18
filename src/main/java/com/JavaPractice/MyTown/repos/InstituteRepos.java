package com.JavaPractice.MyTown.repos;

import com.JavaPractice.MyTown.model.Institute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituteRepos extends JpaRepository<Institute, Long> {
}
