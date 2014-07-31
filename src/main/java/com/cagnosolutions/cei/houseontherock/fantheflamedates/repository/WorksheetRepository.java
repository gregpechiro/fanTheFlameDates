package com.cagnosolutions.cei.houseontherock.fantheflamedates.repository;
/**
 * Created by greg on 7/29/14.
 */

import com.cagnosolutions.cei.houseontherock.fantheflamedates.domain.Worksheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorksheetRepository extends JpaRepository<Worksheet, Long> {

}