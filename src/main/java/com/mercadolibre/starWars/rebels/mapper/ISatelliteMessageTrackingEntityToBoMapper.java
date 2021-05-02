package com.mercadolibre.starWars.rebels.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.mercadolibre.starWars.rebels.domain.SatelliteMessageTracking;
import com.mercadolibre.starWars.rebels.domain.bo.SatelliteMessageTrackingBO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, 
uses = {
		ISatellitePositionHistoryEntityToBoMapper.class
}) 
public interface ISatelliteMessageTrackingEntityToBoMapper {
	
	SatelliteMessageTrackingBO fromEntityToBO(SatelliteMessageTracking request);
	
	List<SatelliteMessageTrackingBO> fromEntityListToBOList(List<SatelliteMessageTracking> request);

}
