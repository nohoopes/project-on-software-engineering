package com.ute.farmhome.mapper.implement;

import com.ute.farmhome.dto.FruitDTO;
import com.ute.farmhome.dto.FruitShowDTO;
import com.ute.farmhome.entity.Fruit;
import com.ute.farmhome.exception.ResourceNotFound;
import com.ute.farmhome.mapper.FruitMapper;
import com.ute.farmhome.mapper.UserMapper;
import com.ute.farmhome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class FruitMapperImplement implements FruitMapper {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Override
    public Fruit map(FruitDTO dto) {
        Fruit fruit = new Fruit();
        fruit.setId(dto.getId());
        fruit.setName(dto.getName());
        fruit.setWeight(dto.getWeight());
        fruit.setRemainingWeight(dto.getRemainingWeight());
        fruit.setUnit(dto.getUnit());
        fruit.setImages(dto.getImages());
        fruit.setDate(LocalDate.parse(dto.getDate()));
        fruit.setDescription(dto.getDescription());
        fruit.setFarmer(userRepository.findById(dto.getFarmer().getId())
                .orElseThrow(() -> new ResourceNotFound("User", "id", String.valueOf(dto.getFarmer().getId()))));
        fruit.setSeason(dto.getSeason());
        fruit.setPopular(dto.getPopular());
        return fruit;
    }

    @Override
    public FruitDTO map(Fruit fruit) {
        FruitDTO dto = new FruitDTO();
        dto.setId(fruit.getId());
        dto.setName(fruit.getName());
        dto.setWeight(fruit.getWeight());
        dto.setRemainingWeight(fruit.getRemainingWeight());
        dto.setUnit(fruit.getUnit());
        dto.setImages(fruit.getImages());
        dto.setDate(String.valueOf(fruit.getDate()));
        dto.setDescription(fruit.getDescription());
        dto.setFarmer(userMapper.mapToShow(fruit.getFarmer()));
        dto.setSeason(fruit.getSeason());
        dto.setPopular(fruit.getPopular());
        return dto;
    }

    @Override
    public FruitShowDTO mapToShow(Fruit fruit) {
        FruitShowDTO dto = new FruitShowDTO();
        dto.setId(fruit.getId());
        dto.setName(fruit.getName());
        dto.setWeight(fruit.getWeight());
        dto.setUnit(fruit.getUnit());
        dto.setRemainingWeight(fruit.getRemainingWeight());
        dto.setImages(fruit.getImages());
        dto.setDate(fruit.getDate());
        dto.setFarmer(userMapper.mapToShow(fruit.getFarmer()));
        dto.setDescription(fruit.getDescription());
        dto.setSeason(fruit.getSeason());
        dto.setPopular(fruit.getPopular());
        return dto;
    }
}
