package com.examly.springapp.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Prescription {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String medication;
    String dosage;
    String duration;
    String instructions; 
    public Prescription()
    {

    }
    public Prescription(int id, String medication, String dosage, String duration, String instructions){
        this.id = id;
        this.medication = medication;
        this.dosage = dosage;
        this.duration = duration;
        this.instructions = instructions;
    }
    

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMedication() {
        return medication;
    }
    public void setMedication(String medication) {
        this.medication = medication;
    }
    public String getDosage() {
        return dosage;
    }
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getInstructions() {
        return instructions;
    }
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}