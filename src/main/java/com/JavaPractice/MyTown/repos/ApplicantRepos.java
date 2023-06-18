package com.JavaPractice.MyTown.repos;

import com.JavaPractice.MyTown.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepos extends JpaRepository<Applicant, Long> {
}
