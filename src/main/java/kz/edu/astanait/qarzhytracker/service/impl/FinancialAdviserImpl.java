package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.domain.FinancialAdvice;
import kz.edu.astanait.qarzhytracker.domain.FinancialAdviceRequest;
import kz.edu.astanait.qarzhytracker.service.FinancialAdviser;
import kz.edu.astanait.qarzhytracker.service.PromptSender;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FinancialAdviserImpl implements FinancialAdviser {

    private final PromptSender promptSender;
    private final FinancialAdvicePromptBuilder promptBuilder;

    @Override
    @Cacheable("financial-advice")
    public FinancialAdvice askForAdvice(final FinancialAdviceRequest request, final UUID userId) {
        var prompt = promptBuilder.build(request, userId);
        var advice = promptSender.sendPrompt(prompt);
        return new FinancialAdvice(advice);
    }
}
