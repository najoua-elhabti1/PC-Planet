package com.pcplanet;

import com.pcplanet.service.ProductService;
import io.github.classgraph.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.core.io.ClassPathResource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/*
package com.pcplanet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PcPlanetApplication {

    public static void main(String[] args) {
        SpringApplication.run(PcPlanetApplication.class, args);
    }

}
*/
@SpringBootApplication
public class PcPlanetApplication  {

    @Autowired
    private ProductService productService;

    public static void main(String[] args) {
        SpringApplication.run(PcPlanetApplication.class, args);
    }

  /*  @Override
    public void run(String... args) throws Exception {
        // Chargez le fichier XML depuis la ressource du projet
        String xmlData = loadXMLFromResource("Data/Products.xml");
        productService.validateXMLAgainstXSD(xmlData);
        // Enregistrez automatiquement les produits à partir du fichier XML
        productService.registerProductsFromXML(xmlData);
    }*/

   /* private String loadXMLFromResource(String resourcePath) throws IOException {
        ClassPathResource resource = new ClassPathResource(resourcePath);
        try (InputStream inputStream = resource.getInputStream()) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }*/
}

