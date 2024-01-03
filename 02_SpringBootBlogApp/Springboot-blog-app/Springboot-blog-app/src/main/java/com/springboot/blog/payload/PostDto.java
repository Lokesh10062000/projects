package com.springboot.blog.payload;

import lombok.Data;

@Data
public class PostDto {
	
	private long id;
	private String titile;
	private String description;
	private String content;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitile() {
		return titile;
	}
	public void setTitile(String title) {
		this.titile = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	

}