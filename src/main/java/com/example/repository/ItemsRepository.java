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
import com.example.domain.Items;
import com.example.domain.Origin;

/**
 * Itemsテーブルの操作を行うリポジトリ.
 * 
 * @author hongo
 *
 */
@Repository
public class ItemsRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	// OriginのROW_MAPPER
	private static final RowMapper<Origin> ORIGIN_ROW_MAPPER = (rs, i) -> {

		Origin origin = new Origin();
		origin.setTrain_id(rs.getInt("id"));
		origin.setName(rs.getString("name"));
		origin.setItem_condition_id(rs.getInt("condition_id"));
		origin.setCategory_name(rs.getString("category_name"));
		origin.setBrand_name(rs.getString("brand"));
		origin.setPrice(rs.getDouble("price"));
		origin.setShipping(rs.getInt("shipping"));
		origin.setItem_description(rs.getString("description"));

		return origin;
	};

	// CategoryのROW_MAPPER
	private static final RowMapper<Category> CATEGORY_ROW_MAPPER = new BeanPropertyRowMapper<>(Category.class);

	/**
	 * originalテーブルの全件検索を行うメソッド
	 * 
	 */
	public List<Origin> findAll() {
		String sql = "SELECT id,name,condition_id,category_name,brand,price,shipping,description FROM original;";
		List<Origin> OriginList = template.query(sql, ORIGIN_ROW_MAPPER);

		return OriginList;
	}

	/**
	 * Originalテーブルから取ってきたCategory_nameを基にCategoryテーブルのＩｄ検索するメソッド.
	 * 
	 * @param categoryName カテゴリー名
	 * @return カテゴリーId
	 */
	public Category findCategoryIdBycategoryName(String categoryName) {
		String sql = "SELECT id FROM category WHERE name_all = :nameAll;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("nameAll", categoryName);
		Category categoryId = template.queryForObject(sql, param, CATEGORY_ROW_MAPPER);

		return categoryId;
	}

	/**
	 * itemsテーブルにデータをインサートするメソッド.
	 * 
	 * @param item 商品
	 */
	public void insert(Items item) {
		String sql = "INSERT INTO items(name,condition,category,brand,price,shipping,description)VALUES(:name,:condition,:category,:brand,:price,:shipping,:description)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		template.update(sql, param);

	}

}
