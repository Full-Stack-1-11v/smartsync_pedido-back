package cl.ecomarket.pedido.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ecomarket.pedido.dto.ProductoDto;
import cl.ecomarket.pedido.service.ProductoServiceDto;

@RestController
@RequestMapping("/api/v1/ecomarket/producto")
public class ProductoDtoController {
    
    @Autowired
    private ProductoServiceDto productoServiceDto;
    @GetMapping()
        public List<ProductoDto> listarProductos() {
            List<ProductoDto> productos = productoServiceDto.listaList();
            if (productos == null) {
                return new ArrayList<>();
            }
            return productos;
        }

    
}
