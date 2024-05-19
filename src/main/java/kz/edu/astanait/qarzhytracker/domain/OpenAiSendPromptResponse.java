package kz.edu.astanait.qarzhytracker.domain;

import java.util.List;

public record OpenAiSendPromptResponse(List<OpenAiChoice> choices) {

    public String firstMessageContent() {
        return choices.getFirst().messageContent();
    }
}
