package com.taotao.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.RedisService;

/**
 * 删除redis中的缓存
 * 
 * @author Administrator
 * 
 */
@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private JedisClient jedisClient; // spring已经配置了这个bean

	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;

	// 删除redis中广告内容
	@Override
	public TaotaoResult syncContent(long contentCid) {
		try {
			jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, contentCid + "");// 第一个参数是常量标记，第二个参数是key
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok();
	}

	// 删除redis中商品分类
	public TaotaoResult syncCategory() {
		try {
			jedisClient.del("INDEX_ITEMCAT_REDIS_KEY");// 第一个参数是常量标记，第二个参数是key
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok();
	}

}