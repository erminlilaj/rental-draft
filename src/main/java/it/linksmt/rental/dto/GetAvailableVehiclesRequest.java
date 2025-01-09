package it.linksmt.rental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAvailableVehiclesRequest {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
