package wey.service;

import java.util.List;

import wey.entity.Animals;

public interface IAnimalsService {
	public Animals create(Animals animals);
	public Animals select(int animalId);
	public List<Animals> readAll();
}
