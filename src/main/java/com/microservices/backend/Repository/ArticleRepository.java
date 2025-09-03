package com.microservices.backend.Repository;


import com.microservices.backend.Entites.article.Articles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Articles, Long> {
    boolean existsByReference(String reference);
}
