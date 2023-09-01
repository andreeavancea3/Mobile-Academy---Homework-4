package com.example.demo.service;

import com.example.demo.model.Cars;
import com.example.demo.model.CarsDTO;
import com.example.demo.repository.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarsService {
    @Autowired
    CarsRepository carsRepository;

    public List<Cars> getAllCars() {
        return carsRepository.findAll();
    }
    public List<CarsDTO> getAllCarsAsDTO() {
        List<Cars> cars = carsRepository.findAll();

        List<CarsDTO> carsDTOs = cars.stream()
                .map(this::convertCarToDTO)
                .collect(Collectors.toList());

        return carsDTOs;
    }

    private CarsDTO convertCarToDTO(Cars car) {
        CarsDTO carsDTO = new CarsDTO();
        carsDTO.setName(car.getName());
        carsDTO.setModel(car.getModel());

        return carsDTO;
    }
    public Cars createCar(Cars car) {
        return carsRepository.save(car);
    }
}
