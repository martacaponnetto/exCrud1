package com.example.exCrud1.Controllers;

import com.example.exCrud1.Entities.Car;
import com.example.exCrud1.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.servlet.function.ServerResponse.ok;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    CarRepository carRepository; // istanza del repository viene iniettata nel controller senza bisogno di un costruttore
    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carRepository.save(car); //metodo per la creax di un'auto
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carRepository.findAll(); //metodo per ottenere tutte le auto
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id) {
        return carRepository.findById(id).orElse(new Car()); //metodo per una singola auto tramite id
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCarType(@PathVariable Long id, @RequestParam String type) { //aggiorna tipo auto con id
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            car.setType(type);
            carRepository.save(car);
            return new ResponseEntity<>(car, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarById(@PathVariable Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("car not found by id : " + id );
        }
    }

    @DeleteMapping("/cars")
    public ResponseEntity<String> deleteAllCars() {
        carRepository.deleteAll();
        return ResponseEntity.ok("All cars deleted successfully");

    }

}


