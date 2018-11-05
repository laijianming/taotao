package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;

/**
 * 展示商品详情页面。
 * 
 * @author Administrator
 * 
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	/**
	 * 商品基本信息
	 * 
	 * @param itemId
	 * @param model
	 * @return
	 */
	@RequestMapping("/item/{itemId}")
	public String showItem(@PathVariable Long itemId, Model model) {
		ItemInfo item = itemService.getItemById(itemId);
		model.addAttribute("item", item);

		return "item";
	}

	/**
	 * 商品描述
	 * 
	 * @param itemId
	 * @return
	 */
	// 解决中文乱码
	@RequestMapping(value = "/item/desc/{itemId}", produces = MediaType.TEXT_HTML_VALUE
			+ ";charset=utf-8")
	@ResponseBody
	public String getItemDesc(@PathVariable Long itemId) {
		String string = itemService.getItemDescById(itemId);
		return string;
	}

	/**
	 * 取商品规格参数
	 * 
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value = "/item/param/{itemId}", produces = MediaType.TEXT_HTML_VALUE
			+ ";charset=utf-8")
	@ResponseBody
	public String getItemParam(@PathVariable Long itemId) {
		String string = itemService.getItemParam(itemId);
		return string;
	}
}