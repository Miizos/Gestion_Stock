package com.microservices.backend.Repository;


import com.microservices.backend.Entites.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    boolean existsByReference(String reference);
}
