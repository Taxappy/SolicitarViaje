package com.taxappy.viaje.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

import com.taxappy.viaje.stream.NotificationDeleteStream;




@EnableBinding(NotificationDeleteStream.class)
public class DeleteStreamConfig {

}
