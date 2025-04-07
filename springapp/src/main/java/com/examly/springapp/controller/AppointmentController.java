// package com.examly.springapp.controller;
// import java.util.Collections;
// import java.util.Date;
// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// // import org.springframework.data.domain.PageRequest;
// // import org.springframework.data.domain.Pageable;
// // import org.springframework.data.domain.Sort;

// import com.examly.springapp.Entity.Appointment;

// import com.examly.springapp.Repositories.AppointmentRepository;
// import com.examly.springapp.Services.AppointmentService;

// @RestController
// @RequestMapping("/Appointment")

// public class AppointmentController {
//     @Autowired
//     AppointmentService as;
//     @Autowired
//     AppointmentRepository ar;

//     @PostMapping("/post")
//    public ResponseEntity<List<Appointment>> createAppointment(@RequestBody List<Appointment> Appointments) {
//     return new ResponseEntity<>(as.createAppointment(Appointments), HttpStatus.CREATED);
//    }

//     @GetMapping("/get")
//     public ResponseEntity<List<Appointment>> getAllAppointments() {
//         return new ResponseEntity<>(as.getAllAppointments(), HttpStatus.OK);
//     }
//     @PutMapping("/update")
//     public ResponseEntity<List<Appointment>> updates(@RequestBody List<Appointment> updatedAppointments) {
//         List<Appointment> updatedAppointmentList = as.updatedAppointments(updatedAppointments);
//         if (updatedAppointmentList != null && !updatedAppointmentList.isEmpty()) {
//             return new ResponseEntity<>(updatedAppointmentList, HttpStatus.OK);
//         } else {
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//         }
//     }
    
//     @DeleteMapping("/delete/{ids}")
//     public ResponseEntity<List<Appointment>> deleteAppointments(@PathVariable List<Integer> ids) {
//         List<Appointment> remainingAppointments = as.deleteAppointments(ids);
    
//         return remainingAppointments.isEmpty()
//             ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList())
//             : ResponseEntity.ok(remainingAppointments);
//     }
// @GetMapping("/{offset}/{pagesize}")
//     public List<Appointment>getAppointments(@PathVariable int offset,@PathVariable int pagesize)
//     {
//         return as.page(pagesize,offset);
//     }
//     @GetMapping("/sortBy/{field}")
//    public List<Appointment>sortAppointments(@PathVariable String field)
//    {
//     return as.sort(field);
//    }
//    @GetMapping("/{offset}/{pagesize}/{field}")
//    public List<Appointment>getAppointmentsSorted(@PathVariable int offset,@PathVariable int pagesize,@PathVariable String field)
//    {
//     return as.pagesort(pagesize,offset,field);
//    }
//    @GetMapping("/findByDateTime")
//    public Optional<Appointment> getAppointmentByDateTime(@RequestParam Date DateTime) {
//        return as.getAppointmentByDateTime(DateTime);
//    }
//     @GetMapping("/get/{id}")
// public ResponseEntity<Appointment> getAppointmentById(@PathVariable int id) {
//     Appointment appointment = as.getAppointmentById(id);
//     return appointment != null ? ResponseEntity.ok(appointment) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
// }
//    @PostMapping("/addAppointment")
// public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment ar) {
//    Appointment savedAppointment = as.addAppointment(ar); 
//     return ResponseEntity.ok(savedAppointment);
//    }
// }
package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.examly.springapp.Entity.Appointment;
import com.examly.springapp.Services.AppointmentService;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/all")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable int id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        return appointment != null ? ResponseEntity.ok(appointment) : ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment) {
        return ResponseEntity.ok(appointmentService.addAppointment(appointment));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable int id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok().build();
    }
}
