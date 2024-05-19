package kz.edu.astanait.qarzhytracker.client;

import feign.RequestInterceptor;
import kz.edu.astanait.qarzhytracker.domain.OpenAiSendPromptRequest;
import kz.edu.astanait.qarzhytracker.domain.OpenAiSendPromptResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "open-ai", configuration = OpenAiClient.Configuration.class)
public interface OpenAiClient {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    OpenAiSendPromptResponse sendPrompt(OpenAiSendPromptRequest request);

    class Configuration {

        @Bean
        public RequestInterceptor authorizationRequestInterceptor(final @Value("${qarzhy-tracker.open-ai.token}") String token) {
            return requestTemplate -> requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer %s".formatted(token));
        }
    }
}
