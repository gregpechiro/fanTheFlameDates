package com.cagnosolutions.cei.houseontherock.fantheflamedates.repository;
/**
 * Created by greg on 8/25/14.
 */

import com.cagnosolutions.cei.houseontherock.fantheflamedates.domain.VimeoVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VimeoVideoRepository extends JpaRepository<VimeoVideo, String> {

}