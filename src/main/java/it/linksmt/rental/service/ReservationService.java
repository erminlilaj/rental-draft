package it.linksmt.rental.service;

import it.linksmt.rental.dto.*;

import java.util.HashMap;
import java.util.List;

public interface ReservationService {


    ReservationResponse createReservation(CreateReservationRequest reservationRequest);
    ReservationResponse getReservationById(Long id);
    List<ReservationResponse> findAllReservations();
    ReservationResponse cancelReservation(Long id);
    boolean checkAvailability(CreateReservationRequest reservationRequest);

    List<ReservationStatisticsResponse> getReservationStatistics(String date);

    List<ReservationResponse> getReservationListOfUser();

   List<ReservationResponse> listOfActiveOrFutureReservations(Long id);
   List<ReservationResponse> cancelReservationsOfVehicle(Long vehicleId);

    HashMap countReservationsStatuses();

    HashMap sumReservationsProfits();

    List<Long> getAvailableVehiclesByDateRange(List<VehicleResponse> availableVehicles, GetAvailableVehiclesRequest getAvailableVehiclesRequest);
}
