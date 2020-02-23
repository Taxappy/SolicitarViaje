package com.taxappy.viaje.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class UserNotification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String placa;
	private int idTaxista;
	private String nombreTaxista;

}
