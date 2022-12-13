package hu.webuni.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AddressDTO {

    private Long id;
    private String country;
    private String postalCode;
    private String city;
    private String street;
    private Integer houseNumber;


}
