package com.taxapy.viajeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taxapy.viajeservice.model.Notification;
import com.taxapy.viajeservice.model.Taxista;
import com.taxapy.viajeservice.model.UserNotification;
import com.taxapy.viajeservice.model.UsoTaxi;
import com.taxapy.viajeservice.model.Viaje;
import com.taxapy.viajeservice.respository.ViajeRepository;
import com.taxapy.viajeservice.service.NotificationDeleteService;
import com.taxapy.viajeservice.service.NotificationSaveService;
import com.taxapy.viajeservice.service.TaxistaService;

@RestController
@RequestMapping("/")
public class SolicitarViajeController {
	@Autowired
	private ViajeRepository viajeRepositoy;
	@Autowired
	private NotificationSaveService notificacionSaveService;
	@Autowired
	private NotificationDeleteService notificacionDeleteService;

	@PostMapping("/viaje")
	public Viaje saveViaje(@RequestBody Viaje viaje) {
		Notification notificacion = new Notification(viaje.getLatitud(), viaje.getLongitud(),viaje.getIdUsuario());
		notificacionSaveService.saveNotification(notificacion);
		return viajeRepositoy.save(viaje);
	}

	@PatchMapping("/viaje")
	public void UpdateViaje(@RequestBody Viaje viaje) {
		Notification notificacion = new Notification(viaje.getLatitud(), viaje.getLongitud(),viaje.getIdUsuario());
		notificacionSaveService.deleteNotification(notificacion);
		viajeRepositoy.save(viaje);
	}
	
	@GetMapping("/viaje/{idUsuario}")
	public Iterable<Viaje> findByIdUsuario(@PathVariable int idUsuario) {
		return viajeRepositoy.findViajesByIdUsuario(idUsuario);
	}

}
