package dio.marketplace.registration.infrastructure.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event_metadata")
public class EventMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    @Column(nullable = false, columnDefinition = "VARCHAR(36)")
    private UUID eventId;

    @Column(columnDefinition = "TEXT")
    private String eventDescription;

    @Transient
    private Map<String, Object> technicalRequirements;

    @ElementCollection
    @CollectionTable(name = "event_sectors", joinColumns = @JoinColumn(name = "event_metadata_id"))
    private List<Sector> sectors;

    @ElementCollection
    @CollectionTable(name = "event_seats", joinColumns = @JoinColumn(name = "event_metadata_id"))
    private List<Seat> seats;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdOn;

    @UpdateTimestamp
    private Instant updatedAt;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class Sector {
        @Column(nullable = false)
        private String name;

        @Column(precision = 10, scale = 2)
        private BigDecimal price;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class Seat {
        @Column(nullable = false)
        private String code;

        @Column(nullable = false)
        private String sectorName;
    }
}