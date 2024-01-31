package com.pcplanet.controller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.pcplanet.entity.*;
import com.pcplanet.service.ChartService;
import com.pcplanet.service.PdfGeneratorService;
import com.pcplanet.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;

import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller

public class CartController {
    @Autowired
    private PdfGeneratorService pdfService;

    @Autowired
    private ChartService cartService;
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;


//
//    @PostMapping("/add/{productId}")
//    @ResponseBody
//    public ModelAndView addToCart(@PathVariable Integer productId) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null || !authentication.isAuthenticated()) {
//
//            // L'utilisateur n'est pas authentifié, rediriger vers la page de connexion
//            return new ModelAndView("redirect:/login");
//        }
//       // Ajout de cette ligne
//
//
//        Integer userId = userService.getUserIdFromAuthentication(authentication);
//        cartService.addToCart(productId, userId);
//
//        // Utilisez String.format pour construire l'URL avec la valeur de productId
//        String redirectUrl = String.format("redirect:/productDetails/%d", productId);
//        return new ModelAndView(redirectUrl);
//    }

//    private Integer getUserIdFromAuthentication(Authentication authentication) {
//        // Check if the principal is an instance of YourUserDetails
//        if (authentication.getPrincipal() instanceof UserDetails) {
//            // Cast the principal to YourUserDetails and retrieve the ID
//            return ((UserDetails) authentication.getPrincipal()).getId();
//        }

    //        // If not an instance of YourUserDetails, handle accordingly
//        return null; // or throw an exception, depending on your requirements
//    }
/*    @GetMapping("/productDetails")
    @ResponseBody
    public long getCartItemCount() {
        return cartService.getCartItemCount();

 */
    @GetMapping("/view")
    public String viewCart(Model model, @RequestParam Integer userId) {
        // Récupérer le panier associé à l'utilisateur par son id_user
        Optional<Cart> cartOptional = cartService.getCartByUserId(userId);
        Cart cart = cartOptional.orElse(null);

        // Ajouter la liste des produits au modèle
        model.addAttribute("products", (cart != null) ? cart.getProducts() : new ArrayList<Product>());
        model.addAttribute("totalPrice", (cart != null) ? cartService.calculateTotalPrice(cart) : 0.0);
        // Afficher la vue du panier
        return "cart-view";
    }

    private String transformXmlToFo(String xmlContent, String xslFilePath) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(xslFilePath));
        ByteArrayOutputStream foOutput = new ByteArrayOutputStream();
        transformer.transform(new StreamSource(new ByteArrayInputStream(xmlContent.getBytes(StandardCharsets.UTF_8))),
                new StreamResult(foOutput));
        return foOutput.toString(StandardCharsets.UTF_8.name());
    }
    // Autres méthodes du contrôleur
    private byte[] convertFoToPdf(String foContent) throws Exception {
        ByteArrayOutputStream pdfOutput = new ByteArrayOutputStream();
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, pdfOutput);
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        Source src = new StreamSource(new ByteArrayInputStream(foContent.getBytes(StandardCharsets.UTF_8)));
        Result res = new SAXResult(fop.getDefaultHandler());
        transformer.transform(src, res);
        return pdfOutput.toByteArray();
    }
    @GetMapping("/generate")
    public void generatePdf(@RequestParam Integer cartId, HttpServletResponse response) {
        try {
            String xmlContent = pdfService.generateXmlContent(cartService.getCartById(cartId));

            // Transformer le XML en PDF en utilisant XSL-FO
            String xslFilePath = "src/main/resources/XSLT files/facture.xsl"; // Chemin vers le fichier XSL-FO
            String foContent = transformXmlToFo(xmlContent, xslFilePath);

            // Convertir le contenu FO en PDF
            byte[] pdfBytes = convertFoToPdf(foContent);

            // Écrire les bytes du PDF dans le flux de sortie de la réponse HTTP
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=invoice.pdf");
            response.getOutputStream().write(pdfBytes);
            // Récupérer le panier à partir de la base de données ou du service approprié
//            Cart cart = cartService.getCartById(cartId);
//
//            // Configurer la réponse HTTP pour le contenu PDF
//            response.setContentType("application/pdf");
////
//            response.setHeader("Content-Disposition", "attachment; filename=invoice.pdf");
//
//
//            // Générer le PDF et l'envoyer à la réponse HTTP
//            pdfService.generatePdf(response, cart);
//
//            // Flush le tampon de la réponse
//            response.flushBuffer();


        } catch (Exception e) {
            System.out.println("je suis la");
            e.printStackTrace(); // Gérer l'exception correctement dans votre application
        }
    }



    @PostMapping("/addToCart/{productId}")
    public String addToCart(
            @PathVariable Integer productId,
            @RequestParam int quantity,
            @RequestParam Integer userId,
            Model model
    ) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Product> productOptional = productRepository.findById(productId);

        if (userOptional.isPresent() && productOptional.isPresent()) {
            User user = userOptional.get();
            Product product = productOptional.get();
            cartService.addToCart(user, product, quantity);
            System.out.println(product);
            model.addAttribute("successMessage", "Product added to cart successfully.");
        } else {
            model.addAttribute("errorMessage", "User or product not found.");
        }

        // Réaffichez la page de détails du produit avec les messages appropriés
        return "redirect:/";
    }
    @GetMapping("/payment")
    public String showPaymentPage() {
        return "paiement"; // Cela renvoie le nom de la page HTML (sans extension) à afficher
    }
}
