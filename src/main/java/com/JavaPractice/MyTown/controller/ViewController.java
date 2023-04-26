package com.JavaPractice.MyTown.controller;

import com.JavaPractice.MyTown.model.Building;
import com.JavaPractice.MyTown.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ViewController {

    private final IBuildingService buildingService;
    @Autowired
    public ViewController (IBuildingService buildingService){
        this.buildingService=buildingService;
    }
    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("list",buildingService.getAllBuildings());
        return "mainPage";
    }
    @GetMapping("/goCreate")
    public String goCreate(){return "CreateBuilding";}
    @PostMapping("/create")
    public String create(Building building){
/*        Building building = new Building(0, "NONE", 0, "NONE", "NONE", "NONE", "NONE", 0);*/
        buildingService.createBuilding(building);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String getOne(@PathVariable("id") Long id, Model model){
        List<Building> arr = new ArrayList<>();
        arr.add(buildingService.getBuildingByID(id));
        model.addAttribute("building", arr);
        return "editBuilding";
    }
    @PostMapping("/edit/{id}")
    public String update(Building building){
/*                         @RequestParam(value = "built", required = false) String built,
                         @RequestParam(value = "creationDate", required = false) String creationDate,
                         @RequestParam(value = "title", required = false) String title,
                         @RequestParam(value = "owner", required = false) String owner,
                         @RequestParam(value = "typeBuilding", required = false) String typeBuilding,
                         @RequestParam(value = "address", required = false) String address,
                         @RequestParam(value = "floors", required = false) String floors) {
        Building building = new Building(
                Long.parseLong(id),
                String.join(built),
                Integer.parseInt(creationDate),
                String.join(title),
                String.join(owner),
                String.join(typeBuilding),
                String.join(address),
                Integer.parseInt(floors));*/

        buildingService.updateBuilding(building);
        return "redirect:/";
    }
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        buildingService.deleteBuilding(id);
        return "redirect:/";
    }
}
