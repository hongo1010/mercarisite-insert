package com.example.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * originテーブルのドメイン.
 * @author hongo
 *
 */
@JsonPropertyOrder({ "train_id", "name", "item_condition_id", "category_name", "brand_name", "price", "shipping",
		"item_description" })
public class Origin {
	private Integer train_id;
	private String name;
	private Integer item_condition_id;
	private String category_name;
	private String brand_name;
	private double price;
	private Integer shipping;
	private String item_description;

	public Origin(Integer train_id, String name, Integer item_condition_id, String category_name, String brand_name,
			double price, Integer shipping, String item_description) {

		this.train_id = train_id;
		this.name = name;
		this.item_condition_id = item_condition_id;
		this.category_name = category_name;
		this.brand_name = brand_name;
		this.price = price;
		this.shipping = shipping;
		this.item_description = item_description;
	}

	public Origin() {
	}

	public Integer getTrain_id() {
		return train_id;
	}

	public void setTrain_id(Integer train_id) {
		this.train_id = train_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getItem_condition_id() {
		return item_condition_id;
	}

	public void setItem_condition_id(Integer item_condition_id) {
		this.item_condition_id = item_condition_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
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

	public String getItem_description() {
		return item_description;
	}

	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}

	@Override
	public String toString() {
		return "Origin [train_id=" + train_id + ", name=" + name + ", item_condition_id=" + item_condition_id
				+ ", category_name=" + category_name + ", brand_name=" + brand_name + ", price=" + price + ", shipping="
				+ shipping + ", item_description=" + item_description + "]";
	}
}