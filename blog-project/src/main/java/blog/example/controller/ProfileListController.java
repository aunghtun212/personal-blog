package blog.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import blog.example.model.entity.Profile;
import blog.example.model.entity.User;
import blog.example.service.ProfileService;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProfileListController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ProfileService profileService;
	//profile 画面の表示
	@GetMapping("/profile/list")
	public String  getProfileListPage(Model model) {
		//sessionからログインしてる人の情報を取得
		User user = (User) session.getAttribute("loginUserInfo");
		//もし、user== null ログイン画面にリダイレクトする
		//そうでない場合は、profileのhtmlを表示してloginしている人の情報を画面に渡す
		if(user == null) {
			return "redirect:/user/login";
		}else {
			//profile 情報を取得
			Profile profile = profileService.selectProfile(user.getUserId());
			model.addAttribute("name", profile.getName());
			model.addAttribute("birthday", profile.getBirthday());
			model.addAttribute("selfIntroduction",profile.getSelfIntroduction());
			return "profile_list.html";
		}
	}
}
