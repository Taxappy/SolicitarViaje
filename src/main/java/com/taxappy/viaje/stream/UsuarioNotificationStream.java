package com.taxappy.viaje.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface UsuarioNotificationStream {
	String INPUT = "notificationU-in";
	String OUTPUT = "notificationU-out";

	@Input(INPUT)
	SubscribableChannel subscribe();

	@Output(OUTPUT)
	MessageChannel notifyTo();

}