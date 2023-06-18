package com.JavaPractice.MyTown.repos;

import com.JavaPractice.MyTown.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepos extends JpaRepository<Score, Long> {
}
