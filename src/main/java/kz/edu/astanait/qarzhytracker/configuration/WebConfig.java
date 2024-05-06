package kz.edu.astanait.qarzhytracker.configuration;

import kz.edu.astanait.qarzhytracker.resolver.TransactionFilterHandlerMethodArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;

/**
 * Spring Web configuration extension.
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final TransactionFilterHandlerMethodArgumentResolver transactionFilterHandlerMethodArgumentResolver;

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(transactionFilterHandlerMethodArgumentResolver);
    }
}
