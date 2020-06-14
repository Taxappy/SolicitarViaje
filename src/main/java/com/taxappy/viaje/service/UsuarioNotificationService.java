package com.taxappy.viaje.service;

import com.taxappy.viaje.model.UsuarioNotification;
import com.taxappy.viaje.stream.UsuarioNotificationStream;


import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;





@Service
public class UsuarioNotificationService {

	private final UsuarioNotificationStream usuarioNotificationStream;


	public UsuarioNotificationService(UsuarioNotificationStream notificationSaveStreams) {
		super();
		this.usuarioNotificationStream = notificationSaveStreams;
	}

	public void sendNotification(final UsuarioNotification notificacion) {
		MessageChannel messageChannel = usuarioNotificationStream.notifyTo();
		messageChannel.send(MessageBuilder.withPayload(notificacion)
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
	}
}