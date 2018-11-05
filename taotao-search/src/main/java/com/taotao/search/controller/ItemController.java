package com.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.service.ItemService;

/**
 * 发布一个rest形式的服务。调用Service的服务方法，把数据导入到索引库中，返回TaotaoResult。
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/manage")
public class ItemController {

	@Autowired
	private ItemService itemService;

	/**
	 * 导入商品数据到索引库
	 */
	@RequestMapping("/importall")
	@ResponseBody
	public TaotaoResult importAllItems() {
		TaotaoResult result = itemService.importAllItems();
		return result;
	}

	/**
	 * 导入新增商品
	 */
	@RequestMapping("/importItem/{itemId}")
	@ResponseBody
	public TaotaoResult importItem(@PathVariable Long itemId) {
		System.out.println("调用了solr的更新服务；itemId是  --" + itemId);
		TaotaoResult result = itemService.importItem(itemId);
		return result;
	}

}
