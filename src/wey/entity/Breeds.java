package wey.entity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "breeds")
public class Breeds {
	private int breedId;
	private String family;
	private String breed;
	private Date createAt;
	private Date updatedAt;
	private Date deleteAt;
	private Animals animals;
	
	@Id @Column(name = "BREED_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getBreedId() {
		return breedId;
	}
	public void setBreedId(int breedId) {
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
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "animals")
	public Animals getAnimals() {
		return animals;
	}
	public void setAnimals(Animals animals) {
		this.animals = animals;
	}
}
