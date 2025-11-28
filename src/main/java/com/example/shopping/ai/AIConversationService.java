package com.example.shopping.ai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class AIConversationService {

  private final WebClient client;
  private final String apiKey;
  private final String apiUrl;

  public AIConversationService(@Value("${ai.api.key}") String apiKey,
                               @Value("${ai.api.url}") String apiUrl) {
    this.apiKey = apiKey;
    this.apiUrl = apiUrl;
    this.client = WebClient.builder()
            .baseUrl(apiUrl)
            .defaultHeader("Authorization", apiKey == null || apiKey.isBlank() ? "" : "Bearer " + apiKey)
            .defaultHeader("Content-Type", "application/json")
            .build();
  }

  // For demo: if no API key provided, return a canned reply.
  public String ask(String userMessage) {
    if (apiKey == null || apiKey.isBlank()) {
      if (userMessage == null) return "No message";
      if (userMessage.toLowerCase().contains("recommend")) {
        return "Demo reply: I recommend Phone X for photography (50MP sensor, OIS).";
      }
      return "Demo reply: AI not configured. Replace AI_API_KEY to enable real calls.";
    }

    Map<String, Object> payload = Map.of(
        "model", "gpt-4o-mini",
        "messages", new Object[] {
            Map.of("role", "user", "content", userMessage)
        },
        "max_tokens", 250
    );

    Mono<Map> responseMono = client.post()
            .bodyValue(payload)
            .retrieve()
            .bodyToMono(Map.class);

    Map resp = responseMono.block();
    if (resp == null) return "AI service returned no response";
    Object choices = resp.get("choices");
    if (choices instanceof java.util.List && !((java.util.List)choices).isEmpty()) {
      Map first = (Map) ((java.util.List) choices).get(0);
      Object message = first.get("message");
      if (message instanceof Map) {
        Object content = ((Map) message).get("content");
        return content == null ? content.toString() : content.toString();
      }
    }
    Object txt = resp.get("text");
    return txt != null ? txt.toString() : resp.toString();
  }
}
