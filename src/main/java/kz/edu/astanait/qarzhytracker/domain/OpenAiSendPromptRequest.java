package kz.edu.astanait.qarzhytracker.domain;

import java.util.Collections;
import java.util.List;

public record OpenAiSendPromptRequest(String model,
                                      List<OpenAiMessage> messages,
                                      int n,
                                      double temperature) {

    public OpenAiSendPromptRequest(final String model,
                                   final OpenAiMessage message,
                                   final int n,
                                   final double temperature) {
        this(model, Collections.singletonList(message), n, temperature);
    }
}
