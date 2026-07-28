package dio.proposalmanagement.proposal.infrastructure.http.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import dio.proposalmanagement.proposal.application.output.ProposalOutput;
import dio.proposalmanagement.proposal.domain.Proposal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProposalResponse(String id, String title, String description, OwnerResponse owner) {
    public record OwnerResponse(String id, String name) {

    }

    public static ProposalResponse from(ProposalOutput output) {
        return new ProposalResponse(
                output.id(),
                output.title(),
                output.description().orElse(null),
                new OwnerResponse(output.ownerId(), output.ownerName())
        );
    }
}