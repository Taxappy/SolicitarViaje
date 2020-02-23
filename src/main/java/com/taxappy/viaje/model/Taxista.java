package com.taxappy.viaje.model;


import javax.persistence.Id;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Taxista {
	@Id
	private int idTaxista;

	private String nombre;
}
