package kz.edu.astanait.qarzhytracker.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SaveCategoryRequest(@NotBlank
                                  @Size(max = 255)
                                  String name,
                                  @Valid
                                  SaveBudgetRequest budget) {
}
