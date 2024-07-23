package com.example.ticketera.service;

import com.example.ticketera.model.TicketModel;
import com.fazecast.jSerialComm.SerialPort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketeraService {

    public String imprimirTicket(String mensaje, String puertoNombre) {
        SerialPort serialPort = SerialPort.getCommPort(puertoNombre);

        if (serialPort == null) {
            throw new RuntimeException("Puerto no encontrado: " + puertoNombre);
        }

        try {
            // Configurar el puerto serial
            serialPort.setComPortParameters(
                9600, // Baudrate
                8,    // Databits
                1,    // Stopbits
                SerialPort.NO_PARITY // Parity
            );

            // Abre el puerto serial
            if (!serialPort.openPort()) {
                throw new RuntimeException("No se pudo abrir el puerto: " + puertoNombre);
            }

            // Escribe el ticket en el puerto serial
            writeToPort(serialPort, new TicketModel(mensaje));

            // Cierra el puerto serial
            serialPort.closePort();

            return "Ticket impreso correctamente.";
        } catch (Exception e) {
            throw new RuntimeException("Error al imprimir el ticket", e);
        }
    }

    public List<String> obtenerPuertos() {
        List<String> puertos = new ArrayList<>();
        SerialPort[] ports = SerialPort.getCommPorts();
        for (SerialPort port : ports) {
            puertos.add(port.getSystemPortName());
        }
        return puertos;
    }

    private void writeToPort(SerialPort serialPort, TicketModel ticket) throws IOException {
        try (OutputStream outputStream = serialPort.getOutputStream()) {
            byte[] ticketData = ticket.toString().getBytes();
            outputStream.write(ticketData);
            outputStream.flush();
        }
    }
}
