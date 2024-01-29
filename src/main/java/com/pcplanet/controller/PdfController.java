package com.pcplanet.controller;

import com.pcplanet.entity.Category;
import com.pcplanet.entity.Product;
import com.pcplanet.entity.ProductRepository;
import com.pcplanet.service.PdfGeneratorService;
import com.pcplanet.service.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class PdfController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductController productController;
    @GetMapping("/filter/generatePdf/{categoryId}")
    public void generatePdf(@PathVariable String categoryId, @RequestParam(name = "minPrice", required = false) Double minPrice,
                            @RequestParam(name = "maxPrice", required = false) Double maxPrice,
                            HttpServletResponse response, Model model) {
        try {
            List<Product> productList;
            model.addAttribute("minPrice", minPrice);
            model.addAttribute("maxPrice", maxPrice);
            // Check if minPrice and maxPrice are provided
            if (minPrice != null && maxPrice != null) {

                // Filter products by price range
                productList = productController.filterProductsByPrice(categoryId, minPrice, maxPrice);
            }
            else {
                // If not provided, get all products
                productList = null;
            }

            // Utilize Apache FOP to generate the PDF file (see PdfGeneratorService below)
            PdfGeneratorService pdfGeneratorService = new PdfGeneratorService();
            pdfGeneratorService.generatePdf(productList, response.getOutputStream());

            // Set header parameters to indicate that the response is a PDF file
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=products.pdf");

            response.flushBuffer();
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }
    @GetMapping("/genererPdf/{categoryId}")
    public void generatePdf(@PathVariable String categoryId, HttpServletResponse response) {
        try {
            List<Product> productList = productRepository.findByCategory(new Category(categoryId));

            // Utilisez Apache FOP pour générer le fichier PDF (voir PdfGeneratorService ci-dessous)
            PdfGeneratorService pdfGeneratorService = new PdfGeneratorService();
            pdfGeneratorService.generatePdf(productList, response.getOutputStream());

            // Paramètres de l'en-tête pour indiquer que la réponse est un fichier PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=products.pdf");

            response.flushBuffer();
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }

    @GetMapping("/generatePdf")
    public void generatePdf(HttpServletResponse response) {
        try {
            List<Product> productList = productRepository.findAll();

            // Utilisez Apache FOP pour générer le fichier PDF (voir PdfGeneratorService ci-dessous)
            PdfGeneratorService pdfGeneratorService = new PdfGeneratorService();
            pdfGeneratorService.generatePdf(productList, response.getOutputStream());

            // Paramètres de l'en-tête pour indiquer que la réponse est un fichier PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=products.pdf");

            response.flushBuffer();
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }
    @GetMapping("/generatePdf/{productId}")
    public void generatePdf(@PathVariable Long productId, HttpServletResponse response) {
        try {
            // Utilisez Apache FOP pour générer le fichier PDF pour le produit spécifié
            Product product = productRepository.findById(Math.toIntExact(productId)).orElse(null);

            if (product != null) {
                PdfGeneratorService pdfGeneratorService = new PdfGeneratorService();
                pdfGeneratorService.generatePdf(Collections.singletonList(product), response.getOutputStream());

                // Paramètres de l'en-tête pour indiquer que la réponse est un fichier PDF
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "inline; filename=" + product.getProduct_name() + ".pdf");

                response.flushBuffer();
            } else {
                // Gérer le cas où le produit n'est pas trouvé
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            // Gérer les exceptions
            e.printStackTrace();
        }
    }


}
