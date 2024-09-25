package az.company.msproduct.controller;

import az.company.msproduct.model.request.CreateProductRequest;
import az.company.msproduct.model.request.ReduceQuantityRequest;
import az.company.msproduct.model.response.ProductResponse;
import az.company.msproduct.service.inter.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void create(@RequestBody @Valid CreateProductRequest createProductRequest) {
        productService.create(createProductRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public ProductResponse getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/reduce-quantity")
    @ResponseStatus(NO_CONTENT)
    public void reduceQuantity(@RequestBody @Valid ReduceQuantityRequest reduceQuantityRequest){
        productService.reduceQuantity(reduceQuantityRequest);
    }

}
