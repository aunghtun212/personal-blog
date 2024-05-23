package blog.example.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.example.model.dao.ProfileDao;
import blog.example.model.dao.UserDao;
import blog.example.model.entity.Profile;

@Service
public class ProfileService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProfileDao profileDao;
	
	//profile一覧チェック
	//もし、userId==-null 戻り値はnull
	//そうでない場合は、findAll内容をcontroller classに渡す
	//戻り値List<Profile>
	public Profile selectProfile(Long userId){
		if(userId == null) {
			return null;
		}else {
			return profileDao.findByUserId(userId);
		}
	}
	
	//profile 登録処理チェック
	//もし、findByUserIdが null だったら
	//false 
	//そうでない場合は、true 保存処理
	public boolean cerateProfile(String name, Date birthday, String selfIntroduction, Long userId) {
		if(userDao.findByUserId(userId)==null || profileDao.findByUserId(userId)!=null ) {
			return false;
		}else {
			profileDao.save(new Profile(name, birthday, selfIntroduction,userId));
			return true;
		}
	}

}
