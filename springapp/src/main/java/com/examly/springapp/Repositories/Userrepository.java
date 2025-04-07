package com.examly.springapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.examly.springapp.Entity.User;


@Repository
public interface Userrepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> getUserByEmail(@Param("email") String email);
    Optional<User> findById(Integer id); 
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO User (name, email, password) VALUES (:name, :email, :password)", nativeQuery = true)
    void insertUserNative(@Param("name") String name,
                          @Param("email") String email,
                          @Param("password") String password);
}
