package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Post extends Model {

	public String title;
	public String slug;

	public boolean isPublished;

	public boolean hasComments;

	public Date postedAt;

	@Lob
	public String content;

	@Lob
	public String tease;

	@ManyToOne
	public User author;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	public List<Comment> comments;

	public Post(final User author, final String title, final String content,
			final String tease) {
		this.author = author;
		this.title = title;
		this.content = content;
		this.tease = tease;
		comments = new ArrayList<Comment>();
		// TODO: improve slugification
		slug = title.replace(' ', '-');
		postedAt = new Date();
		isPublished = false;
		hasComments = true;
	}

	public Post next() {
		return Post.find("postedAt > ? order by postedAt asc", postedAt)
				.first();
	}

	public Post previous() {
		return Post.find("postedAt < ? order by postedAt desc", postedAt)
				.first();
	}

	public Post withComment(final String author, final String content,
			final String email) {
		if (!hasComments) {
			throw new IllegalStateException("No comments for post");
		}

		Comment newComment = new Comment(this, author, content, email).save();
		comments.add(newComment);
		this.save();
		return this;
	}

}
