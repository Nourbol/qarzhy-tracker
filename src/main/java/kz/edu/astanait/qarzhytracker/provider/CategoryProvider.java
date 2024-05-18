package kz.edu.astanait.qarzhytracker.provider;

import kz.edu.astanait.qarzhytracker.entity.CategoryEntity;
import kz.edu.astanait.qarzhytracker.repository.CategoryRepository;
import kz.edu.astanait.qarzhytracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CategoryProvider {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public CategoryEntity findByNameOrCreate(final String name, final UUID userId) {
        return categoryRepository.findByNameAndUserId(name, userId)
                                 .orElseGet(() -> {
                                     var user = userRepository.getReferenceById(userId);
                                     return categoryRepository.save(new CategoryEntity(name, user));
                                 });
    }
}
