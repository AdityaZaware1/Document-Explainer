package com.ben.Document_Explainer.controller;

import com.ben.Document_Explainer.reponse.AiResponse;
import com.ben.Document_Explainer.service.DocumentService;
import com.ben.Document_Explainer.util.DocumentTextConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class AiController {

    private final OpenAiChatModel chatModel;
    private final DocumentTextConverter documentTextConverter;
    private final DocumentService documentService;

    @PostMapping("/uploadDocument/{userId}")
    public AiResponse generate(@PathVariable Long userId,
                               @RequestParam(value = "file") MultipartFile file) throws Exception{

        documentService.uploadDocument(userId, file);

        String resumeText = documentTextConverter.extractText(userId);
        String prompt = "You are an expert at simplifying complex documents.\n" +
                "\n" +
                "Document Section:\n" +
                "\"\"\"\n" +
                resumeText + "\n" +
                "\"\"\"\n" +
                "\n" +
                "Instructions:\n" +
                "- Summarize the section in 10 short bullet points.\n" +
                "- Use simple, clear language.\n" +
                "- Only include the most important ideas.\n" +
                "- Avoid long explanations or legal jargon.\n" +
                "\n" +
                "Your Summary:";


        String aiResponse = chatModel.call(prompt);

        List<String> lines = Arrays.stream(aiResponse.split("\n"))
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .collect(Collectors.toList());

        return new AiResponse(aiResponse.trim(), lines);

    }
}
