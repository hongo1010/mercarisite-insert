package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.CategoryService;

/**
 * categoryテーブルを操作する画面のコントローラークラス.
 * 
 * @author hongo
 *
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService service;

	/**
	 * categoryテーブルにテータを挿入するメソッド.
	 */
	@RequestMapping("/")
	public String insert() {

		service.findByCategory();

		return "index";
	}

}
