package com.ute.farmhome.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "fruit")
public class Fruit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	float weight;
	float remainingWeight;
	String unit;
	@OneToMany(mappedBy = "fruit", cascade = CascadeType.ALL)
	List<FruitImage> images = new ArrayList<>();
	LocalDate date;
	String description;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	User farmer;
	Boolean popular;
	String season;
}
