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
import com.examly.springapp.Entity.Prescription;
import com.examly.springapp.Repositories.PrescriptionRepository;
import com.examly.springapp.Services.PrescriptionService;

@RestController
@RequestMapping("/Prescription")

public class PrescriptionController {
    @Autowired
    PrescriptionService ps;
    @Autowired
    PrescriptionRepository pr;

    @PostMapping("/post")
   public ResponseEntity<List<Prescription>> createPrescription(@RequestBody List<Prescription> Prescriptions) {
    return new ResponseEntity<>(ps.createPrescription(Prescriptions), HttpStatus.CREATED);
   }

    @GetMapping("/get")
    public ResponseEntity<List<Prescription>> getAllPrescriptions() {
        return new ResponseEntity<>(ps.getAllPrescriptions(), HttpStatus.OK);
    }
    @PutMapping("/update")
public ResponseEntity<List<Prescription>> updates(@RequestBody List<Prescription> updatedPrescriptions) {
    List<Prescription> updatedPrescriptionList = ps.updatePrescriptions(updatedPrescriptions);
    if (updatedPrescriptionList != null && !updatedPrescriptionList.isEmpty()) {
        return new ResponseEntity<>(updatedPrescriptionList, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

    
    @DeleteMapping("/delete/{ids}")
    public ResponseEntity<List<Prescription>> deletePrescriptions(@PathVariable List<Integer> ids) {
        List<Prescription> remainingPrescriptions = ps.deletePrescriptions(ids);
    
        return remainingPrescriptions.isEmpty()
            ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList())
            : ResponseEntity.ok(remainingPrescriptions);
    }
@GetMapping("/{offset}/{pagesize}")
    public List<Prescription>getPrescriptions(@PathVariable int offset,@PathVariable int pagesize)
    {
        return ps.page(pagesize,offset);
    }
    @GetMapping("/sortBy/{field}")
   public List<Prescription>sortPrescriptions(@PathVariable String field)
   {
    return ps.sort(field);
   }
   @GetMapping("/{offset}/{pagesize}/{field}")
   public List<Prescription>getPrescriptionsSorted(@PathVariable int offset,@PathVariable int pagesize,@PathVariable String field)
   {
    return ps.pagesort(pagesize,offset,field);
   }
   @GetMapping("/findByMedication")
   public Optional<Prescription> getPrescriptionByMedication(@RequestParam String medication) {
       return ps.getPrescriptionByMedication(medication);
   }
   @GetMapping("/get/{id}")
public ResponseEntity<Prescription> getPrescriptionById(@PathVariable int id) {
    Prescription prescription = ps.getPrescriptionById(id);
    return prescription != null ? ResponseEntity.ok(prescription) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
}

   @PostMapping("/addprescription")
public ResponseEntity<Prescription> addPrescription(@RequestBody Prescription p) {
    Prescription savedPrescription = ps.addUPrescription(p); 
    return ResponseEntity.ok(savedPrescription);
   }
}