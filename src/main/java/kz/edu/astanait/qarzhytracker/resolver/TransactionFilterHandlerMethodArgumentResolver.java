package kz.edu.astanait.qarzhytracker.resolver;

import kz.edu.astanait.qarzhytracker.configuration.TransactionFilterConfigProperties;
import kz.edu.astanait.qarzhytracker.domain.TransactionFilter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import java.time.Clock;

@Component
@RequiredArgsConstructor
public class TransactionFilterHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private final TransactionFilterConfigProperties configProperties;
    private final Clock clock;

    @Override
    public boolean supportsParameter(final @NonNull MethodParameter parameter) {
        return TransactionFilter.class.equals(parameter.getParameterType());
    }

    @Override
    public TransactionFilter resolveArgument(final @NonNull MethodParameter parameter,
                                             final ModelAndViewContainer mavContainer,
                                             final @NonNull NativeWebRequest webRequest,
                                             final WebDataBinderFactory binderFactory) {
        return configProperties.extractFromRequest(webRequest, clock);
    }
}
