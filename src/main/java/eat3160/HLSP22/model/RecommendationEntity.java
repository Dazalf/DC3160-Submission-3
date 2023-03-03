package eat3160.HLSP22.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="recommendations")
public class RecommendationEntity {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false, unique=true)
	private Integer id;
	
	@Column(name="name", nullable=true, unique=false)
	private String name;
	
	@Column(name="publisher", nullable=true, unique=false)
	private String publisher;
	
	@Column(name="type", nullable=true, unique=false)
	private String type;
	
	@Column(name="link", nullable=true, unique=false)
	private String link;

	public Integer getID() {
		return id;
	}

	public void setUserID(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}



}
