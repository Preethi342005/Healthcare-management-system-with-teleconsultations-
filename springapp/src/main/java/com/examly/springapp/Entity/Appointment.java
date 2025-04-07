// package com.examly.springapp.Entity;

// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;

// @Entity
// public class Appointment {
//      @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     int id;
//     int dateTime;
//     int patientId;
//     int doctorId;
//     String status;
    
//     public Appointment()
//     {

//     }
//     public Appointment(int id, int dateTime, int patientId, int doctorId, String status)
//     {
//         this.id=id;
//         this.dateTime = dateTime;
//         this.patientId = patientId;
//         this.doctorId = doctorId;
//         this.status = status;
//     }
//     public int getId() {
//         return id;
//     }
//     public void setId(int id) {
//         this.id = id;
//     }
//     public int getDateTime() {
//         return dateTime;
//     }
//     public void setDateTime(int dateTime) {
//         this.dateTime = dateTime;
//     }
//     public int getPatientId() {
//         return patientId;
//     }
//     public void setPatientId(int patientId) {
//         this.patientId = patientId;
//     }
//     public int getDoctorId() {
//         return doctorId;
//     }
//     public void setDoctorId(int doctorId) {
//         this.doctorId = doctorId;
//     }
//     public String getStatus() {
//         return status;
//     }
//     public void setStatus(String status) {
//         this.status = status;
//     }
// }
package com.examly.springapp.Entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime dateTime;
    private int patientId;
    private String status;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    public Appointment() {}

    public Appointment(int id, LocalDateTime dateTime, int patientId, String status, Doctor doctor) {
        this.id = id;
        this.dateTime = dateTime;
        this.patientId = patientId;
        this.status = status;
        this.doctor = doctor;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
}
