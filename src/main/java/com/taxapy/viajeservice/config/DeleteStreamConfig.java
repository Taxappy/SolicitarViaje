package com.taxapy.viajeservice.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

import com.taxapy.viajeservice.stream.NotificationDeleteStream;


@EnableBinding(NotificationDeleteStream.class)
public class DeleteStreamConfig {

}
