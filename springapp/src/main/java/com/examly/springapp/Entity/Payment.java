package com.examly.springapp.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Payment {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    Double amount;
    int patientId;
    int appointmentId;
    String status;
    public Payment()
    {

    }
    public Payment(int id, Double amount, int patientId, int appointmemtId, String status)
    {
        this.id = id;
        this.amount = amount;
        this.patientId = patientId;
        this.appointmentId = appointmemtId;
        this.status = status;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    public int getAppointmentId() {
        return appointmentId;
    }
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    



}
