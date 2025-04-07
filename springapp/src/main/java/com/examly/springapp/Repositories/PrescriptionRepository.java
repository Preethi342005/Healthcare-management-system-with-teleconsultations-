package com.examly.springapp.Repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.examly.springapp.Entity.Prescription;


@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {

    
    @Query("SELECT Pr FROM Prescription Pr WHERE Pr.medication = :medication")
    Optional<Prescription> getPrescriptionByMedication(@Param("medication") String medication);

   
    Optional<Prescription> findPrescriptionByMedication(@Param("medication") String medication);

  
    Optional<Prescription> findById(Integer id);

    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Prescription (medication, dosage, duration, instructions) VALUES (:medication, :dosage, :duration, :instructions)", nativeQuery = true)
    void insertPrescriptionNative(@Param("medication") String medication,
                                  @Param("dosage") String dosage,
                                  @Param("duration") String duration,
                                  @Param("instructions") String instructions);
   
}