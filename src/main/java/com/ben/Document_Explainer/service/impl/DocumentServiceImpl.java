package com.ben.Document_Explainer.service.impl;

import com.ben.Document_Explainer.entity.Document;
import com.ben.Document_Explainer.entity.User;
import com.ben.Document_Explainer.repo.DocumentRepo;
import com.ben.Document_Explainer.service.DocumentService;
import com.ben.Document_Explainer.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    @Value("documents/")
    String DIR;

    private final UserService userService;
    private final DocumentRepo documentRepo;

    @PostConstruct
    public void init() {
        File file = new File(DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    @Override
    public void uploadDocument(Long userId, MultipartFile document) {

        User user = userService.getUserById(userId);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        try {
            String fileName = document.getOriginalFilename();
            String contentType = document.getContentType();
            InputStream is = document.getInputStream();

            //cleaning file name
            String cleanFile = StringUtils.cleanPath(fileName);

            //cleaning folder
            String cleanFolder = StringUtils.cleanPath(DIR);

            //creating folder path
            Path path = Paths.get(cleanFolder, cleanFile);

            Files.copy(is,path, StandardCopyOption.REPLACE_EXISTING);

            Document document1 = new Document();
            document1.setFilePath(path.toString());
            document1.setUserId(userId);
            documentRepo.save(document1);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to upload document");
        }
    }

    @Override
    public Document getDocumentByUserId(Long id) {

        Document document = documentRepo.findDocumentsByUserId((id));

        if (document == null) {
            throw new RuntimeException("Document not found");
        }

        return document;
    }

    @Override
    public Document getDocument(Long id) {
        Optional<Document> document = documentRepo.findTopByUserIdOrderByCreatedAtDesc(id);

        if (document.isEmpty()) {
            throw new RuntimeException("Document not found");
        }

        return document.get();
    }
}
