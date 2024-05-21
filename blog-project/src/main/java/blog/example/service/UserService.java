package blog.example.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.example.model.dao.UserDao;
import blog.example.model.entity.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	//保存処理（登録処理）
	//もし、findByAdminEmail == null だったら、登録処理をします
	//save メソッドを使用して登録処理をします
	//保存できたらtrue
	//そうでない場合は、保存処理結果false
	
	public boolean createUser(String userName, String userEmail, String password) {
		if (userDao.findByUserEmail(userEmail)==null) {
			userDao.save(new User(userName, userEmail, password));
			return true;
			}else {
				return false;
			}
	}
	
	//ログイン処理
	//もし、emailとpassword がfindByUserEmailAndPasswordを使用して存在しない場合==nullの場合
	//その場合は、存在しないnullであることをコントローラークラスに知らせる
	//そうでない場合ログインしている人に情報をコントローラークラスに返す
	
	public User loginCheck(String userEmail, String password) {
		User user = userDao.findByUserEmailAndPassword(userEmail, password);
		if (user == null) {
			return null;
		}else {
			return user;
		}
	}
}
