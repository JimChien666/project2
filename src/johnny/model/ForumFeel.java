package johnny.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FORUM_FEEL")
public class ForumFeel {
	private int id;
	private int forumid;
	private int memberid;
	private int feeltypeid;

	@Id @Column(name = "ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "FORUM_ID")
	public int getForumid() {
		return forumid;
	}

	public void setForumid(int forumid) {
		this.forumid = forumid;
	}

	@Column(name = "MEMBER_ID")
	public int getMemberid() {
		return memberid;
	}

	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}

	@Column(name = "FEEL_TYPE_ID")
	public int getFeeltypeid() {
		return feeltypeid;
	}

	public void setFeeltypeid(int feeltypeid) {
		this.feeltypeid = feeltypeid;
	}

}
