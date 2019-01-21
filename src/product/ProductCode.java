package product;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ProductCode {

	SimpleDateFormat transFormat = new SimpleDateFormat("yyMMdd");

	public String productCode(Date productDate, String productNum) {

		// 제품등록날짜(달 + 일 4자리) + 제품번호 2자리 + 랜덤 4자리
		String productCode = "";
		String productDateS = transFormat.format(productDate).substring(2, 6);

		Random rand = new Random();
		String random = "";
		
		for (int i = 0; i < 4; i++) {
			
			String ran = Integer.toString(rand.nextInt(10));
			random += ran;
		}
		
		productCode = productDateS + productNum + random;

		return productCode;
	}
}
