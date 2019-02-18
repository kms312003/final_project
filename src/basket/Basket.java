package basket;

public class Basket {

	private int id; 			 // PK
	private String productCategory; // 제품 종류
	private String email; 		 // 유저 ID
	private String productCode;  // 제품 Code
	private String productName;  // 제품명
	private int price; 			 // 가격
	private int count;		 	 // 상품 갯수
	private int total;			 // 총 가격
	
	public Basket(){}

	public Basket(int id, String productCategory, String email, String productCode, String productName, int price,
			int count, int total) {
		super();
		this.id = id;
		this.productCategory = productCategory;
		this.email = email;
		this.productCode = productCode;
		this.productName = productName;
		this.price = price;
		this.count = count;
		this.total = total;
	}

	@Override
	public String toString() {
		return "Basket [id=" + id + ", productCategory=" + productCategory + ", email=" + email + ", productCode="
				+ productCode + ", productName=" + productName + ", price=" + price + ", count=" + count + ", total="
				+ total + "]";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
