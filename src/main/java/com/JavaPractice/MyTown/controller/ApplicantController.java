package com.JavaPractice.MyTown.controller;

import com.JavaPractice.MyTown.model.Applicant;
import com.JavaPractice.MyTown.model.Specialty;
import com.JavaPractice.MyTown.repos.ApplicantRepos;
import com.JavaPractice.MyTown.repos.SpecialtyRepos;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/applicant")
public class ApplicantController {
    private final ApplicantRepos applicantRepos;
    private final SpecialtyRepos specialtyRepos;

    public ApplicantController(ApplicantRepos applicantRepos, SpecialtyRepos specialtyRepos) {
        this.applicantRepos = applicantRepos;
        this.specialtyRepos = specialtyRepos;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Applicant> applicants = applicantRepos.findAll();
        Iterable<Specialty> specialties = specialtyRepos.findAll();

        model.addAttribute("applicants", applicants);
        model.addAttribute("specialties", specialties);

        return "applicant";
    }
    @PostMapping("add")
    public String add(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,
                      @RequestParam Specialty specialty,
                      @RequestParam(defaultValue = "false") boolean gold_medal,
                      @RequestParam String name) {
        Applicant applicant = new Applicant(name, date, specialty, gold_medal);
        applicantRepos.save(applicant);
        return "redirect:/applicant";
    }
    @PostMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        Applicant  applicant = applicantRepos.findById(id).orElseThrow();
        applicantRepos.delete(applicant);
        return "redirect:/applicant";
    }
    @GetMapping("{id}")
    public String one(@PathVariable(value = "id") Long id,
                      Model model) {
        Applicant applicant = applicantRepos.findById(id).orElseThrow();
        Iterable<Specialty> specialties = specialtyRepos.findAll();

        model.addAttribute("applicant", applicant);
        model.addAttribute("specialties", specialties);
        return "applicant-edit";
    }
    @PostMapping("{id}")
    public String edit(@PathVariable(value = "id") Long id,
                       @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,
                       @RequestParam Specialty specialty,
                       @RequestParam(defaultValue = "false") boolean gold_medal,
                       @RequestParam String name) {
        Applicant applicant = applicantRepos.findById(id).orElseThrow();
        applicant.setName(name);
        applicant.setDate(date);
        applicant.setSpecialty(specialty);
        applicant.setGold_medal(gold_medal);

        applicantRepos.save(applicant);
        return "redirect:/applicant";
    }
}
