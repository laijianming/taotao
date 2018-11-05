import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.taotao.portal.controller.IndexController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:springmvc.xml")
// @ContextConfiguration(locations = {
// "file:E:/taotao/taotao/taotao-protal/src/main/resources/springmvc.xml" })
public class TestController {

	@Autowired
	// private MyController indexController;
	private IndexController controller;

	@org.junit.Test
	public void test() {

		System.out.println("hello");
		System.out.println(controller);
		System.out.println();
		System.out.println("--------");
	}
}