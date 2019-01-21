package product;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ProductCode {

	public String productCode(String productDate, String productNum) {

		// 제품등록날짜(달 + 일 4자리) + 제품번호 2자리 + 랜덤 4자리
		String productCode = "";
		String productDate2 = productDate.replaceAll("-", "").substring(4, 8);
		
		Random rand = new Random();
		String random = "";
		
		for (int i = 0; i < 4; i++) {
			
			String ran = Integer.toString(rand.nextInt(10));
			random += ran;
		}
		
		productCode = productDate2 + productNum + random;

		return productCode;
	}
}
