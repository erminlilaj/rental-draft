package it.linksmt.rental.service.serviceImpl;

import it.linksmt.rental.dto.CreateVehicleRequest;
import it.linksmt.rental.dto.UpdateVehicleRequest;
import it.linksmt.rental.entity.VehicleEntity;
import it.linksmt.rental.repository.VehicleRepository;
import it.linksmt.rental.security.SecurityBean;
import it.linksmt.rental.security.SecurityContext;
import it.linksmt.rental.service.AuthenticationService;
import it.linksmt.rental.service.VehicleService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VehicleServiceImpl implements VehicleService {

    VehicleRepository vehicleRepository;
    public AuthenticationService authenticationService;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, AuthenticationService authenticationService) {
        this.vehicleRepository = vehicleRepository;
        this.authenticationService = authenticationService;
    }

    @Override
    public VehicleEntity createVehicle(CreateVehicleRequest createVehicleRequest) {
        SecurityBean currentUser = SecurityContext.get();

        if (!authenticationService.isAdmin(currentUser)) {
            throw new AccessDeniedException("Only admins can create vehicles.");
        }
        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setBrand(createVehicleRequest.getBrand());
        vehicleEntity.setModel(createVehicleRequest.getModel());
        vehicleEntity.setYear(createVehicleRequest.getYear());
        vehicleEntity.setGearboxType(createVehicleRequest.getGearboxType());
        vehicleEntity.setFuelType(createVehicleRequest.getFuelType());
        vehicleEntity.setColor(createVehicleRequest.getColor());
        vehicleEntity.setVehicleStatus(createVehicleRequest.getVehicleStatus());
        vehicleEntity.setDailyFee(createVehicleRequest.getDailyFee());
       return vehicleRepository.save(vehicleEntity);
    }

    @Override
    public List<VehicleEntity> findAllVehicle() {
        return vehicleRepository.findAll();
    }

    @Override
    public VehicleEntity findVehicleById(Long id) {
        //if (vehicleRepository.existsById(id)) {
            return vehicleRepository.findById(id).orElse(null);

        //todo
        //return null;
    }

    @Override
    public boolean deleteVehicle(Long id) {
        SecurityBean currentUser = SecurityContext.get();

        if (!authenticationService.isAdmin(currentUser)) {
            throw new AccessDeniedException("Only admins can delete vehicles.");
        }
       if(vehicleRepository.existsById(id)) {
           vehicleRepository.deleteById(id);
           return true;
       }
       return false;
    }

    @Override
    public VehicleEntity updateVehicle(Long id, UpdateVehicleRequest updateVehicleRequest) {
        SecurityBean currentUser = SecurityContext.get();

        if (!authenticationService.isAdmin(currentUser)) {
            throw new AccessDeniedException("Only admins can update vehicles.");
        }
//        if(!vehicleRepository.existsById(id)) {
//            //todo throw exp
//            return null;
//        }
        VehicleEntity vehicle = findVehicleById(id);
        vehicle.setModel(updateVehicleRequest.getModel());
        vehicle.setColor(updateVehicleRequest.getColor());
        vehicle.setDailyFee(updateVehicleRequest.getDailyFee());
        return vehicleRepository.save(vehicle);
    }
}
