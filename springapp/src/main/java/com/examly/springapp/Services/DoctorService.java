// package com.examly.springapp.Services;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Sort;
// import org.springframework.stereotype.Service;

// import com.examly.springapp.Entity.Doctor;

// import com.examly.springapp.Repositories.DoctorRepository;


// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;
// @Service
// public class DoctorService {
//     @Autowired
//     DoctorRepository dr;
    

//     public List<Doctor> createDoctor(List<Doctor>d) {
//         return dr.saveAll(d);
//     }

//     public List<Doctor> getAllDoctors() {
//         return dr.findAll();
//     }

//     public Doctor getDoctorById(int id) {
//         return dr.findById(id).orElse(null);
//     }

//     public List<Doctor> updateDoctors(List<Doctor> updatedDoctor) {
//         List<Doctor> result = new ArrayList<>();
//         for (Doctor Doctor : updatedDoctor) {
//             Doctor existingDoctor = dr.findById(Doctor.getId()).orElse(null);
//             if (existingDoctor != null) {
//                 existingDoctor.setName(Doctor.getName());
//                 existingDoctor.setSpecialization(Doctor.getSpecialization());
//                 existingDoctor.setContactInfo(Doctor.getContactInfo());
                
    
    
//                 dr.save(existingDoctor);
//                 result.add(existingDoctor);
//             }
//         }
//         return result; 
//     }
    
//     public List<Doctor> deleteDoctors(List<Integer> DoctorIds) {
//         dr.deleteAllById(DoctorIds);
//         return dr.findAll(); // Return remaining users
//     }
    
//     public List<Doctor> page(int pageSize,int pageNumber)
//     {
        
//         Pageable page=PageRequest.of(pageNumber, pageSize);
//         return dr.findAll(page).getContent();
        
//     }
//     public List<Doctor>sort(String field)
//     {
//         Sort sort=Sort.by(Sort.Direction.ASC,field);
//         return dr.findAll(sort);
//     }
//     public List<Doctor>pagesort(int pageSize,int pageNumber,String field)
//     {
//         return dr.findAll(
//             PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC,field))
//         ).getContent();
//     }
   
//     public Doctor addDoctor(Doctor Doctor) {
//         return dr.save(Doctor); 
//     }
//     public Optional<Doctor> getDoctorByName(String name) {
//         return dr.getDoctorByName(name);
//     }
    
// }

package com.examly.springapp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.examly.springapp.Entity.Doctor;
import com.examly.springapp.Repositories.DoctorRepository;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(int id) {
        return doctorRepository.findById(id).orElse(null);
    }

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(int id) {
        doctorRepository.deleteById(id);
    }
}
