package com.JavaPractice.MyTown.controller;

import com.JavaPractice.MyTown.model.Building;
import com.JavaPractice.MyTown.service.IBuildingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("buildings")
public class BuildingController {
    private final IBuildingService buildingService;
    @Autowired
    public BuildingController (IBuildingService buildingService){this.buildingService=buildingService;}
    @GetMapping
    public List<Building> list() {return buildingService.getAllBuildings();}
    @GetMapping("{id}")
    public Building getOne(@PathVariable("id") Long id){return buildingService.getBuildingByID(id);}
    @PostMapping
    public Building create(@RequestBody Building building){return buildingService.createBuilding(building);}
    @PutMapping("{id}")
    public Building update(@PathVariable("id") Building buildingFromFile,
                           @RequestBody Building building) {
        BeanUtils.copyProperties(building,buildingFromFile,"id");
        return buildingService.updateBuilding(buildingFromFile);
    }
   @DeleteMapping("{id}")
   public void delete(@PathVariable("id") Building building){buildingService.deleteBuilding(building.getId());}
}