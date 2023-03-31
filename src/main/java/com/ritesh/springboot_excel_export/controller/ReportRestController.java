package com.ritesh.springboot_excel_export.controller;

import com.ritesh.springboot_excel_export.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

//@RequiredArgsConstructor
@RestController
public class ReportRestController {

    //Instance variable inside class but outside method,block and constructor

    private ReportService reportService;

    //Setter injection
    @Autowired
    public void setReportService(@Autowired ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/excel")
    public ResponseEntity<String> generateExcelReport(HttpServletResponse response) throws Exception {

        response.setContentType("application/octet-stream");


        String headerKey = "Content-Disposition";
        String headerValue = "attachment;fileName= course.xls";

        response.setHeader(headerKey, headerValue);
        reportService.generateExcel(response);
        return ResponseEntity.ok("excel file generated");
    }
}
