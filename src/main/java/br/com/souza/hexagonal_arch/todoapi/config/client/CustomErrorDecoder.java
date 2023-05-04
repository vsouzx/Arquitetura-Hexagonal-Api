package br.com.souza.hexagonal_arch.todoapi.config.client;

import br.com.souza.hexagonal_arch.todoapi.config.handler.exceptions.BadRequestException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        String requestUrl = response.request().url();
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());

        if(responseStatus.value() == 400){
            return new BadRequestException(requestUrl);
        }else{
            return new Exception("Exception: " +  responseStatus);
        }
    }
}
