
package dio.marketplace.registration.infrastructure.event;

import dio.marketplace.catalog.infrastructure.persistence.entity.EventMetadata;
import dio.marketplace.registration.infrastructure.event;

import dio.marketplace.catalog.infrastructure.persistence.entity.EventMetadata; // Verifique se o caminho do pacote está correto ou se o módulo precisa ser exportado/importado
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.stereotype.Component;

@Component
public class EventMetadataEventListener extends AbstractMongoEventListener<EventMetadata> {
}
// Verifique se o caminho do pacote está correto ou se o módulo precisa ser exportado/importado
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.stereotype.Component;

