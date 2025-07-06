package com.ben.Document_Explainer.service;

import com.ben.Document_Explainer.entity.Document;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {

    public void uploadDocument(Long userId, MultipartFile document);

    Document getDocumentByUserId(Long id);

    Document getDocument(Long id);

}
