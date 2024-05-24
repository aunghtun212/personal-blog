package blog.example.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.example.model.entity.User;
import blog.example.service.ProfileService;
import jakarta.servlet.http.HttpSession;

//Springにコントローラークラスのお知らせアノテーション
@Controller
public class ProfileRegisterController {
	/*
	 * @Autowiredアノテーションを使って 
	 * profileService 変数を用意してProfileService クラスのメソッド使えるようにする
	 */
	@Autowired
	private ProfileService profileService;
	
	//@Autowiredアノテーションを使って
	//session変数を用意してSessionのユーザ情報を取得できるようにする
	@Autowired
	private HttpSession session;
	
	//profile 画面の表示
	@GetMapping("/profile/register")
	public String getProfileRegisterPage(Model model) {
		//session からログインしている人の情報をuser変数に格納
		User user = (User) session.getAttribute("loginUserInfo");
		//userがnullならlogin画面にリダイレクトする
		//そうでない場合は、login画面に名前を渡す
		//profile登録のhtmlを表示
		if(user == null) {
			return "redirect:/user/login";
		}else {
			model.addAttribute("userName",user.getUserName());
			return "profile.html";
		}
	}

	//profile登録
	@PostMapping("/profile/register/process")
	public String profileRegisterprocess(@RequestParam String name,
			@RequestParam Date birthday, @RequestParam String selfIntroduction) {
		//session からログインしている人の情報をuser変数に格納
		User user = (User) session.getAttribute("loginUserInfo");
		if(profileService.cerateProfile(name, birthday, selfIntroduction, user.getUserId())) {
			return "redirect:/profile/list";
		}else {
			return "profile.html";
		}
	}
}
