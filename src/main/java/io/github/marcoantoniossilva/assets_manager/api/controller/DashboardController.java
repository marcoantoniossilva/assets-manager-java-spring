package io.github.marcoantoniossilva.assets_manager.api.controller;

import io.github.marcoantoniossilva.assets_manager.domain.model.ChartItem;
import io.github.marcoantoniossilva.assets_manager.domain.model.DashboardModel;
import io.github.marcoantoniossilva.assets_manager.domain.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

  private final DashboardService dashboardService;

  public DashboardController(DashboardService dashboardService) {
    this.dashboardService = dashboardService;
  }

  @GetMapping
  private DashboardModel get() {
    return dashboardService.get();
  }

  @GetMapping("chart")
  private List<ChartItem> getChart() {
    return dashboardService.getChart();
  }

}
