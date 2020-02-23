package com.taxappy.viaje.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface NotificationDeleteStream {
	String INPUT = "notificationD-in";
	String OUTPUT = "notificationD-out";

	@Input(INPUT)
	SubscribableChannel subscribe();

	@Output(OUTPUT)
	MessageChannel notifyTo();

}
