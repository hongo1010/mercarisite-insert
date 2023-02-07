package com.example.controller;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Origin;
import com.example.service.MercariInsertService;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
/**
 * @author hongo
 * TSVファイルを読み込んでoriginalテーブルに保存する操作を行う画面.
 *
 */
@Controller
@RequestMapping("/dataInsert")
public class MercariTsvInsertController {
	@Autowired
	public MercariInsertService service;


	/**
	 * 	TSVファイルを読み込んでインサートする処理を行うメソッド
	 * @return indexHtml
	 * @throws Exception
	 */
	@GetMapping("/insert")
	public String insert() throws Exception {
		CsvMapper mapper = new CsvMapper();
		// ヘッダあり、タブ区切り
		CsvSchema schema = mapper.schemaFor(Origin.class).withHeader().withColumnSeparator('\t');

		Path path = Paths.get("document/train.tsv");
		try (BufferedReader br = Files.newBufferedReader(path)) {

			MappingIterator<Origin> it = mapper.readerFor(Origin.class).with(schema).readValues(br);

			// TSVファイルを全行まとめて読み込む
			List<Origin> originList = it.readAll();
            // 読みこんだデータをoriginテーブルにインサートする
			originList.forEach(origin -> service.Insert(origin));
            }
		
		return "index";
	}
}
