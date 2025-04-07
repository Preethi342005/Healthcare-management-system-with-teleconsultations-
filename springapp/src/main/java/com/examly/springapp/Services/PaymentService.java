package com.examly.springapp.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.examly.springapp.Entity.Payment;
import com.examly.springapp.Repositories.PaymentRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class PaymentService {
       @Autowired
       PaymentRepository payr;

    public List<Payment> createPayment(List<Payment>pay) {
        return payr.saveAll(pay);
    }

    public List<Payment> getAllPayments() {
        return payr.findAll();
    }
    

    public Payment getPaymentById(int id) {
        return payr.findById(id).orElse(null);
    }

    public List<Payment> updatePayments(List<Payment> updatedPayments) {
        List<Payment> result = new ArrayList<>();
        for (Payment Payment : updatedPayments) {
            Payment existingPayment = payr.findById(Payment.getId()).orElse(null);
            if (existingPayment != null) {
                existingPayment.setAmount(Payment.getAmount());
                existingPayment.setPatientId(Payment.getPatientId());
                existingPayment.setAppointmentId(Payment.getAppointmentId());
                existingPayment.setStatus(Payment.getStatus());
    
    
                payr.save(existingPayment);
                result.add(existingPayment);
            }
        }
        return result; 
    }
    
   
    public List<Payment> deletePayments(List<Integer> paymentIds) {
        payr.deleteAllById(paymentIds);
        return payr.findAll(); 
    }
    
    
    public List<Payment> page(int pageSize,int pageNumber)
    {
        
        Pageable page=PageRequest.of(pageNumber, pageSize);
        return payr.findAll(page).getContent();
        
    }
    public List<Payment>sort(String field)
    {
        Sort sort=Sort.by(Sort.Direction.ASC,field);
        return payr.findAll(sort);
    }
    public List<Payment>pagesort(int pageSize,int pageNumber,String field)
    {
        return payr.findAll(
            PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC,field))
        ).getContent();
    }
   
    public Payment addPayment(Payment Payment) {
        return payr.save(Payment); 
    }
    public Optional<Payment> getPaymentByStatus(String status) {
        return payr.getPaymentByStatus(status);
    }
}
    


