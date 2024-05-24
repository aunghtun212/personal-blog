package blog.example.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.example.model.entity.Blog;
import blog.example.model.entity.User;
import blog.example.service.BlogService;
import jakarta.servlet.http.HttpSession;

//Springにコントローラークラスのお知らせアノテーション
@Controller
public class BlogEditController {
	/*
	 * @Autowiredアノテーションを使って 
	 * blogService 変数を用意してBlogService クラスのメソッド使えるようにする
	 */
	@Autowired
	private BlogService blogService;
	
	//@Autowiredアノテーションを使って
	//session変数を用意してSessionのユーザ情報を取得できるようにする
	@Autowired
	private HttpSession session;
	
	//編集画面の表示
	@GetMapping("/blog/edit/{blogId}")
	public String getBlogEditPage(@PathVariable Long blogId, Model model) {
		//sessionからログインしてる人の情報を取得
		User user = (User) session.getAttribute("loginUserInfo");
		//もし、user== null ログイン画面にリダイレクトする
		if(user==null) {
			return "redirect:/user/login";
		}else {
			//編集画面に表示させる情報を変数に格納blog
			Blog blog = blogService.blogEditCheck(blogId);
			//もし、blog == null だったら、ブログ一覧ページにダイレクト
			//そうでない場合は、編集画面に編集する内容を渡す
			//編集画面を表示
			if(blog == null ) {
				return "redirect:/blog/list";
			}else {
				model.addAttribute("userName",user.getUserName());
				model.addAttribute("blog",blog);
				return "blog_editer.html";
			}
		}
	}

	// 更新処理をする
	@PostMapping("/blog/edit/process")
	public String blogUpdate(@RequestParam String blogTitle,
			@RequestParam String categoryName,
			@RequestParam MultipartFile blogImage,
			@RequestParam String article,
			@RequestParam Long blogId) {
		//sessionからログインしてる人の情報を取得
		User user = (User) session.getAttribute("loginUserInfo");
		/*
		 * もしuserがnullだったらlogin画面にリダイレクトする そうでない場合は、画像のファイル取得 画像のアップロード
		 * もし、同じファイルの名前がなかったら保存 ブログ一覧画面にリダイレクト そうでない場合は、ブログ登録画面にとどまります
		 */
		
		//ファイルの保存
		// もし、blohUpdateの結果がtrue の場合、blog一覧にリダイレクトする
		// そうでない場合は、blog編集画面にリダイレクトする
		if(user== null) {
			return "redirect:/admin/login";
		}else {
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
					+ blogImage.getOriginalFilename();
			try {
				Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-img/"+fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(blogService.blogUpdate(blogId, blogTitle, categoryName, 
					fileName, article, user.getUserId())) {
				return "redirect:/blog/list";
			}else {
				return "redirect:/blog/edit";
			}
		}
		
	}
	
}
