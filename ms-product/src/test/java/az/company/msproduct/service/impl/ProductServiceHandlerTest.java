package az.company.msproduct.service.impl;

import az.company.msproduct.dao.entity.ProductEntity;
import az.company.msproduct.dao.repository.ProductRepository;
import az.company.msproduct.exception.NotFoundException;
import az.company.msproduct.mapper.ProductMapper;
import az.company.msproduct.model.enums.ErrorMessage;
import az.company.msproduct.model.request.CreateProductRequest;
import az.company.msproduct.model.response.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceHandlerTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceHandler productServiceHandler;

    private ProductResponse productResponse;
    private CreateProductRequest createProductRequest;
    private ProductEntity productEntity;

    @BeforeEach
    void setUp() {
        var ID = 1L;

        productResponse = new ProductResponse();
        productResponse.setId(ID);
        productResponse.setName("Test Product Name");
        productResponse.setDescription("Test Product Description");
        productResponse.setPrice(BigDecimal.TEN);
        productResponse.setQuantity(10);

        createProductRequest = new CreateProductRequest();
        createProductRequest.setName("Test Product Name");
        createProductRequest.setDescription("Test Product Description");
        createProductRequest.setPrice(BigDecimal.TEN);
        createProductRequest.setQuantity(10);

        productEntity = new ProductEntity();
        productEntity.setId(ID);
        productEntity.setName("Test Product Name");
        productEntity.setDescription("Test Product Description");
        productEntity.setPrice(BigDecimal.TEN);
        productEntity.setQuantity(10);
    }

    @Test
    void testCreate() {
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);

        productServiceHandler.create(createProductRequest);

        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void testGetProductById() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));

        when(ProductMapper.PRODUCT_MAPPER.buildProductResponse(productEntity)).thenReturn(productResponse);

        ProductResponse response = productServiceHandler.getProductById(1L);

        assertEquals(productResponse, response);
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testGetProductById_NotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            productServiceHandler.getProductById(1L);
        });

        assertEquals(String.format(ErrorMessage.PRODUCT_NOT_FOUND.getMessage(), 1L), exception.getMessage());
        verify(productRepository, times(1)).findById(1L);
    }
}
