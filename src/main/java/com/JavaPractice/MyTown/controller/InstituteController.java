package com.JavaPractice.MyTown.controller;

import com.JavaPractice.MyTown.model.Discipline;
import com.JavaPractice.MyTown.model.Institute;
import com.JavaPractice.MyTown.model.Specialty;
import com.JavaPractice.MyTown.repos.DisciplineRepos;
import com.JavaPractice.MyTown.repos.InstituteRepos;
import com.JavaPractice.MyTown.repos.SpecialtyRepos;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/institute")
public class InstituteController {
    private final InstituteRepos instituteRepos;

    public InstituteController(InstituteRepos instituteRepos) {
        this.instituteRepos = instituteRepos;
    }

    @GetMapping
    public String all(Model model) {
        Iterable<Institute> institutes = instituteRepos.findAll();
        model.addAttribute("institutes", institutes);
        return "institute";
    }
    @PostMapping("add")
    public String add(@RequestParam String name) {
        Institute institute= new Institute(name);
        instituteRepos.save(institute);
        return "redirect:/institute";
    }
    @PostMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        Institute institute = instituteRepos.findById(id).orElseThrow();
        instituteRepos.delete(institute);
        return "redirect:/institute";
    }
    @GetMapping("{id}")
    public String one(@PathVariable(value = "id") Long id,
                      Model model) {
        Institute institute = instituteRepos.findById(id).orElseThrow();

        model.addAttribute("institute", institute);
        return "institute-edit";
    }
    @PostMapping("{id}")
    public String edit(@PathVariable(value = "id") Long id,
                       @RequestParam String name) {
        Institute institute = instituteRepos.findById(id).orElseThrow();
        institute.setName(name);
        instituteRepos.save(institute);
        return "redirect:/institute";
    }
}
