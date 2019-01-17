package shop.basket;

public class Basket {

	private int id; 		// PK
	private int userId; 	// 유저 ID
	private int productCode;  // 제품 Code
	private int numbers;	// 상품 갯수
	private int validity;	// 유효성 검사 1일때 유효/2일때 장바구니에서 삭제된 제품
	private int price; 		// 가격
	
	public Basket(){}
	
	public Basket(int id, int userId, int productCode, int numbers, int validity, int price) {
		super();
		this.id = id;
		this.userId = userId;
		this.productCode = productCode;
		this.numbers = numbers;
		this.validity = validity;
		this.price = price;
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
	public int getProductCode() {
		return productCode;
	}
	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}
	public int getNumbers() {
		return numbers;
	}
	public void setNumbers(int numbers) {
		this.numbers = numbers;
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
