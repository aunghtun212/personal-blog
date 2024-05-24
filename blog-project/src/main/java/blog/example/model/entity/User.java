package blog.example.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//SpringにEntityクラスのお知らせアノテーション
@Entity
//Spring にテーブルの名前お知らせアノテーション
@Table(name="users")
public class User {
	//テーブルのID
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	
	//テーブルコラムuser_nameをキャメルケース
	private String userName;
	
	//テーブルコラムuser_emailをキャメルケース
	private String userEmail;
	
	//テーブルコラムpasswordをキャメルケース
	private String password;

	//空のコンストラクタ
	public User() {
	}

	//コンストラクタ
	public User(String userName, String userEmail, String password) {
		this.userName = userName;
		this.userEmail = userEmail;
		this.password = password;
	}

	//ゲッターとセッター
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
