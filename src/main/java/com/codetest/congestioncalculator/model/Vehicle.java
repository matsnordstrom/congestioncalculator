package com.codetest.congestioncalculator.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "vehicleType")
@JsonSubTypes({
  @JsonSubTypes.Type(value = Car.class, name = "Car"),
  @JsonSubTypes.Type(value = Motorbike.class, name = "Motorbike"),
  @JsonSubTypes.Type(value = Diplomat.class, name = "Diplomat"),
  @JsonSubTypes.Type(value = Foreign.class, name = "Foreign"),
  @JsonSubTypes.Type(value = Emergency.class, name = "Emergency"),
  @JsonSubTypes.Type(value = Tractor.class, name = "Tractor"),
  @JsonSubTypes.Type(value = Military.class, name = "Military"),
})
public interface Vehicle {
  String getVehicleType();
}
