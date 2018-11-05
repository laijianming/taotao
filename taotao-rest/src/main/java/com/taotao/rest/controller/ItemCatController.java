package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.rest.service.ItemCatService;

@Controller
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;

	/**
	 * 
	 * 门户系统调用rest服务查询内容分类，以String返回数据给门户系统
	 * 
	 * @param callback
	 * @return
	 */

	// 方法一
	@RequestMapping(value = "/itemcat/list", produces = MediaType.APPLICATION_JSON_VALUE
			+ ";charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback) {
		// 直接返回字符串
		String json = itemCatService.getItemCatList();
		String result = callback + "(" + json + ");";
		return result;
	}

	// 方法二
	// @RequestMapping("/itemcat/list")
	// @ResponseBody
	// public Object getItemCatList(String callback) {
	// CatResult catResult = itemCatService.getItemCatList();
	// MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(
	// catResult);
	// mappingJacksonValue.setJsonpFunction(callback);
	// return mappingJacksonValue;
	// }
}
