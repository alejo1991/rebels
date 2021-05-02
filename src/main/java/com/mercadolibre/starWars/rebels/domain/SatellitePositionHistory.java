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
import javax.persistence.ManyToOne;
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
@Table(name = "satellite_position_history")
public class SatellitePositionHistory implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Satellite satellite;
	
	@Column(name = "position_x", nullable = false)
	private Float positionX;
	
	@Column(name = "position_y", nullable = false)
	private Float positionY;
	
	@OneToMany(
			fetch = FetchType.LAZY,
			mappedBy = "satellitePosition",
			cascade = CascadeType.ALL,
			orphanRemoval = true
		)
	private List<SatelliteMessageTracking> messageTrackingList;

}
