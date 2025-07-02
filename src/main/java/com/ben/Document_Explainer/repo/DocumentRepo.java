package com.ben.Document_Explainer.repo;

import com.ben.Document_Explainer.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentRepo extends JpaRepository<Document, Long> {
    Document getDocumentsByUserId(Long userId);

    Document findDocumentsByUserId(Long userId);

}
