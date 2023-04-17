package com.team.project.service.remote;

import com.team.project.entity.Application;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("application-service")
public interface RemoteApplicationService {

    @GetMapping("application-service/application/{id}")
    Application getApplicationById();

    @GetMapping("application-service/application")
    List<Application> getAllApplications();

    @GetMapping("application-service/application/status/{status}")
    List<Application> getApplicationsByStatus(@PathVariable String status);
}
