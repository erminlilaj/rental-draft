package it.linksmt.rental.controller;

import it.linksmt.rental.dto.CreateVehicleRequest;
import it.linksmt.rental.dto.UpdateVehicleRequest;
import it.linksmt.rental.entity.VehicleEntity;
import it.linksmt.rental.enums.UserType;
import it.linksmt.rental.security.SecurityBean;
import it.linksmt.rental.security.SecurityContext;
import it.linksmt.rental.service.AuthenticationService;
import it.linksmt.rental.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;


    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<VehicleEntity> createVehicle(@RequestBody CreateVehicleRequest createVehicleRequest) {
     VehicleEntity createdVehicle= vehicleService.createVehicle(createVehicleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVehicle);
    }

    @GetMapping
    public ResponseEntity<List<VehicleEntity>> getAllVehicles() {
        List<VehicleEntity> vehicleList = vehicleService.findAllVehicle();
        if (vehicleList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(vehicleList); // Return an empty list
        }
        return ResponseEntity.status(HttpStatus.OK).body(vehicleList);
    }


    @GetMapping("/{id}")
    public ResponseEntity<VehicleEntity> getVehicleById(@PathVariable Long id) {
        VehicleEntity vehicle = vehicleService.findVehicleById(id);
        if (vehicle == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // No body for NOT_FOUND
        }
        return ResponseEntity.status(HttpStatus.OK).body(vehicle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        if (vehicleService.deleteVehicle(id)) {
            return ResponseEntity.status(HttpStatus.OK).build(); // No body for success
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // No body for NOT_FOUND
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleEntity> updateVehicle(@PathVariable Long id, @RequestBody UpdateVehicleRequest updateVehicleRequest) {
        VehicleEntity vehicle= vehicleService.updateVehicle(id,updateVehicleRequest);

        return ResponseEntity.status(HttpStatus.OK).body(vehicle);
}


}
