// src/main/java/org/acme/routes/dto/RouteRequest.java
package dtos;

// Record para receber os nomes das estações na requisição POST
public record RouteRequest(String estacaoOrigem, String estacaoDestino) { }

