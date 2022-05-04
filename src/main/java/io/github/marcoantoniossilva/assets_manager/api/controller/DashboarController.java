package io.github.marcoantoniossilva.assets_manager.api.controller;

import io.github.marcoantoniossilva.assets_manager.domain.model.DashboardModel;
import io.github.marcoantoniossilva.assets_manager.domain.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboarController {

  private final DashboardService dashboardService;

  public DashboarController(DashboardService dashboardService) {
    this.dashboardService = dashboardService;
  }

  @GetMapping
  private DashboardModel get() {
    return dashboardService.get();
  }

}
