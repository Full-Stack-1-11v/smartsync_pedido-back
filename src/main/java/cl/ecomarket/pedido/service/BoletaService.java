package cl.ecomarket.pedido.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.ecomarket.pedido.dto.BoletaDto;
import cl.ecomarket.pedido.model.Pedido;
import jakarta.transaction.Transactional;
@Transactional
@Service
public class BoletaService {

    private final List<BoletaDto> boletas = new ArrayList<>();

    public BoletaDto guardarBoleta(BoletaDto boleta) {
        boletas.add(boleta);
        return boleta;
    }

    public List<BoletaDto> listarBoletas() {
        return boletas;
    }


}


