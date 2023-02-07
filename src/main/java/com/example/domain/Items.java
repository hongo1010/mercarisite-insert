package com.example.domain;

/**
 * Itemsテーブルのドメイン.
 * @author hongo
 *
 */
public class Items {
	/** ID */
	private Integer id;
	/** 商品名 */
	private String name;
	/** コンディションID */
	private Integer condition;
	/** カテゴリー */
	private Integer category;
	/** ブランド */
	private String brand;
	/** 価格 */
	private double price;
	/** 運送 */
	private Integer shipping;
	/** 説明 */
	private String description;

	/** Getter&Setter */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCondition() {
		return condition;
	}

	public void setCondition(Integer condition) {
		this.condition = condition;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getShipping() {
		return shipping;
	}

	public void setShipping(Integer shipping) {
		this.shipping = shipping;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/** toString */
	@Override
	public String toString() {
		return "Items [id=" + id + ", name=" + name + ", condition=" + condition + ", category=" + category + ", brand="
				+ brand + ", price=" + price + ", shipping=" + shipping + ", description=" + description + "]";
	}
}
