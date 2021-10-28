package com.example.test2.controller;

import com.example.test2.dao.StudentDao;
import com.example.test2.entity.Student;
import com.example.test2.service.StudentService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/students")
public class StudentController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStudents(@Context UriInfo uriInfo){
        List<Student> students = StudentDao.getStudentsByParam(uriInfo.getQueryParameters());
        return Response.status(Response.Status.OK).entity(students).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addStudent(Student student){
        StudentService studentService = new StudentService();
        boolean checkStudent = studentService.checkStudentInput(student);
        if(!checkStudent){
            String message = studentService.getMessage();
            return Response.status(Response.Status.OK).entity(message).build();
        }
        StudentDao.createStudent(student);
        return Response.status(Response.Status.CREATED).entity(student).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateStudent(Student student){
        StudentDao.updateStudentInfo(student);
        return Response.status(Response.Status.OK).entity(student).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteStudent(@PathParam("id") Long id){
        Student student = StudentDao.getStudentById(id);
        StringBuilder result = new StringBuilder();
        if(student == null){
            result.append("This student doesnt exist");
            return Response.status(Response.Status.FORBIDDEN).entity(result.toString()).build();
        }
        StudentDao.deleteStudent(student);
        result.append("Delete succesfull");
        return Response.status(Response.Status.OK).entity(result.toString()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/birthday")
    public Response getAllBirthdayPeople(){
        List<Student> students = StudentDao.findBirthdayPeople();
        return Response.status(Response.Status.OK).entity(students).build();
    }
}
