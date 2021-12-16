package com.jwtauth.repository;

import com.jwtauth.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo, Integer> {

    @Query(value="select * from userinfo u where u.userid = ?1", nativeQuery = true)
    UserInfo findByUserId(int userid);
    //public void findByUserId(Param("id") int userid);

    UserInfo findByUsername(String username);

}
