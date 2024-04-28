package kz.edu.astanait.qarzhytracker.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity {

    @Id
    private UUID id = UUID.randomUUID();
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Override
    public int hashCode() {
        return this.id != null ? this.id.hashCode() : super.hashCode();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof BaseEntity that) {
            return Objects.equals(getId(), that.getId());
        }
        return false;
    }
}
