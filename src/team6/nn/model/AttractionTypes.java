package team6.nn.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "Attraction_Types")
public class AttractionTypes {
	private int id;
	private String name;
	private Set<Attractions> attractions = new HashSet<Attractions>();
	
	
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "attractions", cascade = CascadeType.ALL)
	public Set<Attractions> getAttractions() {
		return attractions;
	}
	public void setAttractions(Set<Attractions> attractions) {
		this.attractions = attractions;
	}
	
	
}