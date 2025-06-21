package cl.ecomarket.pedido.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import cl.ecomarket.pedido.client.ProductoFeingIntefaz;
import cl.ecomarket.pedido.dto.ProductoDto;

@SpringBootTest
@ActiveProfiles("test")
class ProductoServiceDtoTest {

    @Mock
    private ProductoFeingIntefaz productoFeingIntefaz;

    @InjectMocks 
    // ProductoServiceDto es la clase que estamos probando, 
    // y queremos inyectar el mock de ProductoFeingIntefaz en ella.
    // Esto permite que ProductoServiceDto use el mock de ProductoFeingIntefaz en lugar de una implementación real.
    private ProductoServiceDto productoServiceDto;


    // Inicializa los mocks antes de cada prueba, para evitar la repetición de código
    // y asegurar que cada prueba comience con un estado limpio.
    @BeforeEach
    
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListaList() {
        // Given
        ProductoDto producto1 = new ProductoDto();
        ProductoDto producto2 = new ProductoDto();
        List<ProductoDto> mockProductos = Arrays.asList(producto1, producto2);

        when(productoFeingIntefaz.listarProductos()).thenReturn(mockProductos);

        // When
        List<ProductoDto> result = productoServiceDto.listaList();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(productoFeingIntefaz, times(1)).listarProductos();
    }
}
