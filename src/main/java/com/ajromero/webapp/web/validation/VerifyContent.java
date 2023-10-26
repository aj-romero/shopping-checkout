package com.ajromero.webapp.web.validation;

import com.ajromero.webapp.web.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VerifyContent implements IVerifyContent{
    @Override
    public void verifyListContent(boolean list) {
        if(list){
            log.warn("List of products is empty");
            throw new ResourceNotFoundException("No products available");
        }
    }
}
