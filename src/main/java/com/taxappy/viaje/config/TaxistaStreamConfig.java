package com.taxappy.viaje.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

import com.taxappy.viaje.stream.TaxistaNotificationStream;




@EnableBinding(TaxistaNotificationStream.class)
public class TaxistaStreamConfig {

}
