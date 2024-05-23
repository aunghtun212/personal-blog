package blog.example.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.example.model.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

	//userservice から渡されるユーザー情報mail addressを条件にDB検索
	User findByUserEmail(String userEmail);
	
	User findByUserEmailAndPassword(String userEmail, String password);
	
	//userservice から渡されるユーザー情報を基にDBへ保存する
	User save(User user);
	
	//ユーザー情報一覧を取得
	List<User> findAll();
	
	User findByUserId(Long userId);
}
