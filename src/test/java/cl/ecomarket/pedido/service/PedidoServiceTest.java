package cl.ecomarket.pedido.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import cl.ecomarket.pedido.model.Pedido;
import cl.ecomarket.pedido.repository.PedidoRepository;

@SpringBootTest
@ActiveProfiles("test")
public class PedidoServiceTest {

    @Autowired
    private PedidoService pedidoService;

    @MockBean
    private PedidoRepository pedidoRepository;

    @Test
    public void testGetPedidos(){

        //Given
        List<Pedido> pedidos = new ArrayList<>();
        
        //When
        when(pedidoRepository.findAll()).thenReturn(pedidos);

        //Then
        List<Pedido> result = pedidoService.findAll();

        assertEquals(pedidos, result);
        assertEquals(0, result.size());
    }

}
