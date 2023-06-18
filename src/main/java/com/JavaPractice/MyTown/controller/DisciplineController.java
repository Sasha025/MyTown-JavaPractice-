package com.JavaPractice.MyTown.controller;

import com.JavaPractice.MyTown.model.Applicant;
import com.JavaPractice.MyTown.model.Discipline;
import com.JavaPractice.MyTown.model.Specialty;
import com.JavaPractice.MyTown.repos.DisciplineRepos;
import com.JavaPractice.MyTown.repos.SpecialtyRepos;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/discipline")
public class DisciplineController {
    private final DisciplineRepos disciplineRepos;
    private final SpecialtyRepos specialtyRepos;

    public DisciplineController(DisciplineRepos disciplineRepos, SpecialtyRepos specialtyRepos) {
        this.disciplineRepos = disciplineRepos;
        this.specialtyRepos = specialtyRepos;
    }

    @GetMapping
    public String all(Model model) {
        Iterable<Discipline> disciplines = disciplineRepos.findAll();
        Iterable<Specialty> specialties = specialtyRepos.findAll();

        model.addAttribute("disciplines", disciplines);
        model.addAttribute("specialties", specialties);

        return "discipline";
    }
    @PostMapping("add")
    public String add(@RequestParam Specialty specialty,
                      @RequestParam String name) {
        Discipline discipline= new Discipline(name, specialty);
        disciplineRepos.save(discipline);
        return "redirect:/discipline";
    }
    @PostMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        Discipline discipline = disciplineRepos.findById(id).orElseThrow();
        disciplineRepos.delete(discipline);
        return "redirect:/discipline";
    }
    @GetMapping("{id}")
    public String one(@PathVariable(value = "id") Long id,
                      Model model) {
        Discipline discipline = disciplineRepos.findById(id).orElseThrow();
        Iterable<Specialty> specialties = specialtyRepos.findAll();

        model.addAttribute("discipline", discipline);
        model.addAttribute("specialties", specialties);
        return "discipline-edit";
    }
    @PostMapping("{id}")
    public String edit(@PathVariable(value = "id") Long id,
                       @RequestParam Specialty specialty,
                       @RequestParam String name) {
        Discipline discipline = disciplineRepos.findById(id).orElseThrow();
        discipline.setName(name);
        discipline.setSpecialty(specialty);
        disciplineRepos.save(discipline);
        return "redirect:/discipline";
    }
}
