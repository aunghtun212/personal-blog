package blog.example.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.example.model.entity.Profile;
import jakarta.transaction.Transactional;

//Springにdaoクラスのお知らせアノテーション
@Repository

//DBに対して一連の操作をひとまとめにする
@Transactional
public interface ProfileDao extends JpaRepository<Profile, Long> {
	
	//保存処理
	Profile save(Profile profile);
	
	//SELECT * FROM profile
	//Profile一覧表示の時使う
	Profile findByUserId(Long userId);
	
	//SELECT * FROM profile WHERE name?
	Profile findByName(String name);
	
	//SELECT *FROM profile WHERE profile_id ?
	Profile findByProfileId(Long ProfileId);
	
	//DELETE FROM profile WHERE profile_id?
	void deleteByProfileId(Long profileId);
	
	
}
