package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Category;
import com.example.domain.Items;
import com.example.domain.Origin;
import com.example.repository.ItemsRepository;

/**
 * Itemsテーブルにデータをインサートするためのサービスクラス.
 * 
 * @author hongo
 *
 */
/**
 * Itemテーブルにデータをインサートするリポジトリ.
 * 
 * @author hongo
 *
 */
@Service
public class ItemsService {
	@Autowired
	private ItemsRepository repository;

	/**
	 * Itemsテーブルにデータをインサートするメソッド
	 * 
	 */
	public void ItemsInsert() {

		// originalテーブルから情報を全件検索してくる
		List<Origin> originalList = repository.findAll();

		// OriginalListをfor文で回して、セットしていく。
		for (Origin origin : originalList) {
			// 毎回 カテゴリーIdをリセット
			Integer categoryId = null;

			// Category_nameを代入
			String categoryName = origin.getCategory_name();

			// categoryNameが空やNullじゃない場合if文の中の処理を行う
			if (!categoryName.isEmpty()) {
				// Category_name から CategoryIdを検索し代入

				Category category = repository.findCategoryIdBycategoryName(categoryName);
				categoryId = category.getId();
			}

			// 取ってきた情報をアイテムのオブジェクトにセットして、セットしたものをItemsテーブルにインサートする
			Items Item = new Items();
			Item.setName(origin.getName());
			Item.setCondition(origin.getItem_condition_id());
			Item.setCategory(categoryId);
			Item.setBrand(origin.getBrand_name());
			Item.setPrice(origin.getPrice());
			Item.setShipping(origin.getShipping());
			Item.setDescription(origin.getItem_description());

			repository.insert(Item);

		}

	}

}
