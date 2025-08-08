package com.service.bookinghotels.configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Value("${server.port}")
    private int port;

    @Bean
    public OpenAPI openApiDescription() {
        Server localhostServer = new Server();
        localhostServer.setUrl("http://localhost:" + port);
        localhostServer.setDescription("Local env");

        Server productionServer = new Server();
        productionServer.setUrl("https://set.prod.url");
        productionServer.setDescription("Production env");

        Contact contact = new Contact();
        contact.setName("alex-lib");
        contact.setEmail("docode@inbox.ru");

        Info info = new Info()
                .title("Book Service API")
                .version("1.0")
                .description("API for accounting books")
                .contact(contact)
                .license(new License()
                        .name("Apache 2.0")
                        .url("https://www.apache.org/licenses/LICENSE-2.0.html"));

        return new OpenAPI().info(info).servers(List.of(localhostServer, productionServer));
    }
}