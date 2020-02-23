package com.taxappy.viaje.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taxappy.viaje.model.Notification;
import com.taxappy.viaje.model.Viaje;
import com.taxappy.viaje.repository.ViajeRepository;
import com.taxappy.viaje.service.NotificationSaveService;



@RestController
@RequestMapping("/")
public class SolicitarViajeController {
	@Autowired
	private ViajeRepository viajeRepositoy;
	@Autowired
	private NotificationSaveService notificacionSaveService;
	

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
