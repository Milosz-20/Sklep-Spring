package com.example.demo.controllers;

import com.example.demo.models.Product;
import com.example.demo.repositories.DbRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {
    DbRepository dbRepository;
    public MainController(DbRepository dbRepository) {
        this.dbRepository = dbRepository;
    }

    @GetMapping("")
    public String mainPage(Model m) {
        m.addAttribute("contentFragment", "home");
        return "index";
    }

    @GetMapping("/products")
    public String products(Model m) {
        List<Product> products = dbRepository.getAllProductsFromDb();
        m.addAttribute("products", products);
        m.addAttribute("contentFragment", "products");
        return "index";
    }

    @GetMapping("/products/view/{id}")
    public String viewProduct(@PathVariable("id") int id, Model m) {
        Product product = dbRepository.getProductById(id);
        m.addAttribute("product", product);
        m.addAttribute("contentFragment", "view");
        return "index";
    }

    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable("id") int id, Model m) {
        Product product = dbRepository.getProductById(id);
        m.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/products/update")
    public String updateProduct(Product product) {
        dbRepository.updateProduct(product);
        System.out.println(product.getId());
        return "redirect:/products";
    }

    @GetMapping("/products/create")
    public String createProduct() {
        return "createProduct";
    }

    @PostMapping("/products/updateCreate")
    public String createProduct(Product product) {
        dbRepository.createProduct(product);
        return "redirect:/products";
    }

//    @GetMapping("/contact")
//    public String contact() {
//        return "contactPage";
//    }
//    @GetMapping("/cart")
//    public String cart() {
//        return "cartPage";
//    }
}
