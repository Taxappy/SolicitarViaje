package com.taxapy.viajeservice.respository;

import org.springframework.data.repository.CrudRepository;

import com.taxapy.viajeservice.model.Viaje;


public interface ViajeRepository extends CrudRepository<Viaje, Integer> {
	Iterable<Viaje>  findViajesByIdUsuario(int idUsuario);

}
