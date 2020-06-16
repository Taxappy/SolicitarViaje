package com.taxappy.viaje.controller;

import java.util.ArrayList;
import java.util.List;

import com.taxappy.viaje.model.TaxistaNotification;
import com.taxappy.viaje.model.UsuarioNotification;
import com.taxappy.viaje.model.Viaje;
import com.taxappy.viaje.repository.ViajeRepository;
import com.taxappy.viaje.service.TaxistaNotificationService;
import com.taxappy.viaje.service.UsuarioNotificationService;
import com.taxappy.viaje.stream.TaxistaNotificationStream;
import com.taxappy.viaje.stream.UsuarioNotificationStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin
public class SolicitarViajeController {
	@Autowired
	private ViajeRepository viajeRepositoy;
	@Autowired
	private TaxistaNotificationService taxistaNotificacionService;
	@Autowired
	private UsuarioNotificationService usuarioNotificacionService;

	List<TaxistaNotification> notificacionesTaxistas = new ArrayList<TaxistaNotification>();
	private final SimpMessagingTemplate template;

	@Autowired
	SolicitarViajeController(final SimpMessagingTemplate template) {
		this.template = template;
	}

	@PostMapping("/")
	public Viaje saveTaxista(@RequestBody Viaje viaje) {
		return viajeRepositoy.save(viaje);
	}

	@GetMapping("/usuario/{idUsuario}")
	public Iterable<Viaje> findByIdUsuario(@PathVariable String idUsuario) {
		return viajeRepositoy.findViajesByIdUsuario(idUsuario);
	}
	@GetMapping("/taxista/{idTaxista}")
	public Iterable<Viaje> findByIdTaxista(@PathVariable int idTaxista) {
		return viajeRepositoy.findViajesByIdTaxista(idTaxista);
	}
	
	@GetMapping("/")
	public Iterable<Viaje> allViajes() {
		return viajeRepositoy.findAll();
	}

	@PostMapping("/notificacion/taxista")
	public TaxistaNotification pedirViaje(@RequestBody Viaje viaje) {
		TaxistaNotification notificacion = new TaxistaNotification();
		notificacion.setIdUsuario(viaje.getIdUsuario());
		notificacion.setLatitud(viaje.getLatitud());
		notificacion.setLongitud(viaje.getLongitud());
		taxistaNotificacionService.sendNotification(notificacion);
		return notificacion;

	}

	@DeleteMapping("/notificacion/taxista")
	public TaxistaNotification cancelarViaje(@RequestBody TaxistaNotification notificacion) {
		taxistaNotificacionService.sendNotification(notificacion);
		return notificacion;
	}

	@GetMapping("/notificacion/taxista")
	public List<TaxistaNotification> getNotificacionTaxista() {
		return notificacionesTaxistas;
	}

	@PostMapping("/notificacion/usuario")
	public UsuarioNotification aceptarViaje(@RequestBody UsuarioNotification notificacion) {
		usuarioNotificacionService.sendNotification(notificacion);
		return notificacion;

	}

	@StreamListener(TaxistaNotificationStream.INPUT)
	public void NotificationT(@Payload TaxistaNotification notificacion) {
		if (notificacionesTaxistas.indexOf(notificacion) != -1) {
			notificacionesTaxistas.remove(notificacion);
		} else {
			notificacionesTaxistas.add(notificacion);
		}
		this.template.convertAndSend("/websocket/taxista", notificacionesTaxistas);
	}

	@StreamListener(UsuarioNotificationStream.INPUT)
	public void NotificationU(@Payload UsuarioNotification notificacion) {
		System.out.println(notificacionesTaxistas.removeIf(n -> (String.valueOf(n.getId()).equals(String.valueOf(notificacion.getIdTaxistaNotificacion())))));
		this.template.convertAndSend("/websocket/taxista", notificacionesTaxistas);
		this.template.convertAndSend("/websocket/usuario", notificacion);
	}

}
