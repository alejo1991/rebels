package com.mercadolibre.starWars.rebels.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.mercadolibre.starWars.rebels.domain.SatteliteMessageTracking;
import com.mercadolibre.starWars.rebels.domain.bo.SatteliteMessageTrackingBO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, 
uses = {
		ISattelitePositionHistoryEntityToBoMapper.class
}) 
public interface ISatteliteMessageTrackingEntityToBoMapper {
	
	SatteliteMessageTrackingBO fromEntityToBO(SatteliteMessageTracking request);
	
	List<SatteliteMessageTrackingBO> fromEntityListToBOList(List<SatteliteMessageTracking> request);

}
