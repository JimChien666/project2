package nn.vo;

import java.io.Serializable;
import java.sql.Blob;

public class AttractionIntroduction implements Serializable {
	private int id;
	private String name;
	private String city;
	private String address;
	private String tel;
	private int coverFileId;
	private int contentFileId;
	private Boolean isFavoriteAvailable;
	
	public AttractionIntroduction() {};
	
	public AttractionIntroduction(int id, String name, String city, String address, String tel, int coverFileId,
			int contentFileId,Boolean isFavoriteAvailable) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.address = address;
		this.tel = tel;
		this.coverFileId = coverFileId;
		this.contentFileId = contentFileId;
		this.isFavoriteAvailable = isFavoriteAvailable;
	}
	
	public Boolean getIsFavoriteAvailable() {
		return isFavoriteAvailable;
	}

	public void setIsFavoriteAvailable(Boolean isFavoriteAvailable) {
		this.isFavoriteAvailable = isFavoriteAvailable;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public int getCoverFileId() {
		return coverFileId;
	}

	public void setCoverFileId(int coverFileId) {
		this.coverFileId = coverFileId;
	}

	public int getContentFileId() {
		return contentFileId;
	}

	public void setContentFileId(int contentFileId) {
		this.contentFileId = contentFileId;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}


	
}
