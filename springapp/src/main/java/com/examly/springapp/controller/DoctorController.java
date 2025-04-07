// package com.examly.springapp.controller;
// import java.util.Collections;
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
// import com.examly.springapp.Entity.User;


// // import org.springframework.data.domain.PageRequest;
// // import org.springframework.data.domain.Pageable;
// // import org.springframework.data.domain.Sort;

// import com.examly.springapp.Entity.Doctor;

// import com.examly.springapp.Repositories.DoctorRepository;
// import com.examly.springapp.Services.DoctorService;

// @RestController
// @RequestMapping("/Doctor")

// public class DoctorController {
//     @Autowired
//     DoctorService ds;
//     @Autowired
//     DoctorRepository dr;
    

//     @PostMapping("/post")
//    public ResponseEntity<List<Doctor>> createDoctor(@RequestBody List<Doctor> Doctors) {
//     return new ResponseEntity<>(ds.createDoctor(Doctors), HttpStatus.CREATED);
//    }

//     @GetMapping("/get")
//     public ResponseEntity<List<Doctor>> getAllDoctors() {
//         return new ResponseEntity<>(ds.getAllDoctors(), HttpStatus.OK);
//     }
//     @PutMapping("/update")
//     public ResponseEntity<List<Doctor>> updates(@RequestBody List<Doctor> updatedDoctors) {
//         List<Doctor> updatedDoctorList = ds.updateDoctors(updatedDoctors);
//         if (updatedDoctorList != null && !updatedDoctorList.isEmpty()) {
//             return new ResponseEntity<>(updatedDoctorList, HttpStatus.OK);
//         } else {
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//         }
//     }
    
//     @DeleteMapping("/delete/{ids}")
//     public ResponseEntity<List<Doctor>> deleteDoctors(@PathVariable List<Integer> ids) {
//         List<Doctor> remainingDoctors = ds.deleteDoctors(ids);
    
//         return remainingDoctors.isEmpty()
//             ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList())
//             : ResponseEntity.ok(remainingDoctors);
//     }
// @GetMapping("/{offset}/{pagesize}")
//     public List<Doctor>getDoctors(@PathVariable int offset,@PathVariable int pagesize)
//     {
//         return ds.page(pagesize,offset);
//     }
//     @GetMapping("/sortBy/{field}")
//    public List<Doctor>sortDoctors(@PathVariable String field)
//    {
//     return ds.sort(field);
//    }
//    @GetMapping("/{offset}/{pagesize}/{field}")
//    public List<Doctor>getDoctorsSorted(@PathVariable int offset,@PathVariable int pagesize,@PathVariable String field)
//    {
//     return ds.pagesort(pagesize,offset,field);
//    }
//    @GetMapping("/findByName")
//    public Optional<Doctor> getDoctorByName(@RequestParam String Name) {
//        return ds.getDoctorByName(Name);
//    }
//     @GetMapping("/get/{id}")
// public ResponseEntity<Doctor> getDoctoryId(@PathVariable int id) {
//     Doctor doctor = ds.getDoctorById(id);
//     return doctor != null ? ResponseEntity.ok(doctor) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
// }
//    @PostMapping("/addDoctor")
// public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor dr) {
//     Doctor savedDoctor = ds.addDoctor(dr); 
//     return ResponseEntity.ok(savedDoctor);
//    }
// }
package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.examly.springapp.Entity.Doctor;
import com.examly.springapp.Services.DoctorService;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/all")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable int id) {
        Doctor doctor = doctorService.getDoctorById(id);
        return doctor != null ? ResponseEntity.ok(doctor) : ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.addDoctor(doctor));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable int id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok().build();
    }
}
