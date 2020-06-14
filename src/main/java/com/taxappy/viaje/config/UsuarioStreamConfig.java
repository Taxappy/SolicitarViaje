package com.taxappy.viaje.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

import com.taxappy.viaje.stream.UsuarioNotificationStream;




@EnableBinding(UsuarioNotificationStream.class)
public class UsuarioStreamConfig {

}