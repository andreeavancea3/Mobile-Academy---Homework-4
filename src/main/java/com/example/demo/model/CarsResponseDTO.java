package com.example.demo.model;

import java.util.List;

public class CarsResponseDTO {
    private boolean darkMode;
    private List<CarsDTO> cars;

    public boolean isDarkMode() {
        return darkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }

    public List<CarsDTO> getCars() {
        return cars;
    }

    public void setCars(List<CarsDTO> cars) {
        this.cars = cars;
    }
}
