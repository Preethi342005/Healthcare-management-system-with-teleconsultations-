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
import com.examly.springapp.Entity.Payment;
import com.examly.springapp.Repositories.PaymentRepository;
import com.examly.springapp.Services.PaymentService;

@RestController
@RequestMapping("/Payment")

public class PaymentController {
    @Autowired
    PaymentService pays;
    @Autowired
    PaymentRepository payr;

    @PostMapping("/post")
   public ResponseEntity<List<Payment>> createPayment(@RequestBody List<Payment> Payments) {
    return new ResponseEntity<>(pays.createPayment(Payments), HttpStatus.CREATED);
   }

    @GetMapping("/get")
    public ResponseEntity<List<Payment>> getAllPayments() {
        return new ResponseEntity<>(pays.getAllPayments(), HttpStatus.OK);
    }
    @PutMapping("/update")
public ResponseEntity<List<Payment>> updates(@RequestBody List<Payment> updatedPayments) {
    List<Payment> updatedPaymentList = pays.updatePayments(updatedPayments);
    if (updatedPaymentList != null && !updatedPaymentList.isEmpty()) {
        return new ResponseEntity<>(updatedPaymentList, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

    
    @DeleteMapping("/delete/{ids}")
    public ResponseEntity<List<Payment>> deletePayments(@PathVariable List<Integer> ids) {
        List<Payment> remainingPayments = pays.deletePayments(ids);
    
        return remainingPayments.isEmpty()
            ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList())
            : ResponseEntity.ok(remainingPayments);
    }
@GetMapping("/{offset}/{pagesize}")
    public List<Payment>getPayments(@PathVariable int offset,@PathVariable int pagesize)
    {
        return pays.page(pagesize,offset);
    }
    @GetMapping("/sortBy/{field}")
   public List<Payment>sortPayments(@PathVariable String field)
   {
    return pays.sort(field);
   }
   @GetMapping("/{offset}/{pagesize}/{field}")
   public List<Payment>getPaymentsSorted(@PathVariable int offset,@PathVariable int pagesize,@PathVariable String field)
   {
    return pays.pagesort(pagesize,offset,field);
   }
   @GetMapping("/findByMedication")
   public Optional<Payment> getPrescriptionByStatus(@RequestParam String status) {
       return pays.getPaymentByStatus(status);
   }
   @GetMapping("/get/{id}")
public ResponseEntity<Payment> getPaymentById(@PathVariable int id) {
    Payment payment = pays.getPaymentById(id);
    return payment != null ? ResponseEntity.ok(payment) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
}

   @PostMapping("/addpayment")
public ResponseEntity<Payment> addPayment(@RequestBody Payment pay) {
    Payment savedPayment = pays.addPayment(pay); 
    return ResponseEntity.ok(savedPayment);
   }
}