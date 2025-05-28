package cl.ecomarket.pedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ecomarket.pedido.dto.BoletaDto;
import cl.ecomarket.pedido.service.BoletaService;


@RestController
@RequestMapping("/api/v1/boleta")
public class BoletaController {
    
     @Autowired
    private BoletaService boletaService;

    @PostMapping("/guardar")
    public BoletaDto crearBoleta(@RequestBody BoletaDto boletaDto) {
        return boletaService.guardarBoleta(boletaDto);
    }

    @GetMapping("/listar")
    public List<BoletaDto> listarBoletas() {
        return boletaService.listarBoletas();
    }
}
