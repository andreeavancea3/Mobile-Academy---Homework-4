package com.example.demo.controller;

import com.example.demo.model.Cars;
import com.example.demo.model.CarsDTO;
import com.example.demo.model.CarsResponseDTO;
import com.example.demo.service.CarsService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/dark-mode")
public class CarsController {
    @Autowired
    CarsService carsService;
    @GetMapping("/cars")
    public ResponseEntity<CarsResponseDTO> getCarsWithDarkMode(HttpServletRequest request) {
        boolean darkMode = getDarkModeFromCookie(request);
        List<CarsDTO> carsDTOS = carsService.getAllCarsAsDTO();

        CarsResponseDTO responseDTO = new CarsResponseDTO();
        responseDTO.setDarkMode(darkMode);
        responseDTO.setCars(carsDTOS);

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/dark-mode")
    public ResponseEntity<Void> setDarkMode(@RequestBody CarsResponseDTO carsResponseDTO, HttpServletResponse response) {
        boolean darkMode = carsResponseDTO.isDarkMode();
        setDarkModeCookie(response, darkMode);

        return ResponseEntity.ok().build();
    }
    private boolean getDarkModeFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("darkmode")) {
                    return Boolean.parseBoolean(cookie.getValue());
                }
            }
        }
        return false;
    }

    private void setDarkModeCookie(HttpServletResponse response, boolean darkMode) {
        Cookie cookie = new Cookie("darkmode", String.valueOf(darkMode));
        cookie.setPath("/");
        response.addCookie(cookie);
    }
    @PostMapping("/addcars")
    public List<CarsDTO> addCars() {
        List<CarsDTO> carDTOs = new ArrayList<>();

        Cars car1 = new Cars();
        car1.setName("Tesla");
        car1.setModel("Model 3");

        Cars car2 = new Cars();
        car2.setName("Dacia");
        car2.setModel("Logan");

        carsService.createCar(car1);
        carsService.createCar(car2);

        carDTOs.add(convertCarToDTO(car1));
        carDTOs.add(convertCarToDTO(car2));

        return carDTOs;
    }
    private CarsDTO convertCarToDTO(Cars car) {
        CarsDTO carDTO = new CarsDTO();
        carDTO.setName(car.getName());
        carDTO.setModel(car.getModel());
        return carDTO;
    }
}
