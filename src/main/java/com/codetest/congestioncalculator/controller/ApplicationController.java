package com.codetest.congestioncalculator.controller;

import com.codetest.congestioncalculator.model.TaxCalculationRequest;
import com.codetest.congestioncalculator.model.TaxCalculationResponse;
import com.codetest.congestioncalculator.service.CongestionTaxCalculator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/congestion")
public class ApplicationController {

  private final CongestionTaxCalculator calculator;

  public ApplicationController(CongestionTaxCalculator calculator) {
    this.calculator = calculator;
  }

  @PostMapping("/calculate-tax")
  public ResponseEntity<TaxCalculationResponse> calculateTax(
      @RequestBody TaxCalculationRequest request) throws Exception {
    return new ResponseEntity<>(
        calculator.getTax(request.getVehicle(), request.getDates()), HttpStatus.OK);
  }
}
