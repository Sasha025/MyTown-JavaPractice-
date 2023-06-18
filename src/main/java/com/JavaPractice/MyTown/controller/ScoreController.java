package com.JavaPractice.MyTown.controller;

import com.JavaPractice.MyTown.model.Applicant;
import com.JavaPractice.MyTown.model.Discipline;
import com.JavaPractice.MyTown.model.Score;
import com.JavaPractice.MyTown.model.Specialty;
import com.JavaPractice.MyTown.repos.ApplicantRepos;
import com.JavaPractice.MyTown.repos.DisciplineRepos;
import com.JavaPractice.MyTown.repos.ScoreRepos;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/score")
public class ScoreController {
    private final ScoreRepos scoreRepos;
    private final ApplicantRepos applicantRepos;
    private final DisciplineRepos disciplineRepos;

    public ScoreController(ScoreRepos scoreRepos, ApplicantRepos applicantRepos, DisciplineRepos disciplineRepos) {
        this.scoreRepos = scoreRepos;
        this.applicantRepos = applicantRepos;
        this.disciplineRepos = disciplineRepos;
    }

    @GetMapping
    public String all(Model model) {
        Iterable<Score> scores = scoreRepos.findAll();
        Iterable<Applicant> applicants = applicantRepos.findAll();
        Iterable<Discipline> disciplines = disciplineRepos.findAll();

        model.addAttribute("scores", scores);
        model.addAttribute("disciplines", disciplines);
        model.addAttribute("applicants", applicants);
        return "score";
    }
    @PostMapping("add")
    public String add(@RequestParam Discipline discipline,
                      @RequestParam Applicant applicant,
                      @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,
                      @RequestParam int mark) {
        Score score = new Score(applicant, discipline, date, mark);
        scoreRepos.save(score);
        return "redirect:/score";
    }
    @PostMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        Score score = scoreRepos.findById(id).orElseThrow();
        scoreRepos.delete(score);
        return "redirect:/score";
    }
    @GetMapping("{id}")
    public String one(@PathVariable(value = "id") Long id,
                      Model model) {
        Score score = scoreRepos.findById(id).orElseThrow();
        Iterable<Applicant> applicants = applicantRepos.findAll();
        Iterable<Discipline> disciplines = disciplineRepos.findAll();

        model.addAttribute("score", score);
        model.addAttribute("applicants", applicants);
        model.addAttribute("disciplines", disciplines);
        return "score-edit";
    }
    @PostMapping("{id}")
    public String edit(@RequestParam Applicant applicant,
                       @RequestParam Discipline discipline,
                       @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,
                       @RequestParam int mark,
                       @PathVariable(value = "id") Long id) {
        Score score = scoreRepos.findById(id).orElseThrow();
        score.setApplicant(applicant);
        score.setDiscipline(discipline);
        score.setDate(date);
        score.setMark(mark);

        scoreRepos.save(score);
        return "redirect:/score";
    }
}
