package com.taxapy.viajeservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="solicitarViaje")
public class Viaje {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int codigoViaje;
	private String idUsuario;
	private int idTaxista;
	private String fechaViaje;
	private String estado;
	private int precio;
	private String longitud;
	private String latitud;
}
