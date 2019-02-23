package order;

import java.util.Date;

public class Order {

	private int id;				 	 // PK
	private String email;		 	 // 주문자 email
	private String productCategory;  // 제품이름
	private String productName;		 // 제품이름
	private String productCode;		 // 제품코드
	private Date orderDate;		 	 // 주문시간
//	private List<Integer> basketIds; // 주문상품 리스트	
	private int price;		  	 	 // 총 주문금액
	private Status status;	  	 	 // 주문상태
//	private Payment payment;		 // 결제정보

	@Override
	public String toString() {
		return "Order [id=" + id + ", email=" + email + ", productCategory=" + productCategory + ", productName="
				+ productName + ", productCode=" + productCode + ", orderDate=" + orderDate + ", price=" + price
				+ ", status=" + status + "]";
	}
	
	public enum Status {
		SUCCESS, FAIL;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
//	public List<Integer> getBasketIds() {
//		return basketIds;
//	}
//	public void setBasketIds(List<Integer> basketIds) {
//		this.basketIds = basketIds;
//	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
}
