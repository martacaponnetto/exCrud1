package com.example.exCrud1.Repository;

import com.example.exCrud1.Entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
