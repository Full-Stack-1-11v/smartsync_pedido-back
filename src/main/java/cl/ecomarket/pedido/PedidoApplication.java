package cl.ecomarket.pedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Clase principal para iniciar la aplicación de pedidos.
 * <p>
 * Esta clase configura y lanza la aplicación Spring Boot.
 * </p>
 */
@SpringBootApplication
@EnableFeignClients
public class PedidoApplication {

    /**
     * Método principal que inicia la aplicación Spring Boot.
     *
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        SpringApplication.run(PedidoApplication.class, args);
    }

    /**
     * Constructor por defecto requerido por Spring Boot.
     */
    public PedidoApplication() {
        // Constructor por defecto
    }
}
