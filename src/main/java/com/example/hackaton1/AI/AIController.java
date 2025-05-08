package com.example.hackaton1.AI;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final AIModelService aiService;

    public AIController(AIModelService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/chat")
    public Mono<String> chat(@RequestBody Map<String, String> body) {
        String prompt = body.get("prompt");
        String modelo = body.getOrDefault("modelo", "gpt-4o");
        return aiService.chat(prompt, modelo);
    }

    @PostMapping("/completion")
    public Mono<String> completar(@RequestBody Map<String, String> body) {
        String prompt = body.get("prompt");
        return aiService.completarTexto(prompt);
    }

    @PostMapping("/multimodal")
    public Mono<String> multimodal(@RequestBody Map<String, String> body) {
        String prompt = body.get("prompt");
        String imagenUrl = body.get("imagen");
        return aiService.multimodal(prompt, imagenUrl);
    }

    @GetMapping("/models")
    public List<String> modelos() {
        return aiService.modelosDisponibles();
    }

    @GetMapping("/history")
    public List<String> historial() {
        return aiService.obtenerHistorial();
    }
}
