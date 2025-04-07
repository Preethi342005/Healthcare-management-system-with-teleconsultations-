package com.examly.springapp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examly.springapp.Entity.Patient;
import com.examly.springapp.Repositories.PatientRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class PatientService {
    @Autowired
    PatientRepository patr;

    public List<Patient> createPatient(List<Patient>pat) {
        return patr.saveAll(pat);
    }

    public List<Patient> getAllPatients() {
        return patr.findAll();
    }

    public Patient getPatientById(int id) {
        return patr.findById(id).orElse(null);
    }

    public List<Patient> updatePatients(List<Patient> updatedPatients) {
        List<Patient> result = new ArrayList<>();
        for (Patient Patient : updatedPatients) {
            Patient existingPatient = patr.findById(Patient.getId()).orElse(null);
            if (existingPatient != null) {
                existingPatient.setName(Patient.getName());
                existingPatient.setContactInfo(Patient.getContactInfo());
                
    
    
                patr.save(existingPatient);
                result.add(existingPatient);
            }
        }
        return result; 
    }
    
    public List<Patient> deletePatients(List<Integer> PatientIds) {
        patr.deleteAllById(PatientIds);
        return patr.findAll(); // Return remaining users
    }
    
    public List<Patient> page(int pageSize,int pageNumber)
    {
        
        Pageable page=PageRequest.of(pageNumber, pageSize);
        return patr.findAll(page).getContent();
        
    }
    public List<Patient>sort(String field)
    {
        Sort sort=Sort.by(Sort.Direction.ASC,field);
        return patr.findAll(sort);
    }
    public List<Patient>pagesort(int pageSize,int pageNumber,String field)
    {
        return patr.findAll(
            PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC,field))
        ).getContent();
    }
   
    public Patient addPatient(Patient Patient) {
        return patr.save(Patient); 
    }
    public Optional<Patient> getPatientByName(String name) {
        return patr.getPatientByName(name);
    }
}
    


