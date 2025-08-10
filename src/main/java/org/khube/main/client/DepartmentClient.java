package org.khube.main.client;

import org.khube.main.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "department-service", url = "${service.url.department-service}")
public interface DepartmentClient {

    @GetMapping("/fetch/{departmentId}")
    DepartmentDto getDepartmentById(@PathVariable("departmentId") Long departmentId);
}
