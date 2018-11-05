import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.taotao.rest.controller.ItemCatController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:springmvc.xml")
public class TestController {

	@Autowired
	private ItemCatController itemController;

	@org.junit.Test
	public void test() {

		System.out.println("hello");
		System.out.println(itemController);
		System.out.println();
		System.out.println("--------");
	}

}
