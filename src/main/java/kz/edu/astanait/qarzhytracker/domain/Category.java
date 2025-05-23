package kz.edu.astanait.qarzhytracker.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Category(UUID id,
                       String name,
                       Budget budget) {

    public Category(final UUID id, final String name) {
        this(id, name, null);
    }
}
