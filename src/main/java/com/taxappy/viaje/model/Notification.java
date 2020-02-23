package com.taxappy.viaje.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Notification implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String latitud;
	private String longitud;
	private String idUsuario;
}
