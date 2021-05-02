package com.mercadolibre.starWars.rebels.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "sattelite_message_tracking")
public class SatteliteMessageTracking implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Sattelite sattelite;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private SattelitePositionHistory sattelitePosition;
	
	@Column(name = "message", nullable = false)
	private String splittedMessage;
	
	@Column(name = "distance", nullable = false)
	private Float distance;
	
}
