//package com.pcplanet.service;
//
//import com.pcplanet.entity.Category;
//import com.pcplanet.entity.Product;
//import com.pcplanet.entity.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import java.io.File;
//
//@Service
//public class XmlDataService {
//    @Autowired
//    private ProductRepository productRepository; // Assurez-vous que ProductRepository étend JpaRepository<Product, Long>
//    protected String getTextContent(Element element, String tagName) {
//        NodeList nodeList = element.getElementsByTagName(tagName);
//        if (nodeList != null && nodeList.getLength() > 0) {
//            return nodeList.item(0).getTextContent();
//        }
//        return null; // Ou une valeur par défaut si l'élément n'est pas trouvé
//    }
//    public void storeXmlData(String xmlFilePath) {
//        // Lire le fichier XML et extraire les données
//        // Exemple avec la bibliothèque javax.xml.parsers
//        try {
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document document = builder.parse(new File("C:\\Users\\lenovo\\Desktop\\XML\\products.xml"));
//
//            // Extraire les données du document XML
//            NodeList nodeList = document.getElementsByTagName("product");
//            for (int i = 0; i < nodeList.getLength(); i++) {
//                Node node = nodeList.item(i);
//                Element element;
//                if (node.getNodeType() == Node.ELEMENT_NODE) {
//                    element = (Element) node;
//
//                    // Créer une entité Product et définir ses propriétés en fonction des données extraites
//                    Product product = new Product();
//                    product.setProductName(element.getElementsByTagName("product_name").item(1).getTextContent());
//                    product.setQte_stock(Double.parseDouble(element.getElementsByTagName("qte_stock").item(5).getTextContent()));
//                    product.setPr_description(element.getElementsByTagName("pr_description").item(3).getTextContent());
//                    product.setImage(element.getElementsByTagName("image").item(4).getTextContent());
////                    product.setCategory(element.getElementsByTagName("category").item(5).getTextContent());
//                    // Définir d'autres propriétés en fonction de votre structure XML
//
//                    // Enregistrez le produit dans la base de données
//                    productRepository.save(product);
//                }
//            }
//
//        } catch (Exception e) {
//            // Gérer les exceptions
//        }
//    }
//}
