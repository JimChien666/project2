package max.entity;

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
import javax.persistence.Transient;

import jim.entity.Categories;

import team6.johnny.model.Article;
import team6.johnny.model.BannedList;
import team6.johnny.model.Comments;
import team6.johnny.model.ForumFeel;
import team6.johnny.model.Forums;
import team6.johnny.model.MemberOption;
import wey.entity.AdoptionRecords;
import wey.entity.Animals;

@Entity
@Table(name ="members")
public class Members {
	private int id;
	private String memberType;
	private String name;
	private String sex;
	private String tel;
	private String income;
	private String account;
	private String password;
	private String email;
	private String address;
	private int adoptedLevelId;
	
    //Animals.Member_Id
	private Animals animals;
	//AdoptionRecords.Member_Id
	private AdoptionRecords adoptionRecords;
	//Article
	private Article article;
//	//AttractionBean
//	private AttractionBean attractionBean;
	//BannedList
	private BannedList bannedList;
	//Comments
	private Comments comments;
	//ForumFeel
	private ForumFeel forumFeel;
	//Forums
	private Forums forums;
	//MemberOption
	private MemberOption memberOption;
	//Categories
	private Categories categories;
	//ActivityApply
//	private ActivityApply activityApply;
	//Favorites
	private Favorites favorites;
	//Files
	private Files files;
	//Favorites
//	private Set<Animals> Animals = new HashSet<Animals>();
//	private Set<Tags> tags = new HashSet<Tags>();
//	private Set<AttractionComments> attractionComments = new HashSet<AttractionComments>();
//	
	
	
    @Id @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "MEMBERTYPE")
	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "SEX")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(name = "TEL")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	@Column(name = "INCOME")
	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}
	@Column(name = "ACCOUNT")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Transient
	public int getAdoptedLevelId() {
		return adoptedLevelId;
	}
	public void setAdoptedLevelId(int adoptedLevelId) {
		this.adoptedLevelId = adoptedLevelId;
	}

	//  關聯
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "animals")
//	public Animals getAnimals() {
//		return animals;
//	}
//	public void setAnimals(Animals animals) {
//		this.animals = animals;
//	}
//	
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "adoptionRecords")
//	public AdoptionRecords getAdoptionRecords() {
//		return adoptionRecords;
//	}
//	public void setAdoptionRecords(AdoptionRecords adoptionRecords) {
//		this.adoptionRecords = adoptionRecords;
//	}
//	
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "article")
//	public Article get() {
//		return article;
//	}
//	public void setArticle(Article article) {
//		this.article = article;
//	}
	
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "attractionBean")
//	public AttractionBean getAttractionBean() {
//		return attractionBean;
//	}
//	public void setAttractionBean(AttractionBean attractionBean) {
//		this.attractionBean = attractionBean;
//	}
	
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "bannedList")
//	public BannedList getBannedList() {
//		return bannedList;
//	}
//	public void setBannedList(BannedList bannedList) {
//		this.bannedList = bannedList;
//	}
//	
//	
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "comments")
//	public Comments getComments() {
//		return comments;
//	}
//	public void setComments(Comments comments) {
//		this.comments = comments;
//	}
//	
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "forumFeel")
//	public ForumFeel getForumFeel() {
//		return forumFeel;
//	}
//	public void setForumFeel(ForumFeel forumFeel) {
//		this.forumFeel = forumFeel;
//	}
//	
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "forums")
//	public Forums getForums() {
//		return forums;
//	}
//	public void setForums(Forums forums) {
//		this.forums = forums;
//	}
//	
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "memberOption")
//	public MemberOption getMemberOption() {
//		return memberOption;
//	}
//	public void setMemberOption(MemberOption memberOption) {
//		this.memberOption = memberOption;
//	}
//	
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "categories")
//	public Categories getCategories() {
//		return categories;
//	}
//	public void setCategories(Categories categories) {
//		this.categories = categories;
//	}
//	
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "activityApply")
//	public ActivityApply getActivityApply() {
//		return activityApply;
//	}
//	public void setActivityApply(ActivityApply activityApply) {
//		this.activityApply = activityApply;
//	}
	
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "favorites")
//	public Favorites getFavorites() {
//		return favorites;
//	}
//	public void setFavorites(Favorites favorites) {
//		this.favorites = favorites;
//	}
//	
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "files")
//	public Files getFiles() {
//		return files;
//	}
//	public void setFiles(Files files) {
//		this.files = files;
//	}
//	
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(name = "ATTRACTION_TAG_REFS", joinColumns = {
//			@JoinColumn(name = "ATTRACTION_ID") }, inverseJoinColumns = { @JoinColumn(name = "TAG_ID") })
//	public Set<Tags> getTags() {
//		return tags;
//	}
//
//	public void setTags(Set<Tags> tags) {
//		this.tags = tags;
//	}
	
	
}
