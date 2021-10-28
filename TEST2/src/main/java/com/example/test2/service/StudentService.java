package com.example.test2.service;

import com.example.test2.entity.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@NoArgsConstructor
public class StudentService {

    private StringBuilder message;

    public boolean checkStudentInput(Student student){
        message = new StringBuilder("Cannot create this student! \nWrong fields: \n");
        boolean accepted = true;
        if(student.getFullName() == null || student.getFullName().length() < 1 || student.getFullName().length() > 50){
            message.append("Name: Must be 1 - 50 characters. \n");
            accepted = false;
        }
        if(student.getBirthDay() == null){
            message.append("Birthday: Cannot be null \n");
            accepted = false;
        }else{
            Period period = Period.between(student.getBirthDay().toLocalDate(), LocalDate.now());
            if(period.getYears() > 150 || period.getYears() < 0){
                message.append("Birthday: Not a real persons'age (min = 0, max = 150) \n");
                accepted = false;
            }
        }
        if(student.getGender() == null ||
                !(student.getGender().equalsIgnoreCase("nam") ||
                student.getGender().equalsIgnoreCase("nu") ||
                student.getGender().equalsIgnoreCase("khac"))){
            message.append("Gender: Must in one of these values 'nam, nu, khac' \n");
            accepted = false;
        }
        if(student.getAverageMark() == null || student.getAverageMark() < 0 || student.getAverageMark() > 10){
            message.append("Average mark: Must be between 0 and 10 \n");
            accepted = false;
        }
        if(student.getClassName() == null){
            message.append("Class name: Cannot be null \n");
            accepted = false;
        }
        if(student.getFaculty() == null){
            message.append("Faculty: Cannot be null \n");
            accepted = false;
        }
        if (student.getHomeTown() == null){
            message.append("Home town: Cannot be null \n");
            accepted = false;
        }
        return accepted;
    }

    public String getMessage(){
        return message.toString();
    }
}
