package cl.ecomarket.pedido.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import cl.ecomarket.pedido.dto.ProductoDto;

/**
 * Clase para comunicación con servicio de productos
 */
@FeignClient(name = "producto-api", url = "https://smartsync-producto-back.onrender.com/")
public interface ProductoFeingInterfaz {

    /**
     * Metodo para obtener el listado de productos
     * @return listado de {@link ProductoDto}
     */
    @GetMapping("/api/v1/ecomarket/producto")
    List<ProductoDto> listarProductos();
}
