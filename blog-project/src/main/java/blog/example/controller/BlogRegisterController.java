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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.example.model.entity.User;
import blog.example.service.BlogService;
import jakarta.servlet.http.HttpSession;

//Springにコントローラークラスのお知らせアノテーション
@Controller
public class BlogRegisterController {
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
	
	//ブログ画面の表示
	@GetMapping("/blog/register")
	public String getBlogRegisterPage(Model model) {
		//session からログインしている人の情報をuser変数に格納
		User user = (User) session.getAttribute("loginUserInfo");
		//userがnullならlogin画面にリダイレクトする
		//そうでない場合は、login画面に名前を渡す
		//ブログ登録のhtmlを表示
		if(user== null) {
			return "redirect:/user/login";
		}else {
			model.addAttribute("userName", user.getUserName());
			return "blog_register.html";
		}
	}

	//ブログの登録
	@PostMapping("/blog/register/process")
	public String blogRegisterProcess(@RequestParam String blogTitle,
			@RequestParam String categoryName,
			@RequestParam MultipartFile blogImage,
			@RequestParam String article) {
		
		//session からログインしている人の情報をuser変数に格納
		User user = (User) session.getAttribute("loginUserInfo");
		
		//もしuserがnullだったらlogin画面にリダイレクトする
		//そうでない場合は、画像のファイル取得
		//画像のアップロード
		//もし、同じファイルの名前がなかったら保存
		//ブログ一覧画面にリダイレクト
		//そうでない場合は、ブログ登録画面にとどまります
		
		if(user == null) {
			return "redirect:/user/login";
		}else {
			//ファイルの取得
			//SimpleDateFormatクラスを使ってformatを指定
			//blogImage object から元のファイル名を取得し連携してfileNameに代入
			
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())+blogImage.getOriginalFilename();
			
			//ファイルの保存作業
			try {
				Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-img/"+fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(blogService.createBlog(blogTitle, categoryName, fileName, article, user.getUserId())) {
				return "redirect:/blog/list";
			}else {
				return "blog_register.html";
			}
		}
	}
}
