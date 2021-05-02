package com.mercadolibre.starWars.rebels.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.mercadolibre.starWars.rebels.domain.SattelitePositionHistory;
import com.mercadolibre.starWars.rebels.domain.bo.SattelitePositionHistoryBO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, 
uses = {}) 
public interface ISattelitePositionHistoryEntityToBoMapper {
	
	SattelitePositionHistoryBO fromEntityToBO(SattelitePositionHistory request);
	
	List<SattelitePositionHistoryBO> fromEntityToBO(List<SattelitePositionHistory> request);

}
