package product;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SimpleDateFormat transFormat = new SimpleDateFormat("yyMMdd");

		// 제품등록날짜(달 + 일 4자리) + 제품번호 2자리 + 랜덤 4자리
		String productCode = "";
		
		String productDateS = "1234";

		Random rand = new Random();
		String random = "";

		for (int i = 0; i < 4; i++) {

			String ran = Integer.toString(rand.nextInt(10));
			random += ran;
		}
		System.out.println("random: " + random);
		
		productCode = productDateS + "01" + random;
		System.out.println(productCode);
	}
}
