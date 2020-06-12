package com.taxappy.viaje.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import com.taxappy.viaje.model.TaxistaNotification;
import com.taxappy.viaje.model.Viaje;
import com.taxappy.viaje.repository.ViajeRepository;
import com.taxappy.viaje.service.TaxistaNotificationService;
import com.taxappy.viaje.stream.TaxistaNotificationStream;



@RestController
@RequestMapping("/")
public class SolicitarViajeController {
	@Autowired
	private ViajeRepository viajeRepositoy;
	@Autowired
	private TaxistaNotificationService notificacionSaveService;
	private ArrayList <TaxistaNotification> TaxistaNotifications;
	private final SimpMessagingTemplate template;


    @Autowired
    SolicitarViajeController(final SimpMessagingTemplate template) {
        this.template = template;
    }

	@PostMapping("/")
	public Viaje saveViaje(@RequestBody Viaje viaje) {
		TaxistaNotification notificacion = new TaxistaNotification(viaje.getLatitud(), viaje.getLongitud(),viaje.getIdUsuario());
		notificacionSaveService.saveNotification(notificacion);
		return viajeRepositoy.save(viaje);
	}
	@GetMapping("/{idUsuario}")
	public Iterable<Viaje> findByIdUsuario(@PathVariable int idUsuario) {
		return viajeRepositoy.findViajesByIdUsuario(idUsuario);
	}
	@StreamListener(TaxistaNotificationStream.INPUT)
	public void saveNotification(@Payload TaxistaNotification notificacion) {
		TaxistaNotifications.add(notificacion);
		this.template.convertAndSend("/websocket/message",  TaxistaNotifications);
	}

}
