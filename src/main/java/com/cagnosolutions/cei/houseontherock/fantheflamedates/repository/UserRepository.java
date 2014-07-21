package com.cagnosolutions.cei.houseontherock.fantheflamedates.repository;

import com.cagnosolutions.cei.houseontherock.fantheflamedates.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

}
