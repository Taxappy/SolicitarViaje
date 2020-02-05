package com.taxapy.viajeservice.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface UserNotificationStream {
	String INPUT = "userNotification-in";
	String OUTPUT = "userNotification-out";

	@Input(INPUT)
	SubscribableChannel subscribe();

	@Output(OUTPUT)
	MessageChannel notifyTo();
}
