package blog.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.example.model.entity.User;
import blog.example.service.UserService;
import jakarta.servlet.http.HttpSession;

//Springにコントローラークラスのお知らせアノテーション
@Controller
public class UserLoginController {
	/*
	 * @Autowiredアノテーションを使って 
	 * userService 変数を用意してuserService クラスのメソッド使えるようにする
	 */
	@Autowired
	private UserService userService;
	
	//@Autowiredアノテーションを使って
	//session変数を用意してSessionのユーザ情報を取得できるようにする
	@Autowired
	private HttpSession session;
	
	//ログイン画面の表示
	@GetMapping("/user/login")
	public String getUserLoginPage() {
		return "login.html";
	}
	
	//ログイン画面の処理
	@PostMapping("/user/login/process")
	public String  userLoginProcess(@RequestParam String userName, 
			@RequestParam String userEmail,
			@RequestParam String password) {
		
		//loginCheckメソッドを呼び出してその結果をuser という変数に格納
		User user = userService.loginCheck(userEmail, password);
		//もし、admin == null ログイン画面にとどまります
		//そうでない場合は、sessionにログイン情報を保存
		//ブログ一覧画面にダイレクトする
		
		if(user==null) {
			return "login.html";
		}else {
			session.setAttribute("loginUserInfo", user);
			return "redirect:/blog/list";
		}
	}
	

	
}
