package az.company.msproduct.service.impl;

import az.company.msproduct.dao.repository.ProductRepository;
import az.company.msproduct.exception.InsufficientQuantityException;
import az.company.msproduct.exception.NotFoundException;
import az.company.msproduct.model.request.CreateProductRequest;
import az.company.msproduct.model.request.ReduceQuantityRequest;
import az.company.msproduct.model.response.ProductResponse;
import az.company.msproduct.service.inter.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static az.company.msproduct.mapper.ProductMapper.PRODUCT_MAPPER;
import static az.company.msproduct.model.enums.ErrorMessage.*;
import static java.lang.String.*;

@Service
@RequiredArgsConstructor
public class ProductServiceHandler implements ProductService {
    private final ProductRepository productRepository;


    @Override
    public void create(CreateProductRequest request) {
        productRepository.save(PRODUCT_MAPPER.buildProductEntity(request));
    }

    @Override
    public ProductResponse getProductById(Long id) {
        return productRepository.findById(id)
                .map(PRODUCT_MAPPER::buildProductResponse)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                PRODUCT_NOT_FOUND.getMessage(),
                                id
                        )
                ));
    }

    @Override
    public void reduceQuantity(ReduceQuantityRequest reduceQuantityRequest) {
        var productEntity =
                productRepository.findById(reduceQuantityRequest.getProductId())
                        .orElseThrow(() -> new NotFoundException(
                                format(
                                        PRODUCT_NOT_FOUND.getMessage(),
                                        reduceQuantityRequest.getProductId()
                                )
                        ));

        if (productEntity.getQuantity() < reduceQuantityRequest.getQuantity()) {
            throw new InsufficientQuantityException(
                    format(INSUFFICIENT_QUANTITY.getMessage(),
                            reduceQuantityRequest.getProductId()
                    ));
        }

        productEntity.setQuantity(
                productEntity.getQuantity() - reduceQuantityRequest.getQuantity()
        );
        productRepository.save(productEntity);

    }
}
