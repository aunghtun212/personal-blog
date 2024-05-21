package blog.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import blog.example.model.entity.Blog;
import blog.example.model.entity.User;
import blog.example.service.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogListController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private BlogService blogService;

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
			//ブログの情報を取得する
			List<Blog> blogList = blogService.selectAllBlogList(user.getUserId());
			model.addAttribute("userName", user.getUserName());
			model.addAttribute("blogList", blogList);
			return "blog_list.html";
		}
	}
}
