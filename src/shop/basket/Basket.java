package shop.basket;

public class Basket {

	private int id; 			 // PK
	private int userId; 		 // 유저 ID
	private String productCode;  // 제품 Code
	private int price; 			 // 가격
	private int count;		 	 // 상품 갯수
	private int validity;		 // 유효성 검사 1일때 유효/2일때 장바구니에서 삭제된 제품
	
	public Basket(){}

	public Basket(int id, int userId, String productCode, int count, int validity, int price) {
		super();
		this.id = id;
		this.userId = userId;
		this.productCode = productCode;
		this.count = count;
		this.validity = validity;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Basket [id=" + id + ", userId=" + userId + ", productCode=" + productCode + ", price=" + price
				+ ", count=" + count + ", validity=" + validity + "]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public int getcount() {
		return count;
	}
	public void setcount(int count) {
		this.count = count;
	}
	public int getValidity() {
		return validity;
	}
	public void setValidity(int validity) {
		this.validity = validity;
	}
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
