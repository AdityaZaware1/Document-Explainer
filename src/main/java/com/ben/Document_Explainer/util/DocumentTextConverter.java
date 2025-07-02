package com.ben.Document_Explainer.util;

import com.ben.Document_Explainer.entity.Document;
import com.ben.Document_Explainer.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DocumentTextConverter {

    private final DocumentService documentService;

    public String extractText(Long userId) throws Exception {

        Document newDocument = documentService.getDocumentByUserId(userId);

        String path = newDocument.getFilePath();

        String newFile = path;

        File file = new File(newFile);
        if (!file.exists()) {
            throw new IllegalArgumentException("Resume file not found: " );
        }

        if (newFile.endsWith(".pdf")) {
            try (PDDocument document = PDDocument.load(file)) {
                return new PDFTextStripper().getText(document);
            }
        } else if (newFile.endsWith(".docx")) {
            try (FileInputStream fis = new FileInputStream(file);
                 XWPFDocument document = new XWPFDocument(fis)) {
                return document.getParagraphs().stream()
                        .map(XWPFParagraph::getText)
                        .collect(Collectors.joining("\n"));
            }
        } else {
            throw new IllegalArgumentException("Unsupported file type: " + newFile);
        }
    }
}
