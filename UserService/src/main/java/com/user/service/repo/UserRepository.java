package com.user.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.user.service.dto.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Query("select count(*) from User u where u.username = :userName")
	public long findAllUserByUserName(@Param("userName") String userName);

	@Query("select u from User u where u.username = :username and u.password = :password")
	User validateExistUser(@Param("username") String username, @Param("password") String password);
}
