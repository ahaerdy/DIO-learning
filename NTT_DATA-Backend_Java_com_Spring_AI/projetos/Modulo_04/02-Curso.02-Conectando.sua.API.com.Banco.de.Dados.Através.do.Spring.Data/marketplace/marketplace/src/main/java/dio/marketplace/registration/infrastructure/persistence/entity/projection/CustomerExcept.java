package dio.marketplace.registration.infrastructure.persistence.entity.projection;
import dio.marketplace.registration.infrastructure.persistence.entity.Customer;
import lombok.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "excerpt", types = Customer.class)
public interface CustomerExcerpt {

    String getFirstName();

    String getLastName();

    @Value("#{target.address?.toString()}")
    String getAddress();

}