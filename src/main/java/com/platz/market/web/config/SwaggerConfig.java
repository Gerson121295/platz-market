package com.platz.market.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration  //Anotacion de Spring
//@EnableSwagger2 //Habilitar Swagger2
@EnableWebMvc
//@EnableSwagger2WebMvc
public class SwaggerConfig {

    @Bean
    public Docket api() { //Un Docket llamado api
        return new Docket(DocumentationType.SWAGGER_2)  //Dentro Docket va el tipo de documentacion
                .select()    //Decirle que queremos que exporte en la documentacion
                .apis(RequestHandlerSelectors.basePackage("com.platz.market.web.controller"))  //Dentro de apis indicamos que solo lo que esta en el paquete controller(se envia el nombre del paquete, esta en un controlador, al inicio) seran expuestos a traves de la documentacion
                .paths(PathSelectors.any())
                .build() //construir la respuesta
                .apiInfo(apiInfo());
        // .apiInfo(apiEndpointInfo()); //llama a funcion que agrega mas info al API
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API de Supermercado")
                .description("Documentación de la API de Supermercado")
                .version("1.0.0")
                .build();
    }
}



    //para agregar mas informacion al API
//    private ApiInfo apiEndpointInfo(){
//        return new ApiInfoBuilder().title("API de Productos")
//                .description("Servicio para la consulta de productos de un supermercado")
//                .license("Apache 2.0")
//                .version("1.0.0")
//                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
//                .build();
//    }










