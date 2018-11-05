import org.junit.Test;
import org.springframework.util.DigestUtils;

public class TestMD5 {

	@Test
	public void test() {
		String passd = "hello";

		String string = DigestUtils.md5DigestAsHex(passd.getBytes());
		String string2 = DigestUtils.md5DigestAsHex(passd.getBytes());
		System.out.println(string);
		System.out.println(string2);
	}

}
