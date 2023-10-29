package com.ifba.educampo.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "associates_photos")
public class AssociatePhoto { // Foto do Associado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "archive_name", nullable = false)
    private String archiveName; // Nome do Arquivo

    @Column(name = "original_name", nullable = false)
    private String originalName; // Nome Original

    @Column(name = "content_type", nullable = false)
    private String contentType; // Tipo de Conteúdo

    @Column(nullable = false)
    private Long size; // Tamanho

    @Column(nullable = false)
    private String url; // URL

    @Column(nullable = false)
    private Boolean deleted = false;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt; // Data de Criação

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Data de Atualização

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt; // Data de Atualização

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "AssociatePhoto{" +
                "id=" + id +
                ", archiveName='" + archiveName + '\'' +
                ", originalName='" + originalName + '\'' +
                ", contentType='" + contentType + '\'' +
                ", size=" + size +
                ", url='" + url + '\'' +
                ", deleted=" + deleted +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                '}';
    }
}
