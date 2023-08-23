package com.onurgundogdu.sessioncommerce.controller;


import com.onurgundogdu.sessioncommerce.model.Product;
import com.onurgundogdu.sessioncommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private HttpSession httpSession;


    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable Long productId) {

        Product product = productRepository.findById(productId).orElse(null);

        if(product != null){
            List<Product> cartProducts = (List<Product>) httpSession.getAttribute("session");
            if (cartProducts == null) {
                cartProducts = new ArrayList<>();
            }
            cartProducts.add(product);
            httpSession.setAttribute("cart", cartProducts);

            return "Product added to cart successfully";
        }
        return "Product not found";

    }
    @GetMapping("/view")
    public List<Product> viewCart() {
        List<Product> cartProducts = (List<Product>) httpSession.getAttribute("cart");
        return cartProducts;
    }
    @PostMapping("/checkout")
    public String checkoutCart() {
        httpSession.removeAttribute("cart");
        return "Checkout completed successfully";
    }


}
