package br.com.edu.fiap.techchallengelanchonete.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BaseController {

    @Value( "${mercadopago.token}" )
    private String TOKEN_MERCADO_PAGO;

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> body = new HashMap<>();
        body.put("status", "Up and Running");

        return ResponseEntity.ok(body);
    }
}
