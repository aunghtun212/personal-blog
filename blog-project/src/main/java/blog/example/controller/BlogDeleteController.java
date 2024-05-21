package blog.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import blog.example.model.entity.User;
import blog.example.service.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogDeleteController {
	@Autowired
	private BlogService blogService;

	@Autowired
	private HttpSession session;

	@PostMapping("/blog/delete")
	public String blogDelete(Long blogId) {
		// sessionからログインしてる人の情報を取得
		User user = (User) session.getAttribute("loginUserInfo");
		// もしuser == null ログイン画面にダイレクト
		if (user == null) {
			return "redirect:/user/login";
		} else {
			// もし、deleteBlog の結果がtrueだったら、
			if (blogService.deleteBlog(blogId)) {
				// ブログ一覧ページにリダイレクト
				return "redirect:/blog/list";
			} else {
				// そうでない場合は、
				// 編集画面にリダイレクト
				return "redirect:/blog/edit" + blogId;
			}
		}
	}
}
