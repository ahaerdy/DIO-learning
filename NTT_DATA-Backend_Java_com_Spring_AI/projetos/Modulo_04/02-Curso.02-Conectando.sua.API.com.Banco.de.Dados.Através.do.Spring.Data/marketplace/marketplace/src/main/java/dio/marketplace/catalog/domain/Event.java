package dio.marketplace.catalog.domain;
import dio.marketplace.registration.infrastructure.persistence.entity.EventMetadata;
import java.time.Instant;
import java.util.Optional;

public class Event {
    private EventId id;
    private String title;
    private Instant date;
    private Optional<EventMetadata> metadata;

    public Event(EventId id, String title, Instant date, Optional<EventMetadata> metadata) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.metadata = metadata;
    }
}
