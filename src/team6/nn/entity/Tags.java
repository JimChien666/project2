package team6.nn.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TAGS")
public class Tags {
	private int id;
	private String name;
	private Set<Attractions> attractions = new HashSet<Attractions>();

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="ATTRACTION_TAG_REFS", joinColumns = {@JoinColumn(name="TAGS_ID")}, inverseJoinColumns = {@JoinColumn(name="ATTRACTION_ID")})
	public Set<Attractions> getAttractions() {
		return attractions;
	}

	public void setAttractions(Set<Attractions> attractions) {
		this.attractions = attractions;
	}

}
