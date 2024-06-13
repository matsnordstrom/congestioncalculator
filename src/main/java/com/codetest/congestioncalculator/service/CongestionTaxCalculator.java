package com.codetest.congestioncalculator.service;

import com.codetest.congestioncalculator.exception.GeneralException;
import com.codetest.congestioncalculator.model.TaxCalculationResponse;
import com.codetest.congestioncalculator.model.Vehicle;
import com.codetest.congestioncalculator.utils.Utils;
import java.time.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CongestionTaxCalculator {

  public static final int DAILY_MAX_FEE = 60;
  public static final int FEE_SEQUENCE_LENGTH_MIN = 60;
  private static final Logger LOGGER = LoggerFactory.getLogger(CongestionTaxCalculator.class);

  public TaxCalculationResponse getTax(Vehicle vehicle, LocalDateTime[] dates)
      throws GeneralException {
    try {
      List<LocalDateTime> datesList = Arrays.asList(dates);
      Map<LocalDate, List<LocalDateTime>> groupedDates = Utils.groupByDate(datesList);
      Map<LocalDate, Integer> costPerDate = new HashMap<>();

      for (List<LocalDateTime> list : groupedDates.values()) {
        Integer cost = getCost(list, vehicle);
        costPerDate.put(list.getFirst().toLocalDate(), cost);
      }

      return TaxCalculationResponse.builder()
          .costPerDate(costPerDate)
          .totalCost(Utils.getTotalCost(costPerDate))
          .build();

    } catch (Exception e) {
      throw new GeneralException("Could not calculate Fee");
    }
  }

  private Integer getCost(List<LocalDateTime> datesList, Vehicle vehicle) {
    LocalDateTime sequenceStart = datesList.getFirst();
    int tempFee = Utils.getTollFee(sequenceStart, vehicle);
    int totalFee = 0;

    for (int i = 1; i < datesList.size(); i++) {
      LocalDateTime nextEntry = datesList.get(i);
      int nextFee = Utils.getTollFee(nextEntry, vehicle);
      Duration duration = Duration.between(sequenceStart, nextEntry);
      if (duration.toMinutes() <= FEE_SEQUENCE_LENGTH_MIN) {
        if (nextFee > tempFee) {
          tempFee = nextFee;
        }
      } else {
        sequenceStart = nextEntry;
        totalFee += tempFee;
        tempFee = nextFee;
      }
    }
    totalFee += tempFee;

    if (totalFee > DAILY_MAX_FEE) {
      totalFee = DAILY_MAX_FEE;
    }
    return totalFee;
  }
}
