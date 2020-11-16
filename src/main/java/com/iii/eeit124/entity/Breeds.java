package com.iii.eeit124.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "breeds")
@Component
public class Breeds {

	private Integer breedId;
	private String family;
	private String breed;
	private Date createAt;
	private Date updatedAt;
	private Date deleteAt;
	
	@Id @Column(name = "BREED_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getBreedId() {
		return breedId;
	}
	public void setBreedId(Integer breedId) {
		this.breedId = breedId;
	}
	@Column(name = "FAMILY")
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	@Column(name = "BREED")
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	@Column(name = "CREATED_AT")
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	@Column(name = "UPDATED_AT")
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	@Column(name = "DELETED_AT")
	public Date getDeleteAt() {
		return deleteAt;
	}
	public void setDeleteAt(Date deleteAt) {
		this.deleteAt = deleteAt;
	}
	
	
}
