package com.ajromero.webapp.payment.api;

import java.util.UUID;
//design pattern singleton

public class GenerateUuid {

    private GenerateUuid() {
    }

    private static class RegisterGenerate {
        private static final GenerateUuid INSTANCE = new GenerateUuid();
    }

    public static GenerateUuid getInstance() {
        return RegisterGenerate.INSTANCE;
    }

    public String generateCode() {
        return UUID.randomUUID().toString();
    }

}
