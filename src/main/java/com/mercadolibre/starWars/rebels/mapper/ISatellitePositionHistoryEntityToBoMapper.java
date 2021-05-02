package com.mercadolibre.starWars.rebels.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.mercadolibre.starWars.rebels.domain.SatellitePositionHistory;
import com.mercadolibre.starWars.rebels.domain.bo.SatellitePositionHistoryBO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, 
uses = {}) 
public interface ISatellitePositionHistoryEntityToBoMapper {
	
	SatellitePositionHistoryBO fromEntityToBO(SatellitePositionHistory request);
	
	List<SatellitePositionHistoryBO> fromEntityToBO(List<SatellitePositionHistory> request);

}
