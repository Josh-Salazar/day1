package edu.acc.j2ee.hubbub;

import java.util.Date;

public class Post implements java.io.Serializable {
    private String content;
    private User author;
    private Date postDate;

    public Post() {
    }

    public Post(String content, User author) {
        this.content = content;
        this.author = author;
        this.postDate = new Date();
    }

    public Post(String content, User author, Date postDate) {
        this.content = content;
        this.author = author;
        this.postDate = postDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
    
    @Override
    public String toString() {
        return String.format("Post[author:%s, postDate:%s, " +
            "content:%s]", author, postDate, content);
    }
}
