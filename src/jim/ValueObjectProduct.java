package jim;

import java.sql.Blob;
import java.util.Date;

public class ValueObjectProduct {
	private int id;
	private String name;
	private int price;
	private Blob img;
//	private String img;
	private String descript;
	private int quantity;
	private int specialPrice;
	private String rewardpoints;
	private boolean isThumb;
	private int memberId;
	private int animalTypeId;
	private int categoryId;
	private String filename;
	private String fileType;
	private String fileUrl;
	private Blob fileBlob;
	private String fileName;

	public ValueObjectProduct() {
	}
	public ValueObjectProduct(String name, int price, Blob img, String descript, int quantity, int specialPrice,
	String rewardpoints, boolean isThumb, int memberId, int animalTypeId, int categoryId,String fileType,
	String fileUrl, Blob fileBlob) {

		this.name = name;
		this.price = price;
		this.img = img;
		this.descript = descript;
		this.quantity = quantity;
		this.specialPrice = specialPrice;
		this.rewardpoints = rewardpoints;
		this.isThumb = isThumb;
		this.memberId = memberId;
		this.animalTypeId = animalTypeId;
		this.categoryId = categoryId;
		this.fileType = fileType;
		this.fileUrl = fileUrl;
		this.fileBlob = fileBlob;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Blob getImg() {
		return img;
	}

	public void setImg(Blob img) {
		this.img = img;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSpecialPrice() {
		return specialPrice;
	}

	public void setSpecialPrice(int specialPrice) {
		this.specialPrice = specialPrice;
	}

	public String getRewardpoints() {
		return rewardpoints;
	}

	public void setRewardpoints(String rewardpoints) {
		this.rewardpoints = rewardpoints;
	}

	public boolean getIsThumb() {
		return isThumb;
	}

	public void setIsThumb(boolean isThumb) {
		this.isThumb = isThumb;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getAnimalTypeId() {
		return animalTypeId;
	}

	public void setAnimalTypeId(int animalTypeId) {
		this.animalTypeId = animalTypeId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Blob getFileBlob() {
		return fileBlob;
	}

	public void setFileBlob(Blob fileBlob) {
		this.fileBlob = fileBlob;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



}
