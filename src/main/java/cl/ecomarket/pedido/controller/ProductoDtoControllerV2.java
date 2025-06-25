package cl.ecomarket.pedido.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ecomarket.pedido.assemblers.ProductoDTOModelAssembler;
import cl.ecomarket.pedido.dto.ProductoDto;
import cl.ecomarket.pedido.service.ProductoServiceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/ecomarket/producto")
@Tag(name = "Productos", description = "Métodos relacionados con los productos.")
public class ProductoDtoControllerV2 {
    
    @Autowired
    private ProductoServiceDto productoServiceDto;

    @Autowired
    private ProductoDTOModelAssembler productoDTOModelAssembler;

    @GetMapping()
    @Operation(summary = "Listar productos", description = "Obtiene una lista de todos los productos disponibles.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente."),
        @ApiResponse(responseCode = "204", description = "No hay productos para mostrar.")
    })
    public CollectionModel<EntityModel<ProductoDto>> listarProductos() {
        List<ProductoDto> productos = productoServiceDto.listaList();
        if (productos == null) {
            productos = new ArrayList<>();
        }
        List<EntityModel<ProductoDto>> productosModel = productos.stream()
                .map(productoDTOModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                productosModel,
                linkTo(methodOn(ProductoDtoController.class).listarProductos()).withSelfRel()
        );
    }
}

