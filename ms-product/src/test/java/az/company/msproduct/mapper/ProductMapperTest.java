package az.company.msproduct.mapper;

import az.company.msproduct.dao.entity.ProductEntity;
import az.company.msproduct.model.request.CreateProductRequest;
import az.company.msproduct.model.response.ProductResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


class ProductMapperTest {
    @Test
    void toEntityTest(){
        var request = new CreateProductRequest();
        request.setDescription("descripiton");
        request.setName("name");
        request.setPrice(BigDecimal.TEN);
        request.setQuantity(5);

        var expected = new ProductEntity();
        expected.setDescription("descripiton");
        expected.setName("name");
        expected.setPrice(BigDecimal.TEN);
        expected.setQuantity(5);


        var actual = ProductMapper.PRODUCT_MAPPER.buildProductEntity(request);

        Assertions.assertThat(actual).isEqualTo(expected);
    }


    @Test
    void toRequestTest(){
        var request = new ProductEntity();
        request.setDescription("descripiton");
        request.setName("name");
        request.setPrice(BigDecimal.TEN);
        request.setQuantity(5);

        var expected = new ProductResponse();
        expected.setDescription("descripiton");
        expected.setName("name");
        expected.setPrice(BigDecimal.TEN);
        expected.setQuantity(5);

        var actual = ProductMapper.PRODUCT_MAPPER.buildProductResponse(request);

        Assertions.assertThat(actual).isEqualTo(expected);


    }


}