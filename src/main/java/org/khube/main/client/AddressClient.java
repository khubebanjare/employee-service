package org.khube.main.client;

import org.khube.main.dto.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "address-service", url = "${service.url.address-service}")
public interface AddressClient {

    @GetMapping("/fetch/{addressId}")
    AddressDto getAddressById(@PathVariable("addressId") Long addressId);

}
