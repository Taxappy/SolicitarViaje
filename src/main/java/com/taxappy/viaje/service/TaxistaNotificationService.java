package com.taxappy.viaje.service;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.taxappy.viaje.model.TaxistaNotification;
import com.taxappy.viaje.stream.TaxistaNotificationStream;



@Service
public class TaxistaNotificationService {

	private final TaxistaNotificationStream taxistaNotificationStream;


	public TaxistaNotificationService(TaxistaNotificationStream notificationSaveStreams) {
		super();
		this.taxistaNotificationStream = notificationSaveStreams;
	}

	public void sendNotification(final TaxistaNotification notificacion) {
		MessageChannel messageChannel = taxistaNotificationStream.notifyTo();
		messageChannel.send(MessageBuilder.withPayload(notificacion)
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
	}
}

