package kz.edu.astanait.qarzhytracker.annotation;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import kz.edu.astanait.qarzhytracker.domain.TransactionType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Parameter(
    in = ParameterIn.QUERY,
    description = "Filters transactions based on their details by specifying a search query.",
    name = "search",
    schema = @Schema(
        type = "string"
    )
)
@Parameter(
    in = ParameterIn.QUERY,
    description = "Specifies the starting date of the transaction range.",
    name = "from",
    schema = @Schema(
        type = "string",
        format = "date"
    )
)
@Parameter(
    in = ParameterIn.QUERY,
    description = "Specifies the ending date of the transaction range.",
    name = "to",
    schema = @Schema(
        type = "string",
        format = "date"
    )
)
@Parameter(
    in = ParameterIn.QUERY,
    description = "Specifies the type of transactions. Acceptable values are EXPENSE and REVENUE.",
    name = "type",
    schema = @Schema(
        implementation = TransactionType.class
    )
)
public @interface TransactionFilterAsQueryParam {
}

