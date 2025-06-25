package cl.ecomarket.pedido.assemblers;

import cl.ecomarket.pedido.controller.PedidoControllerV2;
import cl.ecomarket.pedido.model.Pedido;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("unused")
// Se utiliza para evitar advertencias de uso de clases no utilizadas
// en el contexto de pruebas unitarias, ya que algunas clases pueden no ser
// directamente referenciadas en el código de producción, pero son necesarias
// para las pruebas unitarias.
public class PedidoModelAssemblerTest {

    @Test
    void toModel_deberiaRetornarEntityModelConLinks() {
        // Arrange
        Pedido pedido = new Pedido(1L, true, Date.valueOf("2024-06-25"));
        PedidoModelAssembler assembler = new PedidoModelAssembler();

        // Act
        EntityModel<Pedido> entityModel = assembler.toModel(pedido);

        // Assert
        assertThat(entityModel.getContent()).isEqualTo(pedido);

        List<Link> links = entityModel.getLinks().toList();
        assertThat(links).hasSize(5);

        assertThat(links).anyMatch(link -> link.getRel().value().equals("listar"));
        assertThat(links).anyMatch(link -> link.getRel().value().equals("buscar"));
        assertThat(links).anyMatch(link -> link.getRel().value().equals("guardar"));
        assertThat(links).anyMatch(link -> link.getRel().value().equals("actualizar"));
        assertThat(links).anyMatch(link -> link.getRel().value().equals("eliminar"));
    }
}
