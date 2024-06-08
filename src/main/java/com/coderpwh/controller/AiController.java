package com.coderpwh.controller;

import com.coderpwh.util.RestResponse;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


/**
 * @author coderpwh
 */
@RestController
public class AiController {




     @Resource
     private OpenAiChatClient openAiChatClient;


    @GetMapping("/ai/generate")
    public RestResponse generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return RestResponse.succuess(openAiChatClient.call(message));
    }

    @GetMapping("/ai/generateStream")
    public Flux<ChatResponse> generateStream(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        Prompt prompt = new Prompt(new UserMessage(message));
        return openAiChatClient.stream(prompt);
    }

}
