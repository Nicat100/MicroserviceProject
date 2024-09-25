package az.company.msproduct.service.inter;

import az.company.msproduct.model.request.CreateProductRequest;
import az.company.msproduct.model.request.ReduceQuantityRequest;
import az.company.msproduct.model.response.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    void create(CreateProductRequest request);

    ProductResponse getProductById(Long id);

    void reduceQuantity(ReduceQuantityRequest reduceQuantityRequest);
}
