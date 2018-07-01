package org.launchcode.maximo.models.data;

import org.launchcode.maximo.models.WorkRequest;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface WorkRequestDao extends CrudRepository<WorkRequest, Integer> {
    @Override
    ArrayList<WorkRequest> findAll();
}
