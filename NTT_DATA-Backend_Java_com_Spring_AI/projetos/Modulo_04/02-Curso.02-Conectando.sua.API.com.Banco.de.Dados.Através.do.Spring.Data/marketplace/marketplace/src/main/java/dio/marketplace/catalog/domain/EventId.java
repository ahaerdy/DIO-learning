package dio.marketplace.catalog.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public record EventId(UUID id) {
    public EventId() {
        this(UUID.randomUUID());
    }
}