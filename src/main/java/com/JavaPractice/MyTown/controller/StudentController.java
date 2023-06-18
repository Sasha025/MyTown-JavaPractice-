package com.JavaPractice.MyTown.controller;

import com.JavaPractice.MyTown.model.*;
import com.JavaPractice.MyTown.repos.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentRepos studentRepos;
    private final InstituteRepos instituteRepos;
    private final ApplicantRepos applicantRepos;

    public StudentController(StudentRepos studentRepos, InstituteRepos instituteRepos, ApplicantRepos applicantRepos) {
        this.studentRepos = studentRepos;
        this.instituteRepos = instituteRepos;
        this.applicantRepos = applicantRepos;
    }

    @GetMapping
    public String all(Model model) {
        Iterable<Student> students = studentRepos.findAll();
        Iterable<Institute> institutes = instituteRepos.findAll();
        Iterable<Applicant> applicants = applicantRepos.findAll();

        model.addAttribute("students", students);
        model.addAttribute("institutes", institutes);
        model.addAttribute("applicants", applicants);
        return "student";
    }
    @PostMapping("add")
    public String add(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,
                      @RequestParam String status,
                      @RequestParam String type,
                      @RequestParam Institute institute,
                      @RequestParam Applicant applicant) {
        Student student = new Student(date, status, type, applicant, institute);
        studentRepos.save(student);
        return "redirect:/student";
    }
    @PostMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        Student student = studentRepos.findById(id).orElseThrow();
        studentRepos.delete(student);
        return "redirect:/student";
    }
    @GetMapping("{id}")
    public String one(@PathVariable(value = "id") Long id,
                      Model model) {
        Student student = studentRepos.findById(id).orElseThrow();
        Iterable<Applicant> applicants = applicantRepos.findAll();
        Iterable<Institute> institutes = instituteRepos.findAll();

        model.addAttribute("student", student);
        model.addAttribute("applicants", applicants);
        model.addAttribute("institutes", institutes);
        return "student-edit";
    }
    @PostMapping("{id}")
    public String edit(@PathVariable(value = "id") Long id,
                       @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,
                       @RequestParam String status,
                       @RequestParam String type,
                       @RequestParam Institute institute,
                       @RequestParam Applicant applicant) {
        Student student = studentRepos.findById(id).orElseThrow();
        student.setDate(date);
        student.setStatus(status);
        student.setType(type);
        student.setApplicant(applicant);
        student.setInstitute(institute);

        studentRepos.save(student);
        return "redirect:/student";
    }
}
