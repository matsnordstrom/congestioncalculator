# Congestion Tax Calculator

## Introduction

This application calculates the Congestion tax cost for a vehicle, based on timestamps when the vehicle passed tax
gates.
___

## Usage

### Run Application

Run the _CongestionCalculatorApplication_, either directly in your preferred IDE or from commandline

```
mvn spring-boot:run
```

The Application is now launched at

[localhost:8080](http://localhost:8080)

### Request

Place the Request to

[localhost:8080/congestion/calculate-tax](localhost:8080/congestion/calculate-tax)

The application will accept POST-requests with a body containing vehicle type and a list of dates.

The body shall be of JSON format according to below example.

```
{
  "vehicle": {
    "vehicleType": "Car"
  },
  "dates": [
    "2013-01-14T21:00:00",
    "2013-01-15T21:00:00",
    "2013-02-07T06:23:27",
    "2013-02-07T15:27:00",
    "2013-02-08T06:27:00",
    "2013-02-08T06:20:27",
    "2013-02-08T14:35:00",
    "2013-02-08T15:29:00",
    "2013-02-08T15:47:00",
    "2013-02-08T16:01:00",
    "2013-02-08T16:48:00",
    "2013-02-08T17:49:00",
    "2013-02-08T18:29:00",
    "2013-02-08T18:35:00",
    "2013-03-26T14:25:00",
    "2013-03-28T14:07:27"
  ]
}
```

### Response

The response will contain information on cost per day and total cost, according to below example

```
{
    "costPerDate": {
        "2013-01-14": 0,
        "2013-01-15": 0,
        "2013-03-28": 8,
        "2013-03-26": 8,
        "2013-02-08": 60,
        "2013-02-07": 21
    },
    "totalCost": 97
}
```

## ENJOY!

