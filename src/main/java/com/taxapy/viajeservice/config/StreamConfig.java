package com.taxapy.viajeservice.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

import com.taxapy.viajeservice.stream.NotificationSaveStream;

@EnableBinding(NotificationSaveStream.class)
public class StreamConfig {

}
