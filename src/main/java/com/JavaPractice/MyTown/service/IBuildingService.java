package com.JavaPractice.MyTown.service;

import com.JavaPractice.MyTown.model.Building;

import java.util.List;

public interface IBuildingService {
    List<Building> getAllBuildings();
    Building getBuildingByID(long id);
    Building createBuilding(Building building);
    Building updateBuilding(Building building);
    void deleteBuilding(long id);
}
