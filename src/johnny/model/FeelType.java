package johnny.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FEEL_TYPE")
public class FeelType {
	private int id;
	private String feelname;

	@Id @Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "FEEL_NAME")
	public String getFeelname() {
		return feelname;
	}

	public void setFeelname(String feelname) {
		this.feelname = feelname;
	}

}
