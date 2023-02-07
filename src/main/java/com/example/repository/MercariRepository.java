package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Origin;

/**
 * originalテーブルを操作するリポジトリ.
 * @author hongo
 *
 */
@Repository
public class MercariRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * originalテーブルにデータをインサートするメソッド
	 * @param origin originalデータ
	 */
	public void insert(Origin origin) {
		
		String sql="INSERT INTO original(id,name,condition_id,category_name,brand,price,shipping,description)VALUES(:train_id,:name,:item_condition_id,:category_name,:brand_name,:price,:shipping,:item_description);";
	
		SqlParameterSource param = new BeanPropertySqlParameterSource(origin);

		template.update(sql, param);
	}

}
