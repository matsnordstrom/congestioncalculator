package com.codetest.congestioncalculator.model;

import java.time.LocalDate;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TaxCalculationResponse {
  private Map<LocalDate, Integer> costPerDate;
  private Integer totalCost;
}
