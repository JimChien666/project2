package tw.wey.service;

import java.util.List;

import tw.wey.entity.Animals;

public interface IAnimalsService {
	public Animals create(Animals animals);
	public Animals read(int animalsId);
	public List<Animals> readAll();
	public Animals update(Animals animals);
	public boolean delete(int animalsId);
}
