package com.taxapy.viajeservice.service;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.taxapy.viajeservice.model.Notification;
import com.taxapy.viajeservice.stream.NotificationDeleteStream;
import com.taxapy.viajeservice.stream.NotificationSaveStream;

@Service
public class NotificationSaveService {

	private final NotificationSaveStream notificationSaveStream;
	private final NotificationDeleteStream notificationDeleteStream;

	public NotificationSaveService(NotificationSaveStream notificationSaveStreams, NotificationDeleteStream notificationDeleteStream) {
		super();
		this.notificationSaveStream = notificationSaveStreams;
		this.notificationDeleteStream = notificationDeleteStream;

	}

	public void saveNotification(final Notification notificacion) {
		MessageChannel messageChannel = notificationSaveStream.notifyTo();
		messageChannel.send(MessageBuilder.withPayload(notificacion)
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
	}
	public void deleteNotification(final Notification notificacion) {
		MessageChannel messageChannel = notificationDeleteStream.notifyTo();
		messageChannel.send(MessageBuilder.withPayload(notificacion)
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
	}

}
