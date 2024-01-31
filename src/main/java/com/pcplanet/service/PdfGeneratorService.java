package com.pcplanet.service;

import com.pcplanet.entity.Cart;
import com.pcplanet.entity.Product;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Service
public class PdfGeneratorService {
    public void generatePdf(List<Product> productList, OutputStream outputStream) {
        try {
            FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, outputStream);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(getXsltSource());
            Source xmlSource = new StreamSource(new StringReader(getXmlContent(productList)));

            // Transformer le XML en PDF
            transformer.transform(xmlSource, new SAXResult(fop.getDefaultHandler()));
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }

    private Source getXsltSource() throws Exception {
        // Charger le fichier XSLT depuis le chemin d'accès ou la ressource
        // Exemple : return new StreamSource(new File("chemin/vers/votre/fichier.xslt"));
        return new  StreamSource(new File("src\\main\\resources\\XSLT files\\products.xsl"));
    }

    private String getXmlContent(List<Product> productList) {
        // Générer le contenu XML à partir de la liste de produits
        // Vous pouvez utiliser une bibliothèque comme JAXB ou construire manuellement le XML
        // Exemple simple (à adapter en fonction de votre structure) :
        StringBuilder xmlContent = new StringBuilder("<products>");
        for (Product product : productList) {
            xmlContent.append("<product>")
                    .append("<product_name>").append(product.getProduct_name()).append("</product_name>")
                    .append("<image>").append(product.getImage()).append("</image>")
                    .append("<pr_description>").append(product.getPr_description()).append("</pr_description>")
                    .append("<price>").append(product.getPrice()).append("</price>")
                    .append("<qte_stock>").append(product.getQte_stock()).append("</qte_stock>")
                    .append("</product>");
        }
        xmlContent.append("</products>");

        return xmlContent.toString();
    }


    public String generateXmlContent(Cart cart) {
        StringBuilder xmlContent = new StringBuilder("<cart>");

        if (cart != null) {
            // Ajouter des informations sur l'utilisateur
            xmlContent.append("<user>")
                    .append("<user_id>").append(cart.getUser().getId()).append("</user_id>")
                    .append("<username>").append(cart.getUser().getUsername()).append("</username>")
                    .append("</user>");
            // Ajouter des informations sur les produits dans le panier
            xmlContent.append("<products>");
            for (Product product : cart.getProducts()) {
                xmlContent.append("<product>")
                        .append("<product_id>").append(product.getId_product()).append("</product_id>")
                        .append("<product_name>").append(product.getProduct_name()).append("</product_name>")
                        .append("<price>").append(product.getPrice()).append("</price>")
                        .append("</product>");
            }
            xmlContent.append("</products>");
        }

        xmlContent.append("</cart>");

        return xmlContent.toString();
    }


//    public void generatePdf(HttpServletResponse response, Cart cart) {
//        try {
//            // Générer le contenu XML à partir du panier
//            String xmlContent = generateXmlContent(cart);
//
//            // Transformer le XML en PDF en utilisant XSLT
//            String xsltFilePath = "C:\\Users\\lenovo\\Documents\\facture.xsl";  // Remplacez par le chemin réel du fichier XSLT
//            TransformerFactory factory = TransformerFactory.newInstance();
//            Transformer transformer = factory.newTransformer(new StreamSource(xsltFilePath));
//
//            // Appliquer la transformation XSLT
//            StringReader reader = new StringReader(xmlContent);
//            transformer.transform(new StreamSource(reader), new StreamResult(response.getOutputStream()));
//
//            // Configurer la réponse HTTP pour le contenu PDF
//            response.setContentType("application/pdf");
//            response.setHeader("Content-Disposition", "inline; filename=invoice.pdf");
//        } catch (Exception e) {
//            e.printStackTrace(); // Gérer l'exception correctement dans votre application
//        }
    //}
public void generatePdf(HttpServletResponse response, Cart cart) {
    try {
        // Générer le contenu XML à partir du panier
        String xmlContent = generateXmlContent(cart);

        // Transformer le XML en PDF en utilisant XSLT
        String xsltFilePath = "src\\main\\resources\\XSLT files\\facture.xsl";  // Remplacez par le chemin réel du fichier XSLT
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(xsltFilePath));

        // Debugging: Print XML content before transformation
        System.out.println("XML Content:\n" + xmlContent);

        // Appliquer la transformation XSLT
        StringReader reader = new StringReader(xmlContent);

        // Capture the PDF content into a ByteArrayOutputStream
        ByteArrayOutputStream pdfOutput = new ByteArrayOutputStream();
        transformer.transform(new StreamSource(reader), new StreamResult(pdfOutput));

        // Convert the ByteArrayOutputStream to a byte array
        byte[] pdfBytes = pdfOutput.toByteArray();

        // Convert the byte array to a string for debugging purposes
        String pdfContent = new String(pdfBytes, StandardCharsets.UTF_8);

        // Debugging: Print PDF content after transformation
        System.out.println("PDF Content:\n" + pdfContent);

        // Write the byte array to the response output stream
        response.getOutputStream().write(pdfBytes);

        // Configurer la réponse HTTP pour le contenu PDF

    } catch (Exception e) {
        e.printStackTrace(); // Gérer l'exception correctement dans votre application
    }
}




}
