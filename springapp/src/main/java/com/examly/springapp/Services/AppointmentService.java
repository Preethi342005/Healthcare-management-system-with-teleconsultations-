// package com.examly.springapp.Services;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Sort;
// import org.springframework.stereotype.Service;

// import com.examly.springapp.Entity.Appointment;

// import com.examly.springapp.Repositories.AppointmentRepository;


// import java.util.ArrayList;
// import java.util.Date;
// import java.util.List;
// import java.util.Optional;
// @Service
// public class AppointmentService {
//     @Autowired
//     AppointmentRepository ar;

//     public List<Appointment> createAppointment(List<Appointment>a) {
//         return ar.saveAll(a);
//     }

//     public List<Appointment> getAllAppointments() {
//         return ar.findAll();
//     }

//     public Appointment getAppointmentById(int id) {
//         return ar.findById(id).orElse(null);
//     }

//     public List<Appointment> updatedAppointments(List<Appointment> updatedAppointment) {
//         List<Appointment> result = new ArrayList<>();
//         for (Appointment Appointment : updatedAppointment) {
//             Appointment existingAppointment = ar.findById(Appointment.getId()).orElse(null);
//             if (existingAppointment != null) {
//                 existingAppointment.setDateTime(Appointment.getDateTime());
//                 existingAppointment.setPatientId(Appointment.getPatientId());
//                 existingAppointment.setDoctorId(Appointment.getDoctorId());
//                 existingAppointment.setStatus(Appointment.getStatus());
                
    
    
//                 ar.save(existingAppointment);
//                 result.add(existingAppointment);
//             }
//         }
//         return result; 
//     }
    
//     public List<Appointment> deleteAppointments(List<Integer> AppointmentIds) {
//         ar.deleteAllById(AppointmentIds);
//         return ar.findAll(); // Return remaining users
//     }
    
//     public List<Appointment> page(int pageSize,int pageNumber)
//     {
        
//         Pageable page=PageRequest.of(pageNumber, pageSize);
//         return ar.findAll(page).getContent();
        
//     }
//     public List<Appointment>sort(String field)
//     {
//         Sort sort=Sort.by(Sort.Direction.ASC,field);
//         return ar.findAll(sort);
//     }
//     public List<Appointment>pagesort(int pageSize,int pageNumber,String field)
//     {
//         return ar.findAll(
//             PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC,field))
//         ).getContent();
//     }
   
//     public Appointment addAppointment(Appointment Appointment) {
//         return ar.save(Appointment); 
//     }
//     public Optional<Appointment> getAppointmentByDateTime(Date dateTime) {
//         return ar.findAppointmentByDateTime(dateTime);
//     }

    
    
// }
package com.examly.springapp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.examly.springapp.Entity.Appointment;
import com.examly.springapp.Repositories.AppointmentRepository;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(int id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    public Appointment addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(int id) {
        appointmentRepository.deleteById(id);
    }
}

