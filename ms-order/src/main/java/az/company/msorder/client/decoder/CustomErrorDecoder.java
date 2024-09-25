package az.company.msorder.client.decoder;

import az.company.msorder.exception.CustomFeignException;
import com.fasterxml.jackson.databind.JsonNode;
import feign.Response;
import feign.codec.ErrorDecoder;

import static az.company.msorder.client.decoder.JsonNodeFieldName.Message;
import static az.company.msorder.model.enums.ErrorMessages.CLIENT_ERROR;
import static az.company.msorder.util.MapperUtil.MAPPER_UTIL;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
       var errorMessage = CLIENT_ERROR.getMessage();
       var statusCode = response.status();

        JsonNode jsonNode;

        try(var body = response.body().asInputStream()){
            jsonNode = MAPPER_UTIL.map(body,JsonNode.class);
        }catch (Exception exception){
            throw new CustomFeignException(statusCode, CLIENT_ERROR.getMessage());
        }

        if(jsonNode.has(Message.getValue())) errorMessage = jsonNode.get(Message.getValue()).asText();
        return new CustomFeignException(statusCode, errorMessage);
    }
}
