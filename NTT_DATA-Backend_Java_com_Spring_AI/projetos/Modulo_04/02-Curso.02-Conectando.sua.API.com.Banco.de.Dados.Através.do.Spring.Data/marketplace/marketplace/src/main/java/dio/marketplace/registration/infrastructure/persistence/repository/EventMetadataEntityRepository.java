package dio.marketplace.registration.infrastructure.persistence.repository;

import dio.marketplace.registration.infrastructure.event.EventMetadataEventListener;
import dio.marketplace.registration.infrastructure.persistence.entity.EventMetadata;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.logging.Logger;

@RepositoryRestResource(path = "event-metadata", collectionResourceRel = "eventMetadata")
public interface EventMetadataEntityRepository extends JpaRepository<EventMetadata, Long> {
    private static final Logger logger = LoggerFactory.getLogger(EventMetadataEventListener.class);

    @Override
    public void onAfterSave(AfterSaveEvent<EventMetadata> event) {
        logger.info("Event metadata save via onAfterSave {}", event.getDocument());
    }

    @Override
    public void onAfterDelete(AfterDeleteEvent<EventMetadata> event) {
        logger.info("Event metadata delete via onAfterDelete {}", event.getDocument());
    }
}