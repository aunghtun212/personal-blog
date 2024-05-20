package blog.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import blog.example.model.entity.User;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogListController {
	@Autowired
	private HttpSession session;

	//ブログ一覧画面を表示
	@GetMapping("/blog/list")
	public String getBlogListPage(Model model) {
		//sessionからログインしてる人の情報を取得
		User user = (User) session.getAttribute("loginUserInfo");
		//もし、user== null ログイン画面にリダイレクトする
		//そうでない場合は、ブログ一覧のhtmlを表示してloginしている人の情報を画面に渡す
		if(user==null) {
			return "redirect:/user/login";
		}else {
			model.addAttribute("userName", user.getUserEmail());
			return "blog_list.html";
		}
	}
}
