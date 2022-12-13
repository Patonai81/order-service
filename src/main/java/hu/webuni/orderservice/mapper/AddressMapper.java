package hu.webuni.orderservice.mapper;

import hu.webuni.orderservice.service.property.AddressProperty;

import hu.webuni.orderservice.wsclient.AddressDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDTO toAddressDTO(AddressProperty addressProperty);
}
