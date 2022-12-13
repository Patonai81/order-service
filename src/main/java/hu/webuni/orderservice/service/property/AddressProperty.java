package hu.webuni.orderservice.service.property;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ConfigurationProperties(prefix = "pickup")
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AddressProperty {

    private String country;
    private String postalCode;
    private String city;
    private String street;
    private Integer houseNumber;


}
