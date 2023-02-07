package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Category;

/**
 * categoryテーブルを操作するリポジトリ.
 * 
 * @author hongo
 *
 */
@Repository
public class CategoryRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Category> CATEGORY_ROW_MAPPER = new BeanPropertyRowMapper<>(Category.class);

	/**
	 * originalテーブルのからカテゴリーネームのみを検索するメソッド.
	 * 
	 * @return
	 */
	public List<Category> findByCatgory() {
		String sql = "SELECT category_name FROM original GROUP BY category_name;";
		List<Category> categotyList = template.query(sql, CATEGORY_ROW_MAPPER);

		return categotyList;
	}

	/**
	 * 大カテゴリーの名前からその大カテゴリーのIDを取得するメソッド.
	 * 
	 * @param BigCategoryName 大カテゴリー名
	 * @return 大カテゴリー
	 */
	public Category findByBigCategoryId(String bigCategoryName) {
		String sql = "SELECT id FROM category WHERE name =:categoryName AND parent IS NULL;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("categoryName", bigCategoryName);
		Category BigCategoryId = template.queryForObject(sql, param, CATEGORY_ROW_MAPPER);

		return BigCategoryId;
	}

	/**
	 * 中カテゴリーの名前とParentIdからその中カテゴリーのIDを検索するメソッド.
	 * 
	 * @param middleCategoryName     中カテゴリー名
	 * @param middleCategoryParentId 中カテゴリーのparentId
	 * @return 中カテゴリー
	 */
	public Category serchByMiddleCategoryId(String middleCategoryName, int middleCategoryParentId) {
		String sql = "SELECT id FROM category WHERE name =:categoryName AND parent=:parentId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("categoryName", middleCategoryName)
				.addValue("parentId", middleCategoryParentId);
		Category MiddleCategoryId = template.queryForObject(sql, param, CATEGORY_ROW_MAPPER);

		return MiddleCategoryId;
	}

	/**
	 * categoryテーブルにデータをインサートするメソッド.
	 * 
	 * @param category カテゴリー
	 */
	public void insert(Category category) {
		String sql = "INSERT INTO category(parent,name,name_all)VALUES(:parentId,:categoryName,:nameAll);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(category);
		template.update(sql, param);
	}

}
