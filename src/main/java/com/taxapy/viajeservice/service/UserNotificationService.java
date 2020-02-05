package com.taxapy.viajeservice.service;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.taxapy.viajeservice.model.UserNotification;
import com.taxapy.viajeservice.stream.UserNotificationStream;

@Service
public class UserNotificationService {

	private final UserNotificationStream userNotificationStream;

	public UserNotificationService(UserNotificationStream userNotificationStream) {
		super();
		this.userNotificationStream = userNotificationStream;
	}

	public void acceptViaje(final UserNotification userNotification) {
		MessageChannel messageChannel = userNotificationStream.notifyTo();
		messageChannel.send(MessageBuilder.withPayload(userNotification)
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
	}

}
