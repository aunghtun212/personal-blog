package blog.example.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.example.model.entity.Blog;
import jakarta.transaction.Transactional;
//Springにdaoクラスのお知らせアノテーション
@Repository

//DBに対して一連の操作をひとまとめにする
@Transactional
public interface BlogDao extends JpaRepository<Blog, Long> {

	//保存処理と更新処理insert and update
	Blog save(Blog blog);
	
	//ブログ一覧を表示させるときに使用
	//SELECT * FROM blog
	List<Blog>findAll();
	
	//SELECT * FROM blog WHERE blog title = ?
	//用途：blogの登録チェックに使用　同じblog名が登録されないようにする
	Blog findByBlogTitle(String blogTitle);
	
	//SELECT * FROM blog WHERE blog_id = ?
	//用途：編集画面を表示する際に使用
	Blog findByBlogId(Long blogId);
	
	//DELETE FROM Blog WHERE blog_id = ?
	//用途：　削除処理に使用します
	void deleteByBlogId(Long blogId);
	
}
