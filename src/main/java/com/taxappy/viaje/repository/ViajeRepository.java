package com.taxappy.viaje.repository;

import org.springframework.data.repository.CrudRepository;

import com.taxappy.viaje.model.Viaje;




public interface ViajeRepository extends CrudRepository<Viaje, Integer> {
	Iterable<Viaje>  findViajesByIdUsuario(int idUsuario);
	Iterable<Viaje>  findViajesByIdTaxista(int idTaxista);

}
