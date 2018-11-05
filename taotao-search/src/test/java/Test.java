import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.taotao.search.mapper.ItemMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-dao.xml")
public class Test {
	@Autowired
	ItemMapper mapper;

	@org.junit.Test
	public void test() {
		System.out.println(mapper);
		long id = 153586379333823L;
		System.out.println(mapper.getItemById(id));
	}
}
