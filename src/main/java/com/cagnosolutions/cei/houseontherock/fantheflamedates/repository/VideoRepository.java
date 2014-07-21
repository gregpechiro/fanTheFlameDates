package com.cagnosolutions.cei.houseontherock.fantheflamedates.repository;
/**
 * Created by greg on 7/3/14.
 */

import com.cagnosolutions.cei.houseontherock.fantheflamedates.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

}