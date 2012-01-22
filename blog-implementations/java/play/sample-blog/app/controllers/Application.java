package controllers;

import java.util.List;

import models.Post;
import play.mvc.Controller;

public class Application extends Controller {

	public static void addCommentToPost(final Long postId, final String author,
			final String content, final String email) {
		Post post = Post.findById(postId);
		post.withComment(author, content, email);
		post(postId);
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