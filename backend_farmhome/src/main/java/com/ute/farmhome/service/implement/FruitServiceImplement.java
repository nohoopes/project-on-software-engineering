package com.ute.farmhome.service.implement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ute.farmhome.dto.FileUpload;
import com.ute.farmhome.dto.FruitDTO;
import com.ute.farmhome.dto.FruitShowDTO;
import com.ute.farmhome.dto.PaginationDTO;
import com.ute.farmhome.entity.Fruit;
import com.ute.farmhome.entity.FruitImage;
import com.ute.farmhome.entity.Order;
import com.ute.farmhome.entity.User;
import com.ute.farmhome.exception.ResourceNotFound;
import com.ute.farmhome.mapper.FruitMapper;
import com.ute.farmhome.repository.FruitImageRepository;
import com.ute.farmhome.repository.FruitRepository;
import com.ute.farmhome.repository.UserRepository;
import com.ute.farmhome.service.FruitImageService;
import com.ute.farmhome.service.FruitService;
import com.ute.farmhome.service.OrderService;
import com.ute.farmhome.service.UserService;
import com.ute.farmhome.utility.UpdateFile;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FruitServiceImplement implements FruitService {
	@Autowired
	private FruitImageService fruitImageService;
	@Autowired
	private UserService userService;
	@Autowired
	private FruitRepository fruitRepository;
	@Autowired
	private FruitImageRepository fruitImageRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UpdateFile updateFile;
	@Autowired
	private FruitMapper fruitMapper;
	@Override
	public PaginationDTO getAllFruit(int no, int limit) {
		PageRequest pageRequest = PageRequest.of(no, limit);
		List<FruitShowDTO> fruits = fruitRepository.findAllFruit(pageRequest).stream().map(item -> fruitMapper.mapToShow(item)).toList();
		Page<Fruit> page = fruitRepository.findAllFruit(pageRequest);
		return new PaginationDTO(fruits, page.isFirst(), page.isLast(), page.getTotalPages(), page.getTotalElements(), page.getSize(), page.getNumber());
	}
	@Override
	public FruitShowDTO getFruitById(int id) {
		return fruitMapper.mapToShow(fruitRepository.findById(id).get());
	}
	@Override
	public FruitDTO readJson(String fruit, List<MultipartFile> images) {
		FruitDTO fruitDTO = new FruitDTO();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			fruitDTO = objectMapper.readValue(fruit, FruitDTO.class);
			if (images != null) {
				if (images.stream().count() > 0) {
					fruitDTO.setImageFiles(images);
				}
			}
		} catch (JsonMappingException e) {
			throw new RuntimeException(e);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		return fruitDTO;
	}
	@Override
	public FruitShowDTO createFruit(FruitDTO fruitDTO) {
		if (fruitDTO.getImageFiles().stream().count() > 0) {
			for (MultipartFile imageFile : fruitDTO.getImageFiles()) {
				FileUpload fileUpload = new FileUpload();
				fileUpload.setFile(imageFile);
				updateFile.update(fileUpload);
				FruitImage fruitImage = new FruitImage();
				fruitImage.setUrl(fileUpload.getOutput());
				fruitDTO.getImages().add(fruitImage);
			}
		}
		Fruit fruit = fruitMapper.map(fruitDTO);
		fruit.setRemainingWeight(fruit.getWeight());
		fruitDTO.getImages().forEach(fruitImage -> {fruitImage.setFruit(fruit);});
		return fruitMapper.mapToShow(fruitRepository.save(fruit));
	}

	@Override
	public PaginationDTO searchFruit(String name, int no, int limit) {
		Pageable pageable = PageRequest.of(no, limit);
		List<FruitShowDTO> listFruit = fruitRepository.searchByName(name, pageable).stream().map(item -> fruitMapper.mapToShow(item)).toList();
		Page<Fruit> page = fruitRepository.searchByName(name, pageable);
		return new PaginationDTO(listFruit, page.isFirst(), page.isLast(), page.getTotalPages(), page.getTotalElements(), page.getSize(), page.getNumber());
	}

	@Override
	public FruitShowDTO updateFruit(FruitDTO fruitDTO) {
		Fruit fruit = fruitRepository.findById(fruitDTO.getId())
				.orElseThrow(() -> new ResourceNotFound("fruit", "id", String.valueOf(fruitDTO.getId())));

		fruit.setName(fruitDTO.getName());
		fruit.setWeight(fruitDTO.getWeight());
		fruit.setRemainingWeight(fruit.getWeight());
		fruit.setUnit(fruitDTO.getUnit());
		fruit.setDate(LocalDate.parse(fruitDTO.getDate()));
		fruit.setSeason(fruitDTO.getSeason());
		fruit.setPopular(fruitDTO.getPopular());
		if (fruitDTO.getImageFiles().stream().count() > 0) {
			List<FruitImage> fruitImages = new ArrayList<>();
			for (MultipartFile imageFile : fruitDTO.getImageFiles()) {
				FileUpload fileUpload = new FileUpload();
				fileUpload.setFile(imageFile);
				fruit.getImages().forEach(fruitImage -> {
					fruitImageService.deleteImageById(fruitImage.getId());
				});
				updateFile.update(fileUpload);
				FruitImage fruitImage = new FruitImage();
				fruitImage.setUrl(fileUpload.getOutput());
				fruitImage.setFruit(fruit);
				fruitImages.add(fruitImage);
			}
			fruit.setImages(fruitImages);
		}
		return fruitMapper.mapToShow(fruitRepository.save(fruit));
	}

	@Override
	public PaginationDTO getFruitByUserId(int id, int no, int limit) {
		Pageable pageable = PageRequest.of(no, limit);
		List<?> listFruit = fruitRepository.getFruitByUserId(id, pageable).stream().map(item -> fruitMapper.mapToShow(item)).toList();
		Page<?> page = fruitRepository.getFruitByUserId(id, pageable);
		return new PaginationDTO(listFruit, page.isFirst(), page.isLast(), page.getTotalPages(), page.getTotalElements(), page.getSize(), page.getNumber());
	}

	@Override
	public PaginationDTO filterPaging(String name, Float amount, List<String> seasonList, Boolean popular, String order, int no, int limit) {
		Pageable pageable = PageRequest.of(no, limit);
		List<?> listFruit = fruitRepository.filterFruit(name, amount, seasonList, popular, order, pageable).stream().map(item -> fruitMapper.mapToShow(item)).toList();
		Page<Fruit> page = fruitRepository.filterFruit(name, amount, seasonList, popular, order, pageable);
		return new PaginationDTO(listFruit, page.isFirst(), page.isLast(), page.getTotalPages(), page.getTotalElements(), page.getSize(), page.getNumber());
	}

	@Override
	public Fruit findFruitById(int id) {
		return fruitRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Fruit", "id", String.valueOf(id)));
	}

	@Override
	public void deleteById(int id) {
		Fruit fruit = fruitRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Fruit", "id", String.valueOf(id)));
		fruit.getImages().forEach(item -> {
			fruitImageService.deleteImageById(item.getId());
		});
		fruitRepository.deleteById(id);
	}

	@Override
	public void save(Fruit fruit) {
		fruitRepository.save(fruit);
	}
}
