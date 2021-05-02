package com.mercadolibre.starWars.rebels.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.mercadolibre.starWars.rebels.domain.Sattelite;
import com.mercadolibre.starWars.rebels.domain.bo.SatteliteBO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, 
uses = {
		ISatteliteMessageTrackingEntityToBoMapper.class,
		ISattelitePositionHistoryEntityToBoMapper.class,
}) 
public interface ISatteliteEntityToBoMapper {
	
	SatteliteBO fromEntityToBO(Sattelite request);
	
	List<SatteliteBO> fromEntityListToBOList(List<Sattelite> request);

}
