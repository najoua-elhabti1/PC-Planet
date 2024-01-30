package com.pcplanet.controller;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.*;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import net.sf.saxon.s9api.*;
import com.pcplanet.entity.Cart;
import com.pcplanet.entity.Category;
import com.pcplanet.entity.Product;
import com.pcplanet.service.CategoryService;
import com.pcplanet.service.ChartService;
import com.pcplanet.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ChartService cartService;
    @Autowired
    private CategoryService categoryService;
//    @GetMapping("/filterByPrice")
//    public List<Product> filterProductsByPrice(@RequestParam double minPrice, @RequestParam double maxPrice) {
//        return productService.filterProductsByPrice(minPrice, maxPrice);
//    }
    @GetMapping("/product/{id}")
    public String showProductDetail(@PathVariable Integer id, Model model) {
        // Fetch the product from the database
        Product product = productService.getProductById(id);
        long cartItemCount = cartService.getCartItemCount();
        model.addAttribute("cartItemCount", cartItemCount);
        // Add the product to the model
        model.addAttribute("productDetail", product);

        // Return the product detail view
        return "productDetails";
    }
//    @GetMapping("/products/{categoryId}")
//    public String showProductsByCategory(@PathVariable String categoryId, Model model) {
//        Optional<Category> category = categoryService.getCategoryById(categoryId);
//        if (category.isPresent()) {
//            List<Product> products = productService.getProductsByCategory(category);
//            model.addAttribute("category", category.get());
//            model.addAttribute("products", products);
//        }
//        return "Products"; // Ceci est le nom de votre fichier HTML (products-by-category.html)
//    }
    // ProductController.java

    @GetMapping("/products/{categoryId}")
    public String getProductsByCategory(@PathVariable String categoryId, Model model) {
        // Load XML data from file or another source
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Data/Products.xml");
        Set<Product> products = new HashSet<>();

        try {
            // Parse XML document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputStream);

            // Use XPath to select products based on category ID
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            String expression = "//product[category/id_category='" + categoryId + "']";
            XPathExpression expr = xpath.compile(expression);
            NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

            // Process the selected products
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element productElement = (Element) nodeList.item(i);
                String productName = productElement.getElementsByTagName("product_name").item(0).getTextContent();
                String description = productElement.getElementsByTagName("pr_description").item(0).getTextContent();
                // Extract other product details as needed

                // Create Product object and add to the list
                Product product = new Product(productName, description);
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }

        // Add the processed products to the model
        model.addAttribute("products", products);

        // Return the Thymeleaf template name
        return "Products";
    }



//    @GetMapping("/products/{categoryId}")
//    public String getProductsByCategory(@PathVariable String categoryId, Model model) throws SaxonApiException {
//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Data/Products.xml");
//        StreamSource xmlSource = new StreamSource(inputStream);
////        ClassPathResource xmlSource = new ClassPathResource("Data/Products.xml");
//            // Load XML data from file
////            StreamSource xmlSource = new StreamSource("products.xml");
//
//            // Create XQuery processor
//            Processor processor = new Processor(false);
//            XQueryCompiler compiler = processor.newXQueryCompiler();
//
//            // Define the XQuery expression with the correct syntax
//            String xqueryString = "//product[category/id_category='" + categoryId + "']";
//
//            XQueryExecutable executable = compiler.compile(xqueryString);
//            XQueryEvaluator evaluator = executable.load();
//
//            // Set the XML source for evaluation
//            evaluator.setSource(xmlSource);
//
//            // Execute XQuery and return result as string
//            XdmValue result = evaluator.evaluate();
//
//            // Add the result to the model
//            model.addAttribute("products", result);
//
//            // Return the Thymeleaf template name
//            return "productDetails";
//    }
public List<Product> filterProductsByPrice(String category, double minPrice, double maxPrice) throws SaxonApiException{
    List<Product> filteredProducts = productService.filterProductsByPrice(minPrice, maxPrice);
    List<Product> filteredProductsByCategory = new ArrayList<>();
    for (Product product : filteredProducts ) {
        System.out.println(category);
        System.out.println(product.getCategory().getId_category());
        Category cat = product.getCategory();

//        System.out.println(Objects.equals(product.getCategory().getId_category().trim(), category.trim()));
        // Check if the price is within the specified range
        if (Objects.equals(product.getCategory().getId_category().trim(), category.trim())) {
            // Add the product to the filtered list
            filteredProductsByCategory.add(product);
        }


    }

    return filteredProductsByCategory;
}
    @GetMapping("/products/filter/{categoryId}")
    public String filterProductsByPrice(@PathVariable String categoryId, @RequestParam(name = "minPrice", required = false) Double minPrice,
                                        @RequestParam(name = "maxPrice", required = false) Double maxPrice,
                                        Model model) throws SaxonApiException {

        List<Product> filteredProducts = filterProductsByPrice(categoryId,minPrice, maxPrice);
        model.addAttribute("products", filteredProducts);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("category", categoryId);
        return "Products";
    }


}
