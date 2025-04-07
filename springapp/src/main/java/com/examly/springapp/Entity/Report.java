package com.examly.springapp.Entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Report {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String title;
    int patientId;
    int doctorId;
    String description;
    Date date;
    public Report()
    {

    }
    public Report(int id, String title, int patientId, int doctorId, String description, Date date){
        this.id =id;
        this.title = title;
        this. patientId = patientId;
        this.doctorId = doctorId;
        this.description= description;
        this.date = date;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    public int getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
