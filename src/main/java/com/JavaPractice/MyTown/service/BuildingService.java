package com.JavaPractice.MyTown.service;


import com.JavaPractice.MyTown.Repository.IBuildingRepository;
import com.JavaPractice.MyTown.model.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService implements IBuildingService {
    private final IBuildingRepository iBuildingRepository;
    @Autowired
    public BuildingService(@Qualifier("buildingRepository") IBuildingRepository iBuildingRepository){this.iBuildingRepository = iBuildingRepository;}

    @Override
    public List<Building> getAllBuildings(){ return iBuildingRepository.getAllBuildings();}
    @Override
    public Building getBuildingByID(long id){ return iBuildingRepository.getBuildingById(id);}

    @Override
    public Building createBuilding(Building building) {return iBuildingRepository.saveBuilding(building);}
    @Override
    public Building updateBuilding(Building building) {return iBuildingRepository.updateBuilding(building);}
    @Override
    public void deleteBuilding(long id) {iBuildingRepository.deleteBuilding(id);}

}
