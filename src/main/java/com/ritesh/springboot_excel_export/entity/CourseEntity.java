package com.ritesh.springboot_excel_export.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COURSE_DTLS")
public class CourseEntity {

    @Id
    private Integer courseId;
    private String courseName;
    private String courseDescription;
    private Integer coursePrice;

}
