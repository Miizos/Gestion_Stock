package com.microservices.backend.Controller;

import com.microservices.backend.Entites.article.Article;
import com.microservices.backend.Service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articles")
@CrossOrigin(origins = "http://localhost:5173")
public class ArticleController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleService articleService;

    @Autowired // Added explicit autowiring
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        try {
            logger.info("Fetching all articles");
            List<Article> articles = articleService.getAllArticles();
            return ResponseEntity.ok(articles);
        } catch (Exception e) {
            logger.error("Error fetching all articles: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        try {
            logger.info("Fetching article with id: {}", id);
            Optional<Article> article = articleService.getArticleById(id);
            return article.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            logger.error("Error fetching article with id {}: ", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Article> addArticle(@Valid @RequestBody Article article) {
        try {
            logger.info("Creating new article: {}", article.getReference()); // Assuming Articles has getTitle()
            Article savedArticle = articleService.addArticle(article);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
        } catch (RuntimeException e) {
            logger.error("Runtime error creating article: ", e);
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            logger.error("Error creating article: ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id,
                                                 @Valid @RequestBody Article articleDetails) {
        try {
            logger.info("Updating article with id: {}", id);
            Article updatedArticle = articleService.updateArticle(id, articleDetails);
            return ResponseEntity.ok(updatedArticle);
        } catch (RuntimeException e) {
            logger.error("Runtime error updating article with id {}: ", id, e);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Error updating article with id {}: ", id, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        try {
            logger.info("Deleting article with id: {}", id);
            articleService.deleteArticle(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            logger.error("Runtime error deleting article with id {}: ", id, e);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Error deleting article with id {}: ", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}