package shop.basket;

public class Basket {

	private int id; // PK
	private int userId;  // 유저 ID
	private int productID; 
	private int numbers;
	private int validity;
	
	public Basket(){}
	
	public Basket(int id, int userId, int productID, int numbers, int validity) {
		super();
		this.id = id;
		this.userId = userId;
		this.productID = productID;
		this.numbers = numbers;
		this.validity = validity;
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
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
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
	
	
}
