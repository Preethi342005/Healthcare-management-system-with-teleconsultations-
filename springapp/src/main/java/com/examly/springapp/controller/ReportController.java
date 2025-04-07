package com.examly.springapp.controller;
import java.util.List;
import java.util.Optional;
import java.util.Collections;

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

import com.examly.springapp.Entity.Report;

import com.examly.springapp.Services.ReportService;
import com.examly.springapp.Repositories.ReportRepository;

@RestController
@RequestMapping("/Report")

public class ReportController {
    @Autowired
    ReportService rs;
    @Autowired
    ReportRepository rr;

    @PostMapping("/post")
   public ResponseEntity<List<Report>> createReport(@RequestBody List<Report> reports) {
    return new ResponseEntity<>(rs.createReport(reports), HttpStatus.CREATED);
   }

    @GetMapping("/get")
    public ResponseEntity<List<Report>> getAllReports() {
        return new ResponseEntity<>(rs.getAllReports(), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<List<Report>> updateReports(@RequestBody List<Report> updatedReports) {
        List<Report> updatedReportList = rs.updateReports(updatedReports);
        if (updatedReportList != null && !updatedReportList.isEmpty()) {
            return new ResponseEntity<>(updatedReportList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
  
    
    @DeleteMapping("/delete/{ids}")
    public ResponseEntity<List<Report>> deleteReports(@PathVariable List<Integer> ids) {
        List<Report> remainingReports = rs.deleteReports(ids);
    
        return remainingReports.isEmpty()
            ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList())
            : ResponseEntity.ok(remainingReports);
    }
@GetMapping("/{offset}/{pagesize}")
    public List<Report>getUsers(@PathVariable int offset,@PathVariable int pagesize)
    {
        return rs.page(pagesize,offset);
    }
    @GetMapping("/sortBy/{field}")
   public List<Report>sortReports(@PathVariable String field)
   {
    return rs.sort(field);
   }
   @GetMapping("/{offset}/{pagesize}/{field}")
   public List<Report>getUsersSorted(@PathVariable int offset,@PathVariable int pagesize,@PathVariable String field)
   {
    return rs.pageSort(pagesize,offset,field);
   }
   @GetMapping("/findByTitle")
   public Optional<Report> getUserByTitle(@RequestParam String title) {
       return rs.getReportByTitle(title);
   }
   @GetMapping("/get/{id}")
public ResponseEntity<Report> getReportById(@PathVariable int id) {
    Report report = rs.getReportById(id);
    return report != null ? ResponseEntity.ok(report) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
}

   
   @PostMapping("/addreport")
public ResponseEntity<Report> addUser(@RequestBody  Report r) {
    Report savedReport = rs.addReport(r); 
    return ResponseEntity.ok(savedReport);
   }
}