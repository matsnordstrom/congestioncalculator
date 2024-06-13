package com.codetest.congestioncalculator.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaxCalculationRequest {
  private Vehicle vehicle;
  private LocalDateTime[] dates;
}
