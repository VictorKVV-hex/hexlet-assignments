package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Value
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    public String serialize() {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return json;
    }
    public static Car unserialize(String strJSON) {
        Car car = null;
        try {
            car = new ObjectMapper().readValue(strJSON, Car.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return car;
    }
}
