// package com.examly.springapp.Entity;

// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.OneToOne;

// @Entity
// public class Doctor {
//      @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     int id;
//     String name;
//     String specialization;
//     String contactInfo;
//      @OneToOne(mappedBy = "doctor")  // This makes the relationship bidirectional
//     private User user;
//     public Doctor()
//     {

//     }
//     public Doctor(int id, String name, String specialization, String contactInfo) {
//         this.id = id;
//         this.name = name;
//         this.specialization = specialization;
//         this.contactInfo = contactInfo;
//     }
//     public int getId() {
//         return id;
//     }
//     public void setId(int id) {
//         this.id = id;
//     }
//     public String getName() {
//         return name;
//     }
//     public void setName(String name) {
//         this.name = name;
//     }
//     public String getSpecialization() {
//         return specialization;
//     }
//     public void setSpecialization(String specialization) {
//         this.specialization = specialization;
//     }
//     public String getContactInfo() {
//         return contactInfo;
//     }
//     public void setContactInfo(String contactInfo) {
//         this.contactInfo = contactInfo;
//     }
    
// }
package com.examly.springapp.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String specialization;
    private String contactInfo;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments;

    public Doctor() {}

    public Doctor(int id, String name, String specialization, String contactInfo, List<Appointment> appointments) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.contactInfo = contactInfo;
        this.appointments = appointments;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }

    public List<Appointment> getAppointments() { return appointments; }
    public void setAppointments(List<Appointment> appointments) { this.appointments = appointments; }
}
