package com.mercadolibre.starWars.rebels.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mercadolibre.starWars.rebels.domain.Sattelite;

@Repository
public interface ISatteliteRepository extends CrudRepository<Sattelite, Long> {
	
	public static final String PARAM_NAME = "name";
	
	Optional<Sattelite> findByName(String name);
	
	@Query(value = "select Sattelite s "
			+ " join s.positionHistoryList p "
			+ " where s.name = :" + PARAM_NAME 
			+ " and p.id in ("
			+ "		select max(p1.id) "
			+ "		from SattelitePositionHistory p1 "
			+ "		where p1.sattelite.name = :" + PARAM_NAME + ")")
	Optional<Sattelite> getSatteliteWithLatestPosition(@Param(PARAM_NAME) String name);

}
