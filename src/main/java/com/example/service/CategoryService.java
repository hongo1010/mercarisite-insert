package com.example.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Category;
import com.example.repository.CategoryRepository;

/**
 * categoryテーブルに情報を登録するサービスクラス.
 * 
 * @author hongo
 *
 */
@Service
public class CategoryService {
	@Autowired
	private CategoryRepository repository;

	/**
	 * カテゴリーネームをoriginalテーブルから検索し、結果からcategoryテーブルにデータをインサートする処理と行うメソッド.
	 * 
	 * @return カテゴリーリスト
	 */
	public List<Category> findByCategory() {

		// List変数宣言
		Set<String> bigCategorySetName = new HashSet<String>();
		Set<String> middleCategorySetName = new HashSet<String>();
		Set<String> smallCategorySetName = new HashSet<String>();

		// String変数宣言
		String categoryName = null;

		// originalテーブルからカテゴリーネームを検索
		List<Category> categotyList = repository.findByCatgory();
		categotyList.remove(0);// 一番最初の空行の削除

		// カテゴリーリストからcategoryNameだけをリスト化する
		for (Category category : categotyList) {
			categoryName = category.getCategoryName();

			// categoryNameの/を除き、1つずつ区切った後、配列化、
			String[] categoryNames = categoryName.split("/");

			// 各配列をfor文で回し、各indexの文字を取り出す
			for (int i = 0; i < categoryNames.length; i++) {

				String name = categoryNames[i];

				// もし配列のindexが０（大カテゴリー）の場合、下記の処理を行う
				if (i == 0) {

					// 大カテゴリーのハッシュセットに大カテゴリー名が登録されていなかったら、大カテゴリー名をハッシュセットに追加し、インサートを行う
					if (!bigCategorySetName.contains(name)) {

						bigCategorySetName.add(name);

						// インスタンス化してセット
						Category bigCategory = new Category();

						bigCategory.setParentId(null);
						bigCategory.setCategoryName(name);
						bigCategory.setNameAll(null);

						// インサート
						repository.insert(bigCategory);

					}

					// もし配列のindexが1（中カテゴリー）だった場合、下記の処理を行う
				} else if (i == 1) {
					// 中カテゴリーのハッシュセットに大カテゴリー名と中カテゴリー名が登録されていなかったら、中カテゴリーのハッシュセットに追加し、インサートを行う
					if (!middleCategorySetName.contains(categoryNames[0] + name)) {

						middleCategorySetName.add(categoryNames[0] + name);

						// ParentIdをセットするために大カテゴリーのIDを取得
						Category bigCategoryId = repository.findByBigCategoryId(categoryNames[0]);
						int searchBigCategoryId = bigCategoryId.getId();

						// インスタンス化してセット
						Category middleCategory = new Category();

						middleCategory.setParentId(searchBigCategoryId);
						middleCategory.setCategoryName(name);
						middleCategory.setNameAll(null);

						// インサート
						repository.insert(middleCategory);
					}

					// もし配列のindexが2（小カテゴリー）だった場合、下記の処理を行う
				} else if (i == 2) {
					// 小カテゴリーのハッシュセットにcategoryName（/区切りのカテゴリー名）が登録されていなかったら、小カテゴリのハッシュセットに追加し、インサートを行う
					// ※大中小のカテゴリー名を指定した場合、エラーが出たため categoryNameで指定。
					if (!smallCategorySetName.contains(categoryName)) {

						smallCategorySetName.add(categoryName);

						// 中カテゴリーのParentIdを取得するために大カテゴリーのIDを取得
						Category bigCategoryId = repository.findByBigCategoryId(categoryNames[0]);
						int searchBigCategoryNameId = bigCategoryId.getId();

						// 小カテゴリのParentIdを取得するために中カテゴリーのIDを取得
						Category smallCategoryId = repository.serchByMiddleCategoryId(categoryNames[1],
								searchBigCategoryNameId);
						int searchMiddleCategoryNameId = smallCategoryId.getId();

						// インスタンス化してセット
						Category smallCategory = new Category();

						smallCategory.setParentId(searchMiddleCategoryNameId);
						smallCategory.setCategoryName(name);
						smallCategory.setNameAll(categoryName);

						// インサート
						repository.insert(smallCategory);
					}
				}
			}
		}
		return categotyList;
	}
}
