package com.ritesh.springboot_excel_export.service;

import com.ritesh.springboot_excel_export.entity.CourseEntity;
import com.ritesh.springboot_excel_export.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final CourseRepository courseRepository;

    public void generateExcel(HttpServletResponse response) throws IOException {

        List<CourseEntity> courses = courseRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Course Info");
        HSSFRow row = sheet.createRow(0); // header row
        row.createCell(0).setCellValue("Id");
        row.createCell(1).setCellValue("Name");
        row.createCell(2).setCellValue("Description");
        row.createCell(3).setCellValue("Price");

        //store data in the next row like row index 1
        int dataRowIndex = 1;

        for (CourseEntity courseEntity : courses) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);

            dataRow.createCell(0).setCellValue(courseEntity.getCourseId());
            dataRow.createCell(1).setCellValue(courseEntity.getCourseName());
            dataRow.createCell(2).setCellValue(courseEntity.getCourseDescription());
            dataRow.createCell(3).setCellValue(courseEntity.getCoursePrice());
            dataRowIndex++;
        }

        // to store excel data to excel file

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);

        outputStream.close();
    }

}

