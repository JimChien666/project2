package nn.vo;

import java.io.Serializable;

public class AttractionIntroduction implements Serializable {
	private int id;
	private String name;
	private String city;
	private String address;
	private String tel;
	private String coverImgUrl;
	private String contentImgUrl;
	
	public AttractionIntroduction() {};
	
	public AttractionIntroduction(int id, String name, String city, String address, String tel, String coverImgUrl,
			String contentImgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.address = address;
		this.tel = tel;
		this.coverImgUrl = coverImgUrl;
		this.contentImgUrl = contentImgUrl;
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
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCoverImgUrl() {
		return coverImgUrl;
	}
	public void setCoverImgUrl(String coverImgUrl) {
		this.coverImgUrl = coverImgUrl;
	}
	public String getContentImgUrl() {
		return contentImgUrl;
	}
	public void setContentImgUrl(String contentImgUrl) {
		this.contentImgUrl = contentImgUrl;
	}
	
	
}
