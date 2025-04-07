package com.examly.springapp.controller;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Sort;

import com.examly.springapp.Entity.Patient;

import com.examly.springapp.Repositories.PatientRepository;
import com.examly.springapp.Services.PatientService;

@RestController
@RequestMapping("/Patient")

public class PatientController {
    @Autowired
    PatientService pats;
    @Autowired
    PatientRepository patr;

    @PostMapping("/post")
   public ResponseEntity<List<Patient>> createPatient(@RequestBody List<Patient> Patients) {
    return new ResponseEntity<>(pats.createPatient(Patients), HttpStatus.CREATED);
   }

    @GetMapping("/get")
    public ResponseEntity<List<Patient>> getAllPatients() {
        return new ResponseEntity<>(pats.getAllPatients(), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<List<Patient>> updatePatients(@RequestBody List<Patient> updatedPatients) {
        List<Patient> updatedPatientList = pats.updatePatients(updatedPatients);
        if (updatedPatientList != null && !updatedPatientList.isEmpty()) {
            return new ResponseEntity<>(updatedPatientList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/delete/{ids}")
    public ResponseEntity<List<Patient>> deletePatients(@PathVariable List<Integer> ids) {
        List<Patient> remainingPatients = pats.deletePatients(ids);
    
        return remainingPatients.isEmpty()
            ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList())
            : ResponseEntity.ok(remainingPatients);
    }
@GetMapping("/{offset}/{pagesize}")
    public List<Patient>getPatients(@PathVariable int offset,@PathVariable int pagesize)
    {
        return pats.page(pagesize,offset);
    }
    @GetMapping("/sortBy/{field}")
   public List<Patient>sortPatients(@PathVariable String field)
   {
    return pats.sort(field);
   }
   @GetMapping("/{offset}/{pagesize}/{field}")
   public List<Patient>getPatientsSorted(@PathVariable int offset,@PathVariable int pagesize,@PathVariable String field)
   {
    return pats.pagesort(pagesize,offset,field);
   }
   @GetMapping("/findByName")
   public Optional<Patient> getPatientByName(@RequestParam String Name) {
       return pats.getPatientByName(Name);
   }
    @GetMapping("/get/{id}")
public ResponseEntity<Patient> getPatientById(@PathVariable int id) {
    Patient patient = pats.getPatientById(id);
    return patient != null ? ResponseEntity.ok(patient) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
}
   
   
   @PostMapping("/addPatient")
public ResponseEntity<Patient> addPatient(@RequestBody Patient pat) {
   Patient savedPatient = pats.addPatient(pat); 
    return ResponseEntity.ok(savedPatient);
   }
}