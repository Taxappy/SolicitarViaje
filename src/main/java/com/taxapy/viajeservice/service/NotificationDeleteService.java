package com.taxapy.viajeservice.service;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.taxapy.viajeservice.model.Notification;
import com.taxapy.viajeservice.stream.NotificationDeleteStream;

@Service
public class NotificationDeleteService {


	private final NotificationDeleteStream notificationDeleteStream;

	public NotificationDeleteService(NotificationDeleteStream notificationDeleteStream) {
		super();
		this.notificationDeleteStream = notificationDeleteStream;

	}

	public void deleteNotification(final Notification notificacion) {
		MessageChannel messageChannel = notificationDeleteStream.notifyTo();
		messageChannel.send(MessageBuilder.withPayload(notificacion)
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
	}


}
