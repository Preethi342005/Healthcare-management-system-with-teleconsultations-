package com.examly.springapp.Repositories;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.examly.springapp.Entity.Payment;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    // Method to find a payment by status
    @Query("SELECT Pay FROM Payment Pay WHERE Pay.status = :status")
    Optional<Payment> getPaymentByStatus(@Param("status") String status);

    // Method to find a payment by ID
    Optional<Payment> findById(Integer id);

    // Method to insert a new payment using a native query
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Payment (amount, patientId, appointmentId, status) VALUES (:amount, :patientId, :appointmentId, :status)", nativeQuery = true)
    void insertPaymentNative(@Param("amount") Double amount,
                             @Param("patientId") int patientId,
                             @Param("appointmentId") int appointmentId,
                             @Param("status") String status);
}