package br.com.fiap.yourmenu.models;

public record Token(
        String token,
        String type,
        String prefix) {
}