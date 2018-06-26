package org.launchcode.maximo.models.data;

import org.launchcode.maximo.models.WorkRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRequestDao extends CrudRepository<WorkRequest, Integer> {
}
