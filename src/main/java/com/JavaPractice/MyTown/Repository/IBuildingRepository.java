package com.JavaPractice.MyTown.Repository;

import com.JavaPractice.MyTown.model.Building;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBuildingRepository {
    Building saveBuilding(Building building);
    void deleteBuilding(Long id);
    List<Building> getAllBuildings();
    Building getBuildingById(Long id);
    Building updateBuilding(Building building);
}
