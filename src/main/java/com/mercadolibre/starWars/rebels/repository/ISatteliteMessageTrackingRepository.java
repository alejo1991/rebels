package com.mercadolibre.starWars.rebels.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mercadolibre.starWars.rebels.domain.SatteliteMessageTracking;

@Repository
public interface ISatteliteMessageTrackingRepository extends CrudRepository<SatteliteMessageTracking, Long> {

}
