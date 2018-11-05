package com.taotao.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.search.mapper.ItemMapper;
import com.taotao.search.pojo.Item;
import com.taotao.search.service.ItemService;

/**
 * 操作索引库： 导入所有的商品数据。没有参数。返回结果TaotaoResult。从数据库中查询出所有的商品数据。
 * 创建一个SolrInputDocument对象，把对象写入索引库。
 * 
 * @author Administrator
 * 
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;

	@Autowired
	private SolrServer solrServer;

	@Override
	public TaotaoResult importAllItems() {
		try {

			// 查询商品列表
			List<Item> list = itemMapper.getItemList();
			// 把商品信息写入索引库
			for (Item item : list) {
				// 创建一个SolrInputDocument对象
				SolrInputDocument document = new SolrInputDocument();
				document.setField("id", item.getId());
				document.setField("item_title", item.getTitle());
				document.setField("item_sell_point", item.getSell_point());
				document.setField("item_price", item.getPrice());
				document.setField("item_image", item.getImage());
				document.setField("item_category_name", item.getCategory_name());
				document.setField("item_desc", item.getItem_des());
				// 写入索引库
				solrServer.add(document);
			}
			// 提交修改
			solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok();
	}

	// 把更新的商品添加到索引库
	public TaotaoResult importItem(Long itemId) {
		// 查询该id商品的字段信息
		Item item = itemMapper.getItemById(itemId);
		// 把商品信息写入索引库
		try {
			// 创建一个SolrInputDocument对象
			SolrInputDocument document = new SolrInputDocument();
			document.setField("id", item.getId());
			document.setField("item_title", item.getTitle());
			document.setField("item_sell_point", item.getSell_point());
			document.setField("item_price", item.getPrice());
			document.setField("item_image", item.getImage());
			document.setField("item_category_name", item.getCategory_name());
			document.setField("item_desc", item.getItem_des());
			// 写入索引库
			solrServer.add(document);
			// 提交修改
			solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok();
	}

}