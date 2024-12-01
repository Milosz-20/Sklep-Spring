package com.example.demo.repositories;

import com.example.demo.models.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DbRepository {
    public List<Product> getAllProducts() {
        ArrayList list = new ArrayList();
        return list;
    }
    JdbcTemplate jt;
    public DbRepository (JdbcTemplate jt) {
        this.jt = jt;
    }

    public List<Product> getAllProductsFromDb() {
        ArrayList list = (ArrayList) jt.query("select * from products", BeanPropertyRowMapper.newInstance(Product.class));

        return list;
    }
    public Product getProductById(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        return jt.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Product.class));
    }

    public void updateProduct(Product product) {
        String sql = "UPDATE products SET name = ?, price = ?, image1 = ?, image2 = ?, image3 = ?, image 4 = ?, description = ? WHERE id = ?";
        jt.update(sql, product.getName(), product.getPrice(), product.getImage1(), product.getImage2(), product.getImage3(), product.getImage4(), product.getDescription(), product.getId());
    }

    public void createProduct(Product product) {
        String sql = "insert into products (name, price, image1, image2, image3, image4, description) values (?, ?, ?, ?)";
        jt.update(sql, product.getName(), product.getPrice(), product.getImage1(), product.getImage2(), product.getImage3(), product.getImage4(), product.getDescription());
    }

    public void addSuperProduct(List<Product> products) {
        for (Product product : products) {
            String sql = "insert into products (name, price, image1, image2, image3, image4, description) values (?, ?, ?, ?)";
            jt.update(sql, product.getName(), product.getPrice(), product.getImage1(), product.getImage2(), product.getImage3(), product.getImage4(), product.getDescription());
        }
    }
    public void deleteProductById(int id) {
        String sql = "delete from products where id = ?";
        jt.update(sql, id);
    }
}

