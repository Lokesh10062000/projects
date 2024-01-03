package com.springboot.blog.entity;

import java.util.HashSet;
import java.util.Set;

import javax.print.attribute.HashAttributeSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = "titile")})
public class Posts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="titile",nullable = false)
	private String titile;
	
	@Column(name="description",nullable = false)
	private String description;
	
	@Column(name="content",nullable = false)
	private String content;
	
	@OneToMany(mappedBy = "posts", cascade=CascadeType.ALL, orphanRemoval = true)
	private Set<Comments> Comments = new HashSet<Comments>();

	public Set<Comments> getComments() {
		return Comments;
	}

	public void setComments(Set<Comments> comments) {
		Comments = comments;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitile() {
		return titile;
	}

	public void setTitile(String titile) {
		this.titile = titile;
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
