package kz.edu.astanait.qarzhytracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kz.edu.astanait.qarzhytracker.configuration.OpenApiConfig;
import kz.edu.astanait.qarzhytracker.domain.Category;
import kz.edu.astanait.qarzhytracker.domain.SaveCategoryRequest;
import kz.edu.astanait.qarzhytracker.domain.UserResponse;
import kz.edu.astanait.qarzhytracker.service.CategoryModifier;
import kz.edu.astanait.qarzhytracker.service.CategoryReader;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@Tag(name = "Categories")
@RestController
@RequestMapping("/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryModifier categoryModifier;
    private final CategoryReader categoryReader;

    @Operation(
        summary = "Add a category for the authenticated user",
        security = @SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEME_NAME)
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> create(final @Valid @RequestBody SaveCategoryRequest request,
                                           final @AuthenticationPrincipal UserResponse userResponse) {
        var createdCategory = categoryModifier.create(request, userResponse.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @Operation(
        summary = "Get categories of the authenticated user",
        security = @SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEME_NAME)
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PageableAsQueryParam
    public ResponseEntity<Page<Category>> getUserTransactions(final @Parameter(hidden = true) Pageable pageable,
                                                              final @AuthenticationPrincipal UserResponse userResponse) {
        var categories = categoryReader.getUserCategories(userResponse.getId(), pageable);
        return ResponseEntity.ok(categories);
    }

    @Operation(
        summary = "Update the specified category of the authenticated user",
        security = @SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEME_NAME)
    )
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> update(final @PathVariable ("id") UUID categoryId,
                                           final @Valid @RequestBody SaveCategoryRequest request,
                                           final @AuthenticationPrincipal UserResponse userResponse) {
        categoryModifier.update(categoryId, request, userResponse.getId());
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Delete the specified category of the authenticated user",
        security = @SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEME_NAME)
    )
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Category> delete(final @PathVariable("id") UUID categoryId,
                                           final @AuthenticationPrincipal UserResponse userResponse) {
        categoryModifier.delete(categoryId, userResponse.getId());
        return ResponseEntity.noContent().build();
    }
}
