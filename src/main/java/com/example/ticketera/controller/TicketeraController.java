package com.example.ticketera.controller;

import com.example.ticketera.service.TicketeraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TicketeraController {

    private final TicketeraService ticketeraService;

    @Autowired
    public TicketeraController(TicketeraService ticketeraService) {
        this.ticketeraService = ticketeraService;
    }

    @GetMapping("/imprimir")
    public String mostrarFormularioImprimir(Model model) {
        return "imprimir";
    }

    @PostMapping("/ticketera/imprimir")
    @ResponseBody
    public String imprimirTicket(@RequestParam String mensaje, @RequestParam String puerto) {
        return ticketeraService.imprimirTicket(mensaje, puerto);
    }

    @GetMapping("/ticketera/puertos")
    @ResponseBody
    public List<String> obtenerPuertos() {
        return ticketeraService.obtenerPuertos();
    }
}
