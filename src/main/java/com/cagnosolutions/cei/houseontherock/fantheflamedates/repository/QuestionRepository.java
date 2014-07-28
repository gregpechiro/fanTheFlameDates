package com.cagnosolutions.cei.houseontherock.fantheflamedates.repository;
/**
 * Created by greg on 7/3/14.
 */

import com.cagnosolutions.cei.houseontherock.fantheflamedates.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

	@Query("SELECT q FROM Question q WHERE q.videoId=:videoId")
	public List<Question> findByVideoId(@Param("videoId") String videoId);
}