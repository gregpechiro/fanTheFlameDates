package com.cagnosolutions.cei.houseontherock.fantheflamedates.repository;
/**
 * Created by greg on 7/3/14.
 */

import com.cagnosolutions.cei.houseontherock.fantheflamedates.domain.Worksheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorksheetRepository extends JpaRepository<Worksheet, Long> {

	@Query("SELECT w FROM Worksheet w WHERE w.videoId=:videoId")
	public List<Worksheet> findByVideoId(@Param("videoId") String videoId);
}