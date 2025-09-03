package com.microservices.backend.Entites.article;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "articles")
@Builder
public class Articles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String reference;

    @Column(nullable = false)
    private String designation;

    @Column(name = "stock_securite")
    private Long stockSecurite;

    @Column(name = "prix_achat_ht", precision = 10, scale = 2)
    private BigDecimal prixDAchatHT;

    @Column(name = "prix_vente_ht", precision = 10, scale = 2)
    private BigDecimal prixDeVenteHT;

    @Column(precision = 5, scale = 2)
    private BigDecimal tva;

    private String image;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public String getReference() {
        return reference;
    }

    public String getDesignation() {
        return designation;
    }

    public Long getStockSecurite() {
        return stockSecurite;
    }

    public BigDecimal getPrixDAchatHT() {
        return prixDAchatHT;
    }

    public BigDecimal getPrixDeVenteHT() {
        return prixDeVenteHT;
    }

    public BigDecimal getTva() {
        return tva;
    }

    public String getImage() {
        return image;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Articles(Long id, String reference, String designation, Long stockSecurite, BigDecimal prixDAchatHT, BigDecimal prixDeVenteHT, BigDecimal tva, String image, LocalDateTime createdAt) {
        this.id = id;
        this.reference = reference;
        this.designation = designation;
        this.stockSecurite = stockSecurite;
        this.prixDAchatHT = prixDAchatHT;
        this.prixDeVenteHT = prixDeVenteHT;
        this.tva = tva;
        this.image = image;
        this.createdAt = createdAt;
    }

    public Articles() {
    }


    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setStockSecurite(Long stockSecurite) {
        this.stockSecurite = stockSecurite;
    }

    public void setPrixDAchatHT(BigDecimal prixDAchatHT) {
        this.prixDAchatHT = prixDAchatHT;
    }

    public void setPrixDeVenteHT(BigDecimal prixDeVenteHT) {
        this.prixDeVenteHT = prixDeVenteHT;
    }

    public void setTva(BigDecimal tva) {
        this.tva = tva;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
