package com.questionservice.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.questionservice.entites.QusestionEntity;
@Repository
public interface QuestionRepo extends JpaRepository<QusestionEntity, Long> {

	 List<QusestionEntity> findByQuizId(Long quizId);
}
