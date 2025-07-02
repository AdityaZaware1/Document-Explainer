package com.ben.Document_Explainer.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AiResponse {

    private String text;

    private List<String> lines;
}
