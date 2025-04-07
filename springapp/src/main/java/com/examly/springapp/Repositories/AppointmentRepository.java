package com.examly.springapp.Repositories;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.examly.springapp.Entity.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query("SELECT ar FROM Appointment ar")
    List<Appointment> getAllDateTimes();

    // This method is removed as it was incorrectly defined
    // @Query("SELECT ar FROM Appointment ar WHERE ar.id = :id")
    // Appointment getAppointmentByDateTime(@Param("dateTime") Date dateTime);
    
    // Correctly defined method to find an appointment by dateTime
    @Query("SELECT ar FROM Appointment ar WHERE ar.dateTime = :dateTime")
    Optional<Appointment> findAppointmentByDateTime(@Param("dateTime") java.util.Date dateTime);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Appointment (dateTime, patientId, doctorId, status) VALUES (:dateTime, :patientId, :doctorId, :status)", nativeQuery = true)
    void insertAppointmentNative(@Param("dateTime") Date dateTime,
                                 @Param("patientId") int patientId,
                                 @Param("doctorId") int doctorId,
                                 @Param("status") String status);
}
