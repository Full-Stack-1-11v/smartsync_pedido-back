package cl.ecomarket.pedido.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * Configuración de Swagger/OpenAPI para la documentación de la API de pedidos.
 * <p>
 * Esta clase configura la documentación interactiva de la API utilizando OpenAPI 3.
 * </p>
 */
@Configuration
public class SwaggerConfig {

    /**
     * Constructor por defecto requerido por Spring.
     */
    public SwaggerConfig() {
        // Constructor por defecto
    }

    /**
     * Bean que configura la instancia principal de OpenAPI para la documentación.
     *
     * @return una instancia personalizada de {@link OpenAPI} con información de la API.
     */
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
        .info(new Info()
                .title("API 2025 Pedidos")
                .version("2.2")
                .description("Documentacion de la API para el sistema de creacion de pedidos."));
    }

}
