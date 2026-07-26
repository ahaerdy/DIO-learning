package dio.proposalmanagement.proposal.infrastructure.persistence.entity;

import dio.proposalmanagement.proposal.domain.Proposal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProposalEntity {
    @Id
    private UUID id;
    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private UUID ownerId;

    @Column(nullable = false)
    private String ownerName;

    public static ProposalEntity from(Proposal proposal) {
        return new ProposalEntity(
                proposal.getId().id(),
                proposal.getTitle(),
                proposal.getDescription().orElse(null),
                proposal.getOwner().id().id(),
                proposal.getOwner().name()
        );
    }
}
