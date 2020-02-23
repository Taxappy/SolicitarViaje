package com.taxappy.viaje.model;

import javax.persistence.Id;


import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UsoTaxi {
	@Id
	private int codigo;
	
	private int idTaxista;
	
	private String placa;
	
	private String fechaUso;

}
