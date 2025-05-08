package com.example.hackaton1.AI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AIModelService {

    @Value("${openai.api.key}")
    private String openaiKey;

    @Value("${deepseek.api.key}")
    private String deepseekKey;

    private final WebClient webClient = WebClient.create();
    private final List<String> historial = new ArrayList<>();

    public Mono<String> chat(String prompt, String modelo) {
        String url, modelId, key, base;

        if (modelo.equalsIgnoreCase("deepseek")) {
            base = "https://api.deepseek.com";
            modelId = "deepseek-chat";
            key = deepseekKey;
        } else {
            base = "https://api.openai.com/v1";
            modelId = "gpt-4o";
            key = openaiKey;
        }

        url = base + "/chat/completions";

        Map<String, Object> body = Map.of(
                "model", modelId,
                "messages", List.of(Map.of("role", "user", "content", prompt))
        );

        return webClient.post()
                .uri(url)
                .header("Authorization", "Bearer " + key)
                .header("Content-Type", "application/json")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .map(res -> {
                    Map respuesta = (Map) ((List) res.get("choices")).get(0);
                    Map mensaje = (Map) respuesta.get("message");
                    String contenido = (String) mensaje.get("content");
                    historial.add("[" + modelId + "] " + prompt + " → " + contenido);
                    return contenido;
                });
    }

    public Mono<String> completarTexto(String prompt) {
        Map<String, Object> body = Map.of(
                "model", "text-davinci-003",
                "prompt", prompt,
                "max_tokens", 100
        );

        return webClient.post()
                .uri("https://api.openai.com/v1/completions")
                .header("Authorization", "Bearer " + openaiKey)
                .header("Content-Type", "application/json")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .map(res -> {
                    Map respuesta = (Map) ((List) res.get("choices")).get(0);
                    String texto = (String) respuesta.get("text");
                    historial.add("[completion] " + prompt + " → " + texto);
                    return texto;
                });
    }

    public Mono<String> multimodal(String prompt, String imagenUrl) {
        Map<String, Object> imagen = Map.of(
                "type", "image_url",
                "image_url", Map.of("url", imagenUrl)
        );

        Map<String, Object> texto = Map.of(
                "type", "text",
                "text", prompt
        );

        Map<String, Object> body = Map.of(
                "model", "gpt-4o",
                "messages", List.of(
                        Map.of("role", "user", "content", List.of(texto, imagen))
                )
        );

        return webClient.post()
                .uri("https://api.openai.com/v1/chat/completions")
                .header("Authorization", "Bearer " + openaiKey)
                .header("Content-Type", "application/json")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .map(res -> {
                    Map respuesta = (Map) ((List) res.get("choices")).get(0);
                    Map mensaje = (Map) respuesta.get("message");
                    String contenido = (String) mensaje.get("content");
                    historial.add("[multimodal] " + prompt + " + imagen → " + contenido);
                    return contenido;
                });
    }

    public List<String> modelosDisponibles() {
        return List.of("gpt-4o", "text-davinci-003", "deepseek-chat", "deepseek-coder");
    }

    public List<String> obtenerHistorial() {
        return historial;
    }
}

