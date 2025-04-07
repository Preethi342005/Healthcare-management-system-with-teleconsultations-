package com.examly.springapp.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.examly.springapp.Entity.Patient;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    // Method to find a patient by name
    @Query("SELECT Pat FROM Patient Pat WHERE Pat.name = :name")
    Optional<Patient> getPatientByName(@Param("name") String name);

    // Method to find a patient by ID
    Optional<Patient> findById(Integer id);

    // Method to insert a new patient using a native query
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Patient (name, contactInfo) VALUES (:name, :contactInfo)", nativeQuery = true)
    void insertPatientNative(@Param("name") String name,
                             @Param("contactInfo") String contactInfo);
}
  