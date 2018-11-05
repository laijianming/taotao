package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;

/**
 * 购物车controller
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	/**
	 * 接收商品的id和商品的数量，商品数量如果为空默认为1.调用Service向购物车添加商品。 展示添加成功页面。
	 */
	@RequestMapping("/add/{itemId}")
	public String addCartItem(@PathVariable Long itemId,
			@RequestParam(defaultValue = "1") Integer num,
			HttpServletRequest request, HttpServletResponse response) {
		TaotaoResult result = cartService.addCartItem(itemId, num, request,
				response);
		return "redirect:/cart/success.html";
	}

	// 跳转页面
	@RequestMapping("/success")
	public String showSuccess() {
		return "cartSuccess";
	}

	/**
	 * 展示购物车
	 */
	@RequestMapping("/cart")
	public String showCart(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<CartItem> list = cartService.getCartItemList(request, response);
		model.addAttribute("cartList", list);
		return "cart";
	}

	/**
	 * 删除购物车商品
	 */
	@RequestMapping("/delete/{itemId}")
	public String deleteCartItem(@PathVariable Long itemId,
			HttpServletRequest request, HttpServletResponse response) {
		cartService.deleteCartItem(itemId, request, response);
		return "redirect:/cart/cart.html";
	}

	/**
	 * 输入数量修改商品数量
	 */
	@RequestMapping(value = "/updateNum/{itemId}/{num}", method = RequestMethod.POST)
	@ResponseBody
	public void updateItemNum(@PathVariable Long itemId, @PathVariable int num,
			HttpServletRequest request, HttpServletResponse response) {
		TaotaoResult result = cartService.updateItemNum(itemId, num, request,
				response);
		// return result;
	}

}