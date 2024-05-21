package blog.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.example.model.dao.BlogDao;
import blog.example.model.entity.Blog;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;
	
	//blog一覧のチェック
	//もし、userId== null　戻り値としてnull
	//そうでない場合は、findAll 内容をコントローラークラスに渡す
	//戻り値List<Blog>
	public List<Blog> selectAllBlogList(Long userId){
		if(userId == null) {
			return null;
		}else {
			return blogDao.findAll();
		}
	}
	
	//blogの登録処理チェック
	//もし、findByblogNameが==nullだったら、
	//true保存処理
	//そうでない場合は
	//false
	public boolean createBlog(String blogTitle, 
			String categoryName, 
			String blogImage, 
			String article, 
			Long userId) {
		if(blogDao.findByBlogTitle(blogTitle)==null) {
			blogDao.save(new Blog(blogTitle, categoryName, blogImage, article, userId));
			return true;
		}else {
			return false;
		}
	}
	
	//編集画面を表示する画面のチェック
	//もし、blogId == null -> null
	//そうでない場合は、
	//findByBlogId情報をコントローラークラスに渡す
	public Blog blogEditCheck(Long blogId) {
		if(blogId == null) {
			return null;
		}else {
			return blogDao.findByBlogId(blogId);
		}
	}

	//更新処理のチェック
	//もし、blogId==nullだったら、更新処理はしない
	//false
	//そうでない場合は、
	//更新処理をする
	//コントローラークラスからもらった、blogIdをつかって、編集する前の、データを取得
	//変更するべきところだけ、セッターを使用してデータの変更をする
	//true を返す
	public boolean blogUpdate(Long blogId,
			String blogTitle,
			String categoryName,
			String blogImage,
			String article,
			Long userId) {
		if(blogId == null) {
			return false;
		}else {
			Blog blog = blogDao.findByBlogId(blogId);
			blog.setBlogTitle(blogTitle);
			blog.setCategoryName(categoryName);
			blog.setBlogImage(blogImage);
			blog.setArticle(article);
			blog.setUserId(userId);
			blogDao.save(blog);
			return true;
		}
	}
	
	//削除処理のチェック
	//もし、コントローラーからもらったblogIdが nullであれば
	//削除できないことfalse
	//そうでない場合は、
	//deleteByBlogId を使用して商品の削除
	//true
	public boolean deleteBlog(Long blogId) {
		if(blogId == null) {
			return false;
		}else {
			blogDao.deleteByBlogId(blogId);
			return true;
		}
		
	}
}
