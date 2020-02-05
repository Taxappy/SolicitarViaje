package com.taxapy.viajeservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.taxapy.viajeservice.model.Taxista;
import com.taxapy.viajeservice.model.UsoTaxi;

@FeignClient(name = "taxapy-taxista-server")
public interface TaxistaService {
	@GetMapping("/taxista/{id}")
	public Taxista findByidTaxista(@PathVariable final int idTaxista);

	@GetMapping("/taxista/usotaxi/{idTaxista}")
	public UsoTaxi findByIdTaxistaAndPlaca(@PathVariable int idTaxista, @RequestParam String placa);

	@GetMapping("/taxista/usotaxi/{idTaxista}")
	public UsoTaxi findByIdTaxistaAndFechaUso(@PathVariable int idTaxista, @RequestParam String fechaUso);

}
