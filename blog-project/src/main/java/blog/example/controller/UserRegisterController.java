package blog.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.example.service.UserService;

@Controller
public class UserRegisterController {
	@Autowired
	private UserService userService;
	//登録画面の表示
	@GetMapping("/user/register")
	public String getUserRegisterPage() {
		return "register.html";
	}
	
	//登録処理
	@PostMapping("/user/register/process")
	public String  userRegisterProcess(@RequestParam String userName,
			@RequestParam String userEmail, @RequestParam String password) {
		//もし、createUserがtrue　　login.htmlに移動
		//そうでない場合register.htmlにとどまります
		
		if(userService.createUser(userName, userEmail, password)) {
			return "login.html";
		}else {
			return "register.html";
		}
	}
	
	

}
