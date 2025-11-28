package com.example.shopping.controller;

import com.example.shopping.ai.AIConversationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AIController {
  private final AIConversationService ai;

  public AIController(AIConversationService ai) { this.ai = ai; }

  @PostMapping("/ask")
  public ResponseEntity<Map<String,String>> ask(@RequestBody Map<String,String> body) {
    String message = body.get("message");
    if (message == null || message.isBlank()) {
      return ResponseEntity.badRequest().body(Map.of("error", "message required"));
    }
    String reply = ai.ask(message);
    return ResponseEntity.ok(Map.of("reply", reply));
  }
}
