package com.example.test2.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_STUDENT_ID")
    @SequenceGenerator(name = "GEN_STUDENT_ID", sequenceName = "SEQ_STUDENT", allocationSize = 1)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "birth_day", nullable = false)
    private Date birthDay;

    @Column(name = "class_name", nullable = false)
    private String className;

    @Column(name = "faculty", nullable = false)
    private String faculty;

    @Column(name = "home_town", nullable = false)
    private String homeTown;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "average_mark", nullable = false)
    private Double averageMark;

}
