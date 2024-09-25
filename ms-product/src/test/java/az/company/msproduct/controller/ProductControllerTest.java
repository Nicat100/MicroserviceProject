package az.company.msproduct.controller;

import az.company.msproduct.model.request.CreateProductRequest;
import az.company.msproduct.model.response.ProductResponse;
import az.company.msproduct.service.inter.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    private static final String PRODUCT_PATH = "/api/v1/product";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;


    @Test
    void test_Create() throws Exception {
        var request = new CreateProductRequest();
        request.setName("Name");
        request.setDescription("Description");
        request.setPrice(new BigDecimal("100.00"));
        request.setQuantity(10);


        doNothing().when(productService).create(request);


        mockMvc.perform(post(PRODUCT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated());

    }

    @Test
    void testGetProductById() throws Exception {

        var ID = 1L;

        var response = new ProductResponse();
        response.setName("Name");
        response.setDescription("Description");
        response.setPrice(new BigDecimal("100.00"));
        response.setQuantity(10);

        when(productService.getProductById(ID)).thenReturn(response);

        mockMvc.perform(get(PRODUCT_PATH + "/{id}", ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));

        verify(productService, times(1)).getProductById(ID);
    }
}