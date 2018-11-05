package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Autowired
	private JedisClient jedisClient;

	@Override
	public String getItemCatList() {

		// 从缓存中取内容
		try {
			String result = jedisClient.get("INDEX_ITEMCAT_REDIS_KEY");
			if (!StringUtils.isBlank(result)) {
				// 直接返回字符串
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		CatResult catResult = new CatResult();
		// 查询分类列表
		catResult.setData(getCatList(0));

		// 把list转换成字符串,redis只能存字符串
		try {
			String cacheString = JsonUtils.objectToJson(catResult);
			jedisClient.set("INDEX_ITEMCAT_REDIS_KEY", cacheString);
			// 设置过期时间为5分钟
			jedisClient.expire("INDEX_ITEMCAT_REDIS_KEY", 3000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return JsonUtils.objectToJson(catResult);
	}

	/**
	 * 查询分类列表
	 * <p>
	 * Title: getCatList
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param parentId
	 * @return
	 */
	private List<?> getCatList(long parentId) {

		// 创建查询条件
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		// 返回值list
		List resultList = new ArrayList<>();
		// 向list中添加节点

		int count = 0;
		for (TbItemCat tbItemCat : list) {
			// 判断是否为父节点
			if (tbItemCat.getIsParent()) {
				CatNode catNode = new CatNode();
				if (parentId == 0) {
					catNode.setName("<a href='/products/" + tbItemCat.getId()
							+ ".html'>" + tbItemCat.getName() + "</a>");
				} else {
					catNode.setName(tbItemCat.getName());
				}
				catNode.setUrl("/products/" + tbItemCat.getId() + ".html");
				catNode.setItem(getCatList(tbItemCat.getId()));

				resultList.add(catNode);
				count++;
				// 第一层只取14条记录
				if (parentId == 0 && count >= 14) {
					break;
				}

				// 如果是叶子节点
			} else {
				resultList.add("/products/" + tbItemCat.getId() + ".html|"
						+ tbItemCat.getName());
			}
		}

		return resultList;
	}
}
