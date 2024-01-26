package com.pcplanet.service;
import javax.xml.transform.stream.StreamSource;

import com.pcplanet.controller.ProductController;

import net.sf.saxon.s9api.*;
import com.pcplanet.entity.*;
import io.github.classgraph.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import net.sf.saxon.s9api.SaxonApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


import javax.swing.event.ListDataEvent;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.*;
import java.util.function.Function;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;


  /*  @Autowired
    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }*/
    public List<Product> listALl(){
        return (List<Product>) repo.findAll();
    }

   /* public  void registerProduct(Product prd) {

        repo.save(prd);
    }*/

 /*   @Transactional
    public void importProductsFromXmlFile(String xmlFilePath) {
        try {
            // Créer un contexte JAXB pour la classe ProductsType
            JAXBContext jaxbContext = JAXBContext.newInstance(ProductsType.class);

            // Créer un objet Unmarshaller
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            // Désérialiser le fichier XML
            File xmlFile = new File(xmlFilePath);
            ProductsType productsType = (ProductsType) unmarshaller.unmarshal(xmlFile);

            // Enregistrer les produits dans la base de données
            for (ProductType productType : productsType.getProduct()) {
                Product product = new Product();
                product.setId_product(productType.getIdProduct());
                product.setProductName(productType.getProductName());
                // Ajoutez d'autres attributs selon votre modèle
                repo.save(product);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
*/
    public List<Product> listAll() {
        return (List<Product>) repo.findAll();
    }

    public void registerProduct(Product product) {
        repo.save(product);
    }

//    public List<Product> getProductsByCategoryAndPriceRange(String xmlData, Category category, double minPrice, double maxPrice) {
//        try {
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            InputSource inputSource = new InputSource(new StringReader(xmlData));
//            Document document = builder.parse(inputSource);
//
//            XPathFactory xPathFactory = XPathFactory.newInstance();
//            XPath xpath = xPathFactory.newXPath();
//
//            String expression = String.format("/products/product[category/id_category='%s' and price>=%f and price<=%f]", category.getId_category(), minPrice, maxPrice);
//            XPathExpression xPathExpression = xpath.compile(expression);
//
//            // Evaluate the XPath expression
//            NodeList nodeList = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);
//
//            // Convert NodeList to a list of products
//            List<Product> products = new ArrayList<>();
//            for (int i = 0; i < nodeList.getLength(); i++) {
//                Node node = nodeList.item(i);
//                Product product = new Product();
//                product.setProductName(xpath.evaluate("productName", node));
//
//                // Constructing the Category object
//                Category productCategory = new Category();
//                productCategory.setId_category(Integer.parseInt(xpath.evaluate("category/id_category", node)));
//                productCategory.setDescription(xpath.evaluate("category/description", node));
//
//                // Setting the Category object in the Product
//                product.setCategory(productCategory);
//
//                product.setPrice(Double.parseDouble(xpath.evaluate("price", node)));
//                products.add(product);
//            }
//
//            return products;
//
//        } catch (Exception e) {
//            // Handle exceptions
//            return null;
//        }
//
//
//    }




 /*   private List<Product> getProductsFromXML(String xmlData) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(xmlData));
            Document document = builder.parse(inputSource);

            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();

            String expression = "/products/product";
            XPathExpression xPathExpression = xpath.compile(expression);

            // Evaluate the XPath expression
            NodeList nodeList = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);

            // Convert NodeList to a list of products
            List<Product> products = new ArrayList<>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                Product product = new Product();
                product.setProductName(xpath.evaluate("productName", node));

                // Constructing the Category object
                Category productCategory = new Category();
                productCategory.setId_category(Integer.parseInt(xpath.evaluate("category/id_category", node)));
                productCategory.setDescription(xpath.evaluate("category/description", node));

                // Setting the Category object in the Product
                product.setCategory(productCategory);

                product.setPrice(Double.parseDouble(xpath.evaluate("price", node)));
                products.add(product);
            }

            return products;

        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            return Collections.emptyList();
        }
    }*/


/*    public void registerProductsFromXML(String xmlData) {
        List<Product> products = getProductsFromXML(xmlData);
        for (Product product : products) {
            registerProduct(product);
        }
    }*/
//    public void validateXMLAgainstXSD(String xmlData) throws IOException, SAXException {
//        // Chargement du schéma XSD depuis la ressource du projet
//        ClassPathResource xsdResource = new ClassPathResource("Data/Products.xsd");
//        try (InputStream xsdInputStream = xsdResource.getInputStream()) {
//            Source xsdSource;
//            xsdSource = new StreamSource(xsdInputStream);
//
//            // Création de la fabrique de schéma
//            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//            Schema schema = schemaFactory.newSchema(xsdSource);
//
//            // Création du validateur
//            Validator validator = schema.newValidator();
//
//            // Validation du fichier XML
//            validator.validate(new StreamSource(Objects.requireNonNull(new StringReader(xmlData))));
//        }
//    }




//    public List<Product> filterProductsByPrice(double minPrice, double maxPrice) {
//        return repo.findByPriceBetween(minPrice, maxPrice);
//    }
//public List<Product> filterProductsByPrice(double minPrice, double maxPrice) throws SaxonApiException {
//    // Load XML document
//    List<Product> filteredProducts = new ArrayList<>();
//
//    try {
//        Processor processor = new Processor(false);
//        XPathCompiler xpathCompiler = processor.newXPathCompiler();
//        DocumentBuilder builder = processor.newDocumentBuilder();
////        ClassPathResource resource = new ClassPathResource("Data/Products.xml");
//
//        XdmNode doc = builder.build(new StreamSource(new File("src/main/resources/Data/Products.xml")));
//
//        // Define XPath expression to select product elements
//        XPathSelector selector = xpathCompiler.compile("/products/product[price >= " + minPrice + " and price <= " + maxPrice + "]").load();
//        selector.setContextItem(doc);
//
//        // Evaluate XPath expression and return selected product nodes
//        XPathExecutable xpathExec = xpathCompiler.compile(".");
//        for (XdmItem item : selector) {
//            XdmNode productNode = (XdmNode) item;
//            // Extract product attributes directly within the loop
//            String productName = extractString(xpathCompiler, productNode, "product_name");
//            String description = extractString(xpathCompiler, productNode, "pr_description");
//            // Extract other attributes as needed
//
//            // Create Product object with extracted attributes
//            Product product = new Product(productName, description);
//            System.out.println(product);
//            filteredProducts.add(product);
//
//        }
//
//    }catch (SaxonApiException e){
//        System.out.println(e.getMessage());
//    }
//    return filteredProducts;
//}
//
//    // Helper method to extract string value using XPath
//    private String extractString(XPathCompiler xpathCompiler, XdmNode contextNode, String xpathExpression) throws SaxonApiException {
//        XPathSelector selector = xpathCompiler.compile(xpathExpression).load();
//        selector.setContextItem(contextNode);
//        XdmItem item = selector.evaluateSingle();
//        return (item != null) ? item.getStringValue() : null;
//    }
public List<Product> filterProductsByPrice(double minPrice, double maxPrice) throws SaxonApiException {
    List<Product> filteredProducts = new ArrayList<>();
    try {

        Processor processor = new Processor(false);
        XPathCompiler xpathCompiler = processor.newXPathCompiler();
        DocumentBuilder builder = processor.newDocumentBuilder();
        XdmNode doc = builder.build(new StreamSource(new File("src/main/resources/Data/Products.xml")));

        // Define XPath expression to select product elements with price between minPrice and maxPrice
        String xpathExpression = "/Products/product[price >= " + minPrice + " and price <= " + maxPrice + "]";
        XPathSelector selector = xpathCompiler.compile(xpathExpression).load();
        selector.setContextItem(doc);

        // Evaluate XPath expression and return selected product nodes
        for (XdmItem item : selector) {
            XdmNode productNode = (XdmNode) item;
            // Extract product attributes directly using XPath expressions
            String productName = xpathCompiler.evaluateSingle("product_name", productNode).getStringValue();
            String description = xpathCompiler.evaluateSingle("pr_description", productNode).getStringValue();
            String image = xpathCompiler.evaluateSingle("image", productNode).getStringValue();
            String categoryId = xpathCompiler.evaluateSingle("category", productNode).getStringValue();
//            System.out.println(categoryId);
            Category category = new Category(categoryId);
            double price = Double.parseDouble(xpathCompiler.evaluateSingle("price", productNode).getStringValue());
            double qte = Double.parseDouble(xpathCompiler.evaluateSingle("qte_stock", productNode).getStringValue());

            // Create Product object with extracted attributes
            Product product = new Product(productName, description,image, price, qte, category);
            filteredProducts.add(product);

        }
    } catch (SaxonApiException e) {
        System.out.println(e.getMessage());
    }
    return filteredProducts;
}


    public Product getProductById(String id) {

        return repo.findById(Integer.parseInt(id)).orElse(null);
    }
    @Transactional
    public void saveProduct(Product product) {
        repo.save(product);
    }

    public List<Product> getProductsByCategory(Optional<Category> category) {
        // Vérifiez si la catégorie est présente
        if (category.isPresent()) {
            // Si la catégorie est présente, récupérez la liste des produits par catégorie
            return repo.findByCategory(category.get());
        } else {
            // Si la catégorie n'est pas présente, renvoyez tous les produits
            return repo.findAll();
        }
    }






    /*public List<Product> listByCategory(Integer categoryId){
     return repo.findById_product(categoryId);
    }*/

}


