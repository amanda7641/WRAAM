package org.launchcode.maximo.models.data;

import org.launchcode.maximo.models.Building;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingDao extends CrudRepository<Building, Integer> {
}
