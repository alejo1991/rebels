package com.mercadolibre.starWars.rebels.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "satellite")
public class Satellite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@OneToMany(
			fetch = FetchType.LAZY,
			mappedBy = "satellite",
			cascade = CascadeType.ALL,
			orphanRemoval = true
		)
	private List<SatelliteMessageTracking> messageTrackingList;
	
	@OneToMany(
			fetch = FetchType.LAZY,
			mappedBy = "satellite",
			cascade = CascadeType.ALL,
			orphanRemoval = true
		)
	private List<SatellitePositionHistory> positionHistoryList;

}
