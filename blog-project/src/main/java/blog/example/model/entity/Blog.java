 package blog.example.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//SpringにEntityクラスのお知らせアノテーション
@Entity
//Spring にテーブルの名前お知らせアノテーション
@Table(name="blog")
public class Blog {
	//テーブルのID
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long blogId;
	
	//テーブルコラムblog_titleをキャメルケース
	private  String blogTitle;
	
	//テーブルコラムcategory_nameをキャメルケース
	private String  categoryName;
	
	//テーブルコラムblog_imageをキャメルケース
	private String blogImage;
	
	//テーブルコラムarticleをキャメルケース
	private String article;
	
	//テーブルコラムuser_idをキャメルケース
	private Long userId;

	//空のコンストラクタ
	public Blog() {
	}

	//コンストラクタ
	public Blog(String blogTitle, String categoryName, String blogImage, String article, Long userId) {
		this.blogTitle = blogTitle;
		this.categoryName = categoryName;
		this.blogImage = blogImage;
		this.article = article;
		this.userId = userId;
	}

	//ゲッターとセッター
	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getBlogImage() {
		return blogImage;
	}

	public void setBlogImage(String blogImage) {
		this.blogImage = blogImage;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	

}
