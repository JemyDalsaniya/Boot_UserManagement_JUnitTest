package com.springboot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.model.User;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserEmailAndUserPassword(String userEmail, String userPassword);

	User findByUserId(int id);

	@Modifying
	@Query("Update User u SET u.userPassword =:userPassword where u.userEmail =:userEmail")
	void updatePassword(@Param("userEmail") String userEmail, @Param("userPassword") String userPassword);

	List<User> findByUserEmail(String userEmail);

	@Modifying
	@Query("Update User u SET u.userStatus=1 where u.userId =:userId")
	void changeRole(@Param("userId") int userId);

	@Query("FROM User u where u.userStatus=1")
	List<User> findByUserStatus(boolean userStatus);

}
