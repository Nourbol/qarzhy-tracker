package kz.edu.astanait.qarzhytracker.domain;

public record OpenAiChoice(OpenAiMessage message) {

    public String messageContent() {
        return message.content();
    }
}
