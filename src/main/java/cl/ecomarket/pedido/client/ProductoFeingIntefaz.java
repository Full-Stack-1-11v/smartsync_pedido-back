package cl.ecomarket.pedido.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import cl.ecomarket.pedido.dto.ProductoDto;

@FeignClient(name = "producto-api", url = "https://smartsync-producto-back.onrender.com/")
public interface ProductoFeingIntefaz {

    @GetMapping("/api/v1/ecomarket/producto")
    List<ProductoDto> listarProductos();
}
