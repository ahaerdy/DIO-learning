package dio.marketplace.registration.domain;

import java.util.UUID;
import org.springframework.util.Assert;

public record CustomerId(UUID id) {
    // Construtor Canônico (Valida a entrada)
    public CustomerId {
        Assert.notNull(id, "id must not be null");
    }

    // Construtor Alternativo (Gera UUID aleatório se chamado sem parâmetros)
    public CustomerId() {
        this(UUID.randomUUID());
    }
}