package cl.ecomarket.pedido.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ecomarket.pedido.dto.ProductoDto;
import cl.ecomarket.pedido.service.ProductoServiceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/ecomarket/producto")
@Tag(name = "Productos", description = "Métodos relacionados con los productos.")
public class ProductoDtoController {
    
    @Autowired
    private ProductoServiceDto productoServiceDto;

    @GetMapping()
    @Operation(summary = "Listar productos", description = "Obtiene una lista de todos los productos disponibles.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente."),
        @ApiResponse(responseCode = "204", description = "No hay productos para mostrar.")
    })
    public List<ProductoDto> listarProductos() {
        List<ProductoDto> productos = productoServiceDto.listaList();
        if (productos == null) {
            return new ArrayList<>();
        }
        return productos;
    }
}
