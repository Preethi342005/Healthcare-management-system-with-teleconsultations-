
package com.examly.springapp.Repositories;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.examly.springapp.Entity.Report;


@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

    // Method to find a report by title
    @Query("SELECT r FROM Report r WHERE r.title = :title")
    Optional<Report> getReportByTitle(@Param("title") String title);

    // Method to find a report by ID
    Optional<Report> findById(Integer id);

    // Method to insert a new report using a native query
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Report (title, patientId, doctorId, description, date) VALUES (:title, :patientId, :doctorId, :description, :date)", nativeQuery = true)
    void insertReportNative(@Param("title") String title,
                            @Param("patientId") int patientId,
                            @Param("doctorId") int doctorId,
                            @Param("description") String description,
                            @Param("date") Date date);   
}