package com.pcplanet.service;

import com.pcplanet.entity.Product;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;

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
        return new  StreamSource(new File("C:\\Users\\lenovo\\Desktop\\XML\\products.xsl"));
    }

    private String getXmlContent(List<Product> productList) {
        // Générer le contenu XML à partir de la liste de produits
        // Vous pouvez utiliser une bibliothèque comme JAXB ou construire manuellement le XML
        // Exemple simple (à adapter en fonction de votre structure) :
        StringBuilder xmlContent = new StringBuilder("<products>");
        for (Product product : productList) {
            xmlContent.append("<product>")

                    .append("<product_name>").append(product.getProductName()).append("</product_name>")
                    .append("<image>").append(product.getImage()).append("</image>")
                    .append("<pr_description>").append(product.getPr_description()).append("</pr_description>")
                    .append("<qte_stock>").append(product.getQte_stock()).append("</qte_stock>")
                    .append("</product>");
        }
        xmlContent.append("</products>");

        return xmlContent.toString();
    }


}
