package com.iii.eeit124.entity;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "animals_files")
@Component
public class AnimalsFiles implements Serializable{
	
	private Integer animalsFilesId;
	private String fileType;
	private String fileName;
	private Integer animalId;
	private Blob fileBlob;
	private Animals animals;
	
	public AnimalsFiles() {}

	public AnimalsFiles(String fileType, String fileName, Blob fileBlob) {
		this.fileType = fileType;
		this.fileName = fileName;
		this.fileBlob = fileBlob;
	}

	@Id
	@Column(name = "Animals_Files_Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getAnimalsFilesId() {
		return animalsFilesId;
	}

	public void setAnimalsFilesId(Integer animalsFilesId) {
		this.animalsFilesId = animalsFilesId;
	}

	@Column(name = "File_Type")
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Column(name = "File_Name")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

//	@Column(name = "Animal_Id")
	@Transient
	public Integer getAnimalId() {
		return animalId;
	}

	public void setAnimalId(Integer animalId) {
		this.animalId = animalId;
	}

	@Column(name = "File_Blob")
	public Blob getFileBlob() {
		return fileBlob;
	}

	public void setFileBlob(Blob fileBlob) {
		this.fileBlob = fileBlob;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ANIMAL_ID")
	public Animals getAnimals() {
		return animals;
	}

	public void setAnimals(Animals animals) {
		this.animals = animals;
	}

	@Override
	public String toString() {
		return "AnimalsFiles [animalsFilesId=" + animalsFilesId + ", fileType=" + fileType + ", fileName=" + fileName
				+ ", animalId=" + animalId + ", fileBlob=" + fileBlob + ", animals=" + animals + "]";
	};
}
