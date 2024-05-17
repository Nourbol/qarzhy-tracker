package kz.edu.astanait.qarzhytracker.domain;

import java.math.BigDecimal;
import java.util.List;

public record CategoriesStatistic(List<CategorySummary> categories,
                                  BigDecimal totalAmount) {
}
