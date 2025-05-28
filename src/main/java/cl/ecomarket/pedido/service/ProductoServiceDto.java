package cl.ecomarket.pedido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ecomarket.pedido.client.ProductoFeingIntefaz;
import cl.ecomarket.pedido.dto.ProductoDto;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class ProductoServiceDto {

    @Autowired
    private ProductoFeingIntefaz productoFeingIntefaz;
     // Este método busca por el nombre del rol y devuelve una lista de roles
    public List<ProductoDto> listaList() {
        return productoFeingIntefaz.listarProductos();
    }
}
