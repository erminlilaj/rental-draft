package it.linksmt.rental.service;

import it.linksmt.rental.dto.CreateReservationRequest;
import it.linksmt.rental.dto.ReservationResponse;
import it.linksmt.rental.dto.ReservationStatisticsResponse;
import it.linksmt.rental.entity.ReservationEntity;

import java.util.List;

public interface ReservationService {


    ReservationResponse createReservation(CreateReservationRequest reservationRequest);
    ReservationEntity getReservationById(Long id);
    List<ReservationEntity> findAllReservations();
    ReservationResponse cancelReservation(Long id);

    List<ReservationStatisticsResponse> getReservationStatistics(String date);
}
