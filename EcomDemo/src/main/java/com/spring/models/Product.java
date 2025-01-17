package com.spring.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("category")
    private String category;

    @JsonProperty("releaseDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date releaseDate;

    @JsonProperty("productAvailable")
    private boolean productAvailable;

    @JsonProperty("stockQuantity")
    private int stockQuantity;
    
    @JsonProperty("imageName")
    private String imageName;
    
    @JsonProperty("imageType")
    private String imageType;
    
    @JsonProperty("imageData")
    @Lob
    private byte[] imageData;

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", brand=" + brand + ", price="
				+ price + ", category=" + category + ", releaseDate=" + releaseDate + ", productAvailable="
				+ productAvailable + ", stockQuantity=" + stockQuantity + ", imageName=" + imageName + ", imageType="
				+ imageType + ", imageData=" + Arrays.toString(imageData) + "]";
	}
	

}
