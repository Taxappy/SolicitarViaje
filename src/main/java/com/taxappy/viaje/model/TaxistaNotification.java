package com.taxappy.viaje.model;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaxistaNotification implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UUID id = UUID.randomUUID();
	private String latitud;
	private String longitud;
	private String idUsuario;

}
