package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Origin;
import com.example.repository.MercariRepository;

/**
 * 大量データ課題のデータを挿入・分類するサービスクラス.
 * @author hongo
 *
 */
@Service
public class MercariInsertService {

	@Autowired
	private MercariRepository repository;

	/**
	 * データをインサートする処理を行うメソッド
	 * @param origin originalデータ
	 */
	public void Insert(Origin origin) {
		repository.insert(origin);
	}
}
