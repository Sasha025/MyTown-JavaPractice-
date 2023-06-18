package com.JavaPractice.MyTown.controller;

import com.JavaPractice.MyTown.model.Applicant;
import com.JavaPractice.MyTown.model.Institute;
import com.JavaPractice.MyTown.model.Specialty;
import com.JavaPractice.MyTown.repos.InstituteRepos;
import com.JavaPractice.MyTown.repos.SpecialtyRepos;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/specialty")
public class SpecialtyController {


    private final SpecialtyRepos specialtyRepos;
    private final InstituteRepos instituteRepos;

    public SpecialtyController(SpecialtyRepos specialtyRepos, InstituteRepos instituteRepos) {
        this.specialtyRepos = specialtyRepos;
        this.instituteRepos = instituteRepos;
    }


    @GetMapping
    public String all(Model model) {
        Iterable<Specialty> specialties = specialtyRepos.findAll();
        Iterable<Institute> institutes = instituteRepos.findAll();

        model.addAttribute("specialties", specialties);
        model.addAttribute("institutes", institutes);

        return "specialty";
    }
    @PostMapping("add")
    public String add(@RequestParam String name,
                      @RequestParam int places,
                      @RequestParam Institute institute) {
        Specialty specialty = new Specialty(name, places, institute);
        specialtyRepos.save(specialty);
        return "redirect:/specialty";
    }
    @PostMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        Specialty specialty = specialtyRepos.findById(id).orElseThrow();
        specialtyRepos.delete(specialty);
        return "redirect:/specialty";
    }
    @GetMapping("{id}")
    public String one(@PathVariable(value = "id") Long id,
                      Model model) {
        Specialty specialty = specialtyRepos.findById(id).orElseThrow();
        Iterable<Institute> institutes = instituteRepos.findAll();

        model.addAttribute("specialty", specialty);
        model.addAttribute("institutes", institutes);
        return "specialty-edit";
    }
    @PostMapping("{id}")
    public String edit(@PathVariable(value = "id") Long id,
                       @RequestParam String name,
                       @RequestParam int places,
                       @RequestParam Institute institute) {
        Specialty specialty = specialtyRepos.findById(id).orElseThrow();
        specialty.setName(name);
        specialty.setPlaces(places);
        specialty.setInstitute(institute);

        specialtyRepos.save(specialty);
        return "redirect:/specialty";
    }
}
