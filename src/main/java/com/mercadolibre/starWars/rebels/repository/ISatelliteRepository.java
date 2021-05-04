package com.mercadolibre.starWars.rebels.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mercadolibre.starWars.rebels.domain.Satellite;

@Repository
public interface ISatelliteRepository extends CrudRepository<Satellite, Long> {
	
	public static final String PARAM_NAME = "name";
	
	Optional<Satellite> findByName(String name);
	
	@Query(value = "select s "
			+ " from Satellite s "
			+ " join s.positionHistoryList p "
			+ " where s.name = :" + PARAM_NAME 
			+ " and p.id in ("
			+ "		select max(p1.id) "
			+ "		from SatellitePositionHistory p1 "
			+ "		where p1.satellite.name = :" + PARAM_NAME + ")")
	Optional<Satellite> getSatelliteWithLatestPosition(@Param(PARAM_NAME) String name);
	
	@Query(value = "select s "
			+ " from Satellite s "
			+ " join s.positionHistoryList p "
			+ " left join s.messageTrackingList m "
			+ " where s.name = :" + PARAM_NAME 
			+ " and p.id in ("
			+ "		select max(p1.id) "
			+ "		from SatellitePositionHistory p1 "
			+ "		where p1.satellite.name = :" + PARAM_NAME + ")"
			+ " or m.id in ("
			+ "		select max(m1.id) "
			+ "		from SatelliteMessageTracking m1 "
			+ "		where m1.satellite.name = :" + PARAM_NAME + ")")
	Optional<Satellite> getSatelliteLatestPositionAndMessage(@Param(PARAM_NAME) String name);

}
