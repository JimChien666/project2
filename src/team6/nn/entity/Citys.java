package team6.nn.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
@Table(name = "CITYS")
public class Citys implements Serializable {
	private int id;
	private String name;
	private Set<Attractions> attractions = new HashSet<Attractions>();;
	
	public Citys() {};
	public Citys(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "city", cascade = CascadeType.ALL)
	public Set<Attractions> getAttractions() {
		return attractions;
	}
	public void setAttractions(Set<Attractions> attractions) {
		this.attractions = attractions;
	}
	
	
	
}
