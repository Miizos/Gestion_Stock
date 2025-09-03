package com.microservices.backend.Service;

import com.microservices.backend.Entites.article.Articles;
import com.microservices.backend.Repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Articles> getAllArticles() {
        return articleRepository.findAll();
    }

    public Optional<Articles> getArticleById(Long id) {
        return articleRepository.findById(id);
    }

    public Articles addArticle(Articles article) {
        if (articleRepository.existsByReference(article.getReference())) {
            throw new RuntimeException("Article with reference " + article.getReference() + " already exists");
        }
        return articleRepository.save(article);
    }

    public Articles updateArticle(Long id, Articles articleDetails) {
        Articles article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found with id: " + id));

        article.setReference(articleDetails.getReference());
        article.setDesignation(articleDetails.getDesignation());
        article.setStockSecurite(articleDetails.getStockSecurite());
        article.setPrixDAchatHT(articleDetails.getPrixDAchatHT());
        article.setPrixDeVenteHT(articleDetails.getPrixDeVenteHT());
        article.setTva(articleDetails.getTva());
        article.setImage(articleDetails.getImage());

        return articleRepository.save(article);
    }

    public void deleteArticle(Long id) {
        if (!articleRepository.existsById(id)) {
            throw new RuntimeException("Article not found with id: " + id);
        }
        articleRepository.deleteById(id);
    }
}