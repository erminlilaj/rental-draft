package it.linksmt.rental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVehicleRequest {
    private String model;
    private String color;
    private double dailyFee;

}
