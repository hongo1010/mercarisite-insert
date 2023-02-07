package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.ItemsService;

/**
 * Itemsテーブルを操作するコントローラークラス.
 * 
 * @author hongo
 *
 */
@Controller
@RequestMapping("/items")
public class ItemsController {

	@Autowired
	private ItemsService service;

	/**
	 * Itemsテーブルに情報をインサートする.
	 */
	@GetMapping("/insert")
	public String ItemsInsert() {

		service.ItemsInsert();

		return "index";
	}

}
