package com.examly.springapp.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.examly.springapp.Entity.Prescription;
import com.examly.springapp.Repositories.PrescriptionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository pr;

    public List<Prescription> createPrescription(List<Prescription>p) {
        return pr.saveAll(p);
    }

    public List<Prescription> getAllPrescriptions() {
        return pr.findAll();
    }

    public Prescription getPrescriptionById(int id) {
        return pr.findById(id).orElse(null);
    }

    public List<Prescription> updatePrescriptions(List<Prescription> updatedPrescriptions) {
        List<Prescription> result = new ArrayList<>();
        for (Prescription Prescription : updatedPrescriptions) {
            Prescription existingPrescription = pr.findById(Prescription.getId()).orElse(null);
            if (existingPrescription != null) {
                existingPrescription.setMedication(Prescription.getMedication());
                existingPrescription.setDosage(Prescription.getDosage());
                existingPrescription.setDuration(Prescription.getDuration());
                existingPrescription.setInstructions(Prescription.getInstructions());
    
    
                pr.save(existingPrescription);
                result.add(existingPrescription);
            }
        }
        return result; 
    }
    
    public List<Prescription> deletePrescriptions(List<Integer> PrescriptionIds) {
        pr.deleteAllById(PrescriptionIds);
        return pr.findAll(); // Return remaining users
    }
    
    public List<Prescription> page(int pageSize,int pageNumber)
    {
        
        Pageable page=PageRequest.of(pageNumber, pageSize);
        return pr.findAll(page).getContent();
        
    }
    public List<Prescription>sort(String field)
    {
        Sort sort=Sort.by(Sort.Direction.ASC,field);
        return pr.findAll(sort);
    }
    public List<Prescription>pagesort(int pageSize,int pageNumber,String field)
    {
        return pr.findAll(
            PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC,field))
        ).getContent();
    }
   
    public Prescription addUPrescription(Prescription Prescription) {
        return pr.save(Prescription); 
    }
    public Optional<Prescription> getPrescriptionByMedication(String medication) {
        return pr.findPrescriptionByMedication(medication);
    }
}
    


