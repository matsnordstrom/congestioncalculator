package com.codetest.congestioncalculator.utils;

import com.codetest.congestioncalculator.model.Vehicle;
import java.time.*;
import java.util.*;

public class Utils {
  private static Map<String, Integer> tollFreeVehicles = new HashMap<>();

  static {
    tollFreeVehicles.put("Motorcycle", 0);
    tollFreeVehicles.put("Tractor", 1);
    tollFreeVehicles.put("Emergency", 2);
    tollFreeVehicles.put("Diplomat", 3);
    tollFreeVehicles.put("Foreign", 4);
    tollFreeVehicles.put("Military", 5);
  }

  public static Map<LocalDate, List<LocalDateTime>> groupByDate(List<LocalDateTime> dateTimeList) {
    Map<LocalDate, List<LocalDateTime>> dateMap = new HashMap<>();

    for (LocalDateTime dateTime : dateTimeList) {
      LocalDate date = dateTime.toLocalDate();
      dateMap.computeIfAbsent(date, k -> new ArrayList<>()).add(dateTime);
    }
    return dateMap;
  }

  public static int getTollFee(LocalDateTime date, Vehicle vehicle) {
    if (isTollFreeDate(date) || isTollFreeVehicle(vehicle)) {
      return 0;
    }
    LocalTime time = date.toLocalTime();

    if (time.isBefore(LocalTime.of(6, 0))) return 0;
    else if (time.isBefore(LocalTime.of(6, 30))) return 8;
    else if (time.isBefore(LocalTime.of(7, 0))) return 13;
    else if (time.isBefore(LocalTime.of(8, 0))) return 18;
    else if (time.isBefore(LocalTime.of(8, 30))) return 13;
    else if (time.isBefore(LocalTime.of(15, 0))) return 8;
    else if (time.isBefore(LocalTime.of(15, 30))) return 13;
    else if (time.isBefore(LocalTime.of(17, 0))) return 18;
    else if (time.isBefore(LocalTime.of(18, 0))) return 13;
    else if (time.isBefore(LocalTime.of(18, 30))) return 0;
    else return 0;
  }

  private static boolean isTollFreeVehicle(Vehicle vehicle) {
    if (vehicle == null) {
      return false;
    }
    String vehicleType = vehicle.getVehicleType();
    return tollFreeVehicles.containsKey(vehicleType);
  }

  private static Boolean isTollFreeDate(LocalDateTime date) {

    DayOfWeek dayOfWeek = date.getDayOfWeek();
    int year = date.getYear();
    Month month = date.getMonth();
    int dayOfMonth = date.getDayOfMonth();

    if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
      return true;
    }

    if (year == 2013) {
      if ((month == Month.JANUARY && dayOfMonth == 1)
          || (month == Month.APRIL && (dayOfMonth == 28 || dayOfMonth == 29))
          || (month == Month.MAY && (dayOfMonth == 1 || dayOfMonth == 30))
          || (month == Month.JUNE && (dayOfMonth == 1 || dayOfMonth == 8 || dayOfMonth == 9))
          || (month == Month.JULY)
          || (month == Month.NOVEMBER && dayOfMonth == 1)
          || (month == Month.DECEMBER
              && (dayOfMonth == 24 || dayOfMonth == 25 || dayOfMonth == 26 || dayOfMonth == 31))) {
        return true;
      }
    }
    return false;
  }

  public static Integer getTotalCost(Map<LocalDate, Integer> costPerDate) {
    return costPerDate.values().stream().mapToInt(Integer::intValue).sum();
  }
}
