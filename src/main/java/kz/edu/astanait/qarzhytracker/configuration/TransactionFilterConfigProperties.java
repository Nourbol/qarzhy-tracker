package kz.edu.astanait.qarzhytracker.configuration;

import kz.edu.astanait.qarzhytracker.domain.TransactionFilter;
import kz.edu.astanait.qarzhytracker.domain.TransactionType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.context.request.WebRequest;
import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@ConfigurationProperties("qarzhy-tracker.data.web.transaction-filter")
public record TransactionFilterConfigProperties(String searchParameter,
                                                String typeParameter,
                                                String fromParameter,
                                                String toParameter,
                                                String dateFormat,
                                                int fromDaysOffset) {

    public TransactionFilter extractFromRequest(final WebRequest webRequest, final Clock clock) {
        var dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat());
        var typeParameterValue = webRequest.getParameter(typeParameter());
        var fromParameterValue = webRequest.getParameter(fromParameter());
        var toParameterValue = webRequest.getParameter(toParameter());
        return new TransactionFilter(
            webRequest.getParameter(searchParameter()),
            Objects.nonNull(typeParameterValue) ? TransactionType.valueOf(typeParameterValue) : null,
            Objects.nonNull(fromParameterValue)
                ? LocalDate.parse(fromParameterValue, dateTimeFormatter)
                : LocalDate.now(clock).minusDays(fromDaysOffset()),
            Objects.nonNull(toParameterValue) ? LocalDate.parse(toParameterValue, dateTimeFormatter) : LocalDate.now(clock)
        );
    }
}
