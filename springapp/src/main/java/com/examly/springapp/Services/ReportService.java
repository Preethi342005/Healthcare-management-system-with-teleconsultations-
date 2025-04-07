package com.examly.springapp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examly.springapp.Entity.Report;
import com.examly.springapp.Repositories.ReportRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {
    @Autowired
    private ReportRepository rr;

    public List<Report> createReport(List<Report> reports) {
        return rr.saveAll(reports);
    }

    public List<Report> getAllReports() {
        return rr.findAll();
    }

    public Report getReportById(int id) {
        return rr.findById(id).orElse(null);
    }

    public List<Report> updateReports(List<Report> updatedReports) {
        List<Report> result = new ArrayList<>();
        for (Report report : updatedReports) {
            Report existingReport = rr.findById(report.getId()).orElse(null);
            if (existingReport != null) {
                existingReport.setTitle(report.getTitle());
                existingReport.setPatientId(report.getPatientId());
                existingReport.setDoctorId(report.getDoctorId());
                existingReport.setDescription(report.getDescription());
                existingReport.setDate(report.getDate());

                rr.save(existingReport);
                result.add(existingReport);
            }
        }
        return result; 
    }
    
    public List<Report> deleteReports(List<Integer> reportIds) {
        rr.deleteAllById(reportIds);
        return rr.findAll(); // Return remaining reports
    }
    
    public List<Report> page(int pageSize, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return rr.findAll(page).getContent();
    }

    public List<Report> sort(String field) {
        Sort sort = Sort.by(Sort.Direction.ASC, field);
        return rr.findAll(sort);
    }

    public List<Report> pageSort(int pageSize, int pageNumber, String field) {
        return rr.findAll(
            PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC, field))
        ).getContent();
    }
   
    public Report addReport(Report report) {
        return rr.save(report); 
    }

    public Optional<Report> getReportByTitle(String title) {
        return rr.getReportByTitle(title); // Assuming you have a method in the repository
    }
}