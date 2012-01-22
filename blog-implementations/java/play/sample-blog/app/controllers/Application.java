package controllers;

import java.util.List;

import models.Post;
import play.data.validation.Required;
import play.mvc.Controller;

public class Application extends Controller {

	public static void addCommentToPost(@Required final Long postId,
			@Required final String author, @Required final String content,
			@Required final String email) {
		Post post = Post.findById(postId);
		if (!validation.hasErrors()) {
			post.withComment(author, content, email);
			flash.success("Thanks for posting %s", author);
		}
		render("Application/post.html", post);
	}

	public static void index() {
		List<Post> posts = Post.find("order by postedAt desc").fetch(10);
		render(posts);
	}

	public static void post(final Long id) {
		Post post = Post.findById(id);
		render(post);
	}

}