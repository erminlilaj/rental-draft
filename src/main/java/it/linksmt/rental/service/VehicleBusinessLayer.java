package it.linksmt.rental.service;

import it.linksmt.rental.dto.GetAvailableVehiclesRequest;
import it.linksmt.rental.dto.ReservationResponse;
import it.linksmt.rental.dto.VehicleResponse;

import java.util.List;

public interface VehicleBusinessLayer {
    List<ReservationResponse> deleteVehicle(Long id);
    List<ReservationResponse> activateOrFutureReservationOfVehicle(Long id);

    List<VehicleResponse> getAvailableVehiclesByDateRange(GetAvailableVehiclesRequest getAvailableVehiclesRequest);
}
