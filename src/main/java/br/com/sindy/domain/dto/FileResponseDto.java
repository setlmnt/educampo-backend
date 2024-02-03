package br.com.sindy.domain.dto;

public record FileResponseDto(
        Long id,
        String archiveName, // Nome do Arquivo
        String originalName, // Nome Original
        String contentType, // Tipo de Conteúdo
        Long size, // Tamanho
        String url // URL
) {
}
