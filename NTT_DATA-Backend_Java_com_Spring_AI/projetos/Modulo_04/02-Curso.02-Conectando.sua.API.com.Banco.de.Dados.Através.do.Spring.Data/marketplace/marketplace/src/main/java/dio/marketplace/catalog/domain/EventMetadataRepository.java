package dio.marketplace.catalog.domain;

import java.util.Optional;
import java.util.UUID;

public interface EventMetadataRepository {
    Optional<EventMetadata> findByEventId(EventId eventId);
}