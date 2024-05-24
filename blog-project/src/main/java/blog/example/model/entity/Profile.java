package blog.example.model.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


//SpringにEntityクラスのお知らせアノテーション
@Entity
public class Profile {
	//テーブルのID
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long profileId;
	
	//テーブルコラムnameをキャメルケース
	private String name;
	
	//テーブルコラムbirthdayをキャメルケース
	private Date birthday;
	
	//テーブルコラムself_introductionをキャメルケース
	private String selfIntroduction;
	
	//テーブルコラムuser_idをキャメルケース
	private Long userId;

	//空のコンストラクタ
	public Profile() {
	}

	//コンストラクタ
	public Profile(String name, Date birthday, String selfIntroduction, Long userId) {
		this.name = name;
		this.birthday = birthday;
		this.selfIntroduction = selfIntroduction;
		this.userId = userId;
	}

	//ゲッターとセッター
	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSelfIntroduction() {
		return selfIntroduction;
	}

	public void setSelfIntroduction(String selfIntroduction) {
		this.selfIntroduction = selfIntroduction;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
