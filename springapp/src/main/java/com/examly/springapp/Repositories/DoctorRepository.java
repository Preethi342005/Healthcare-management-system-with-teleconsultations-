package com.examly.springapp.Repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.examly.springapp.Entity.Doctor;  

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    // Method to find a doctor by name
    @Query("SELECT dr FROM Doctor dr WHERE dr.name = :name")
    Optional<Doctor> getDoctorByName(@Param("name") String name);

    // Method to find a doctor by ID
    Optional<Doctor> findById(Integer id);

    // Method to insert a new doctor using a native query
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Doctor (name, specialization, contactInfo) VALUES (:name, :specialization, :contactInfo)", nativeQuery = true)
    void insertDoctorNative(@Param("name") String name,
                            @Param("specialization") String specialization,
                            @Param("contactInfo") String contactInfo);
}

