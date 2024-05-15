package kz.edu.astanait.qarzhytracker.specification;

import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.SingularAttribute;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import java.util.Objects;
import java.util.function.Function;

/**
 * A utility class for building JPA specifications.
 *
 * @param <T> The type of the entity for which specifications are being built.
 */
@RequiredArgsConstructor(staticName = "wrap")
public class SpecificationBuilder<T> {

    private final Specification<T> baseSpecification;

    @Setter(AccessLevel.PRIVATE)
    private boolean returnsEmpty;

    /**
     * Creates a {@link SpecificationBuilder} for text containment checks on a specified attribute.
     *
     * @param attribute The singular attribute to be checked for text containment.
     * @param text      The text to check for containment.
     * @param <T>       The type of the entity.
     * @return A {@link SpecificationBuilder} instance for text containment checks.
     */
    public static <T> SpecificationBuilder<T> containsText(final SingularAttribute<T, String> attribute,
                                                           final String text) {
        return containsText(root -> root.get(attribute), text);
    }

    /**
     * Creates a {@link SpecificationBuilder} for text containment checks on a specified path.
     *
     * @param pathRetriever A function to retrieve the path to be checked for text containment.
     * @param text          The text to check for containment.
     * @param <T>           The type of the entity.
     * @return A {@link SpecificationBuilder} instance for text containment checks.
     */
    public static <T> SpecificationBuilder<T> containsText(final Function<Root<T>, Path<String>> pathRetriever,
                                                           final String text) {
        return wrap((root, query, cb) -> cb.like(cb.lower(pathRetriever.apply(root)), "%" + text.toLowerCase() + "%"));
    }

    public static <T, V> SpecificationBuilder<T> isEqualTo(final Function<Root<T>, Path<V>> pathRetriever,
                                                           final V value) {
        return wrap((root, query, cb) -> cb.equal(pathRetriever.apply(root), value));
    }

    public static <T, V extends Comparable<? super V>> SpecificationBuilder<T> between(final SingularAttribute<T, V> attribute,
                                                                                       final V from,
                                                                                       final V to) {
        return between(root -> root.get(attribute), from, to);
    }

    public static <T, V extends Comparable<? super V>> SpecificationBuilder<T> between(final Function<Root<T>, Path<V>> pathRetriever,
                                                                                       final V from,
                                                                                       final V to) {
        Specification<T> specification = (root, query, cb) -> cb.between(pathRetriever.apply(root), from, to);
        return wrap(specification);
    }

    /**
     * Specifies that the resulting specification should be empty if the provided value is null.
     *
     * @param value The value to be checked for null.
     * @param <V>   The type of the value.
     * @return A {@link SpecificationBuilder} instance with the specified condition.
     */
    public <V> SpecificationBuilder<T> returnEmptyIfNull(final @Nullable V value) {
        return returnEmptyIf(Objects.isNull(value));
    }

    /**
     * Specifies that the resulting specification should be empty if the search query is invalid.
     * An invalid search query is either null or contains less than or equal to one non-whitespace character.
     *
     * @param searchQuery The search query to be checked for validity.
     * @return A {@link SpecificationBuilder} instance with the specified condition.
     */
    public SpecificationBuilder<T> returnEmptyIfInvalidSearch(final @Nullable String searchQuery) {
        return returnEmptyIf(Objects.isNull(searchQuery) || searchQuery.trim().length() <= 1);
    }

    /**
     * Specifies that the resulting specification should be empty based on the provided condition.
     *
     * @param returnsEmptyCondition The condition to determine if the specification should be empty.
     * @return A {@code SpecificationBuilder} instance with the specified condition.
     */
    public SpecificationBuilder<T> returnEmptyIf(final boolean returnsEmptyCondition) {
        setReturnsEmpty(returnsEmptyCondition);
        return this;
    }

    /**
     * Builds and returns the final specification based on the specified conditions.
     *
     * @return The constructed specification, which may be empty depending on the specified conditions.
     */
    public Specification<T> build() {
        return returnsEmpty ? emptySpecification() : baseSpecification;
    }

    /**
     * Returns an empty specification.
     *
     * @param <E> The type of the entity for which an empty specification is created.
     * @return An empty specification.
     */
    public static <E> Specification<E> emptySpecification() {
        return (root, query, criteriaBuilder) -> null;
    }
}
