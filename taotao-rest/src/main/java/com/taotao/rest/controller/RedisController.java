package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.service.RedisService;

/**
 * manage更新内容后调用此controller 缓存同步controller
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/cache/sync")
public class RedisController {

	@Autowired
	private RedisService redisService;

	/**
	 * 同步广告内容
	 * 
	 * @param contentCid
	 * @return
	 */
	@RequestMapping("/content/{contentCid}")
	@ResponseBody
	public TaotaoResult contentCacheSync(@PathVariable Long contentCid) {
		TaotaoResult result = redisService.syncContent(contentCid);
		return result;
	}

	/**
	 * 同步商品分类内容
	 * 
	 * @return
	 */
	@RequestMapping("/category")
	@ResponseBody
	public TaotaoResult categoryListCache() {
		// TODO 后台管理没有分类处理相关操作
		TaotaoResult result = redisService.syncCategory();
		return result;
	}
}