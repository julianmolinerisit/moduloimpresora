package com.example.ticketera.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SerialPortConfig {

    @Value("${serial.port.name}")
    private String name;

    @Value("${serial.port.baudrate}")
    private int baudrate;

    @Value("${serial.port.databits}")
    private int databits;

    @Value("${serial.port.stopbits}")
    private int stopbits;

    @Value("${serial.port.parity}")
    private int parity; // Añadido el campo para paridad

    // Getters para cada propiedad

    public String getName() {
        return name;
    }

    public int getBaudrate() {
        return baudrate;
    }

    public int getDatabits() {
        return databits;
    }

    public int getStopbits() {
        return stopbits;
    }

    public int getParity() { // Método para obtener paridad
        return parity;
    }
}
