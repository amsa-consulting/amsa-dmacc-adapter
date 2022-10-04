package com.amsadmacc.amsadmaccadapter.repo;

import com.amsadmacc.amsadmaccadapter.model.PathwaysJourney;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PathwaysJourneyRepo extends CrudRepository<PathwaysJourney, Integer> {
//public interface PathwaysJourneyRepo extends JpaRepository<PathwaysJourney, Integer> {

}
