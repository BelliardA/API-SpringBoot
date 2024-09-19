package com.crea.dev6.firstapi.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.crea.dev6.firstapi.dao.ProductDao;
import com.crea.dev6.firstapi.model.Product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;



@Tag(name = "Product API", description = "GET and POST method for Products")
@RestController
public class ProductController {

    @Autowired
    private ProductDao productDao;

    // renvoie la liste des tous les produits
    @Operation(summary = "Get all products", description = "Get all products from the database", tags = { "Product API",
            "get" })

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(schema = @Schema(implementation = Product.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "No content", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(schema = @Schema()) })
    })

    @GetMapping("/products")
    public List<Product> findAll() {
        return productDao.findAll();
    }

    // renvoie un produit inexistant

    @Operation(summary = "Get Fake product", description = "Find a fake product", tags = { "Product API",
            "get" })

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(schema = @Schema(implementation = Product.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "No content", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(schema = @Schema()) })
    })
    @GetMapping("/productfake")
    public Product findFake() {
        return new Product(1, "Fake Product", 0, 0);
    }

    // renvoie un produit par son id
    @Operation(summary = "Get a product by is ID", description = "search a product by is ID", tags = { "Product API",
            "get" })

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(schema = @Schema(implementation = Product.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "No content", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(schema = @Schema()) })
    })
    @GetMapping("/product/{id}")
    public Optional<Product> findById(@PathVariable("id") int id) {
        return productDao.findById(id);
    }

    // renvoie un produit par son id
    @Operation(summary = "Get a product by is Name", description = "search a product by is full name", tags = {
            "Product API",
            "get" })

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(schema = @Schema(implementation = Product.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "No content", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(schema = @Schema()) })
    })
    @GetMapping("/productname/{name}")
    public Product findByName(@PathVariable("name") String name) {
        return productDao.findByName(name);
    }

    // renvoie un produit par son id
    @Operation(summary = "Get a product by is Name", description = "search a product by is full name", tags = {
            "Product API",
            "get" })

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(schema = @Schema(implementation = Product.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "No content", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(schema = @Schema()) })
    })
    @GetMapping("/productPrice/{id}")
    public List<Product> findByPriceSup(@PathVariable("price") double price) {
        return productDao.findByPriceGreaterThan(price);
    }

    // ajout d'un produit
    @PostMapping("/addProduct")
    public ResponseEntity<Void> addProduit(@RequestBody Product product) {
        Product productAdded = productDao.save(product);

        if (productAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/getRecipeByIngredient/{id}")
    public String getRecipeByIngredient(@PathVariable("id") int id) {
        Optional<Product> product = productDao.findById(id);
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("apiKey", "LH484ulQj4Me5QxQwND8gTANyv7GIyFe");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        ResponseEntity<Object> reponse = 
        restTemplate.exchange("https://api.apilayer.com/spoonacular/recipes/complexSearch?query=" + product.get().getName(),
        HttpMethod.GET, new HttpEntity<>(headers), Object.class);

        return reponse.getBody().toString();
    }

    
    

}
