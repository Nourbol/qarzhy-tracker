package kz.edu.astanait.qarzhytracker.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.edu.astanait.qarzhytracker.domain.FinancialAdviceRequest;
import kz.edu.astanait.qarzhytracker.service.TransactionStatisticReader;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FinancialAdvicePromptBuilder {

    private static final String PROMPT = """
        Act as Finance Advisor, your aim to give 3 advices for person's expenses.
        As an input I give you JSON with revenues and expenses grouped by categories.
        As an output I expect 3 advices to improve financial well-being, each advice no more than 150 words length.
        Language: %s.
        Currency: KZT.

        Input:
        ```
        %s
        ```

        Output:
        """;

    private final TransactionStatisticReader transactionStatisticReader;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public String build(final FinancialAdviceRequest request, final UUID userId) {
        var financialOverview =
            transactionStatisticReader.getUserCategorizedFinancialOverview(userId, request.statisticPeriodFrom(), request.statisticPeriodTo());
        var json = objectMapper.writeValueAsString(financialOverview);
        return PROMPT.formatted(request.responseLanguage(), json);
    }
}
