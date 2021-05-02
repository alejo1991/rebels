package com.mercadolibre.starWars.rebels.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.mercadolibre.starWars.rebels.domain.Satellite;
import com.mercadolibre.starWars.rebels.domain.bo.SatelliteBO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, 
uses = {
		ISatelliteMessageTrackingEntityToBoMapper.class,
		ISatellitePositionHistoryEntityToBoMapper.class,
}) 
public interface ISatelliteEntityToBoMapper {
	
	SatelliteBO fromEntityToBO(Satellite request);
	
	List<SatelliteBO> fromEntityListToBOList(List<Satellite> request);

}
