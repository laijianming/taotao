package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.service.ItemService;

/**
 * 商品信息: 接收商品id调用Service查询商品信息，返回商品对象，使用TaotaoResult包装。 Url：/item/info/{itemId}
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;

	/**
	 * 取商品的基本信息
	 * 
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/info/{itemId}")
	@ResponseBody
	public TaotaoResult getItemBaseInfo(@PathVariable Long itemId) {
		TaotaoResult result = itemService.getItemBaseInfo(itemId);
		return result;
	}

	/**
	 * 取商品描述信息
	 * 
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public TaotaoResult getItemDesc(@PathVariable Long itemId) {
		TaotaoResult result = itemService.getItemDesc(itemId);
		return result;
	}

	/**
	 * 取商品规格参数
	 * 
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/param/{itemId}")
	@ResponseBody
	public TaotaoResult getItemParam(@PathVariable Long itemId) {
		TaotaoResult result = itemService.getItemParam(itemId);
		return result;
	}

}