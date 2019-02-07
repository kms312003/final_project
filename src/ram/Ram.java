package ram;

import java.util.Date;

public class Ram {

	private int id;					// PK
	private String productCode;		// 제품코드
	private String productName;     // 제품
	private ProductCompany productCompany;   // 제조회사
	private String productSort;		// 제품 분류
	private int memoryCapacity;		// 메모리 용량
	private int clock;				// 동작쿨럭
	private int voltage;			// 동작전압
	private Date productDate;  		// 제품 등록일
	private Date regDate;			// 제품 입력일
	private int price; 				// 가격
	private int count;				// 수량
	private String filename;		// 파일 이름
	private int filesize;			// 파일 사이즈
	
	public enum ProductCompany {
		SAMSUNG, GSKILL, GEIL;
	}
	
	@Override
	public String toString() {
		return "Ram [id=" + id + ", productCode=" + productCode + ", productName=" + productName + ", productCompany="
				+ productCompany + ", productSort=" + productSort + ", memoryCapacity=" + memoryCapacity + ", clock="
				+ clock + ", voltage=" + voltage + ", productDate=" + productDate + ", regDate=" + regDate + ", price="
				+ price + ", count=" + count + ", filename=" + filename + ", filesize=" + filesize + "]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public ProductCompany getProductCompany() {
		return productCompany;
	}
	public void setProductCompany(ProductCompany productCompany) {
		this.productCompany = productCompany;
	}
	public String getProductSort() {
		return productSort;
	}
	public void setProductSort(String productSort) {
		this.productSort = productSort;
	}
	public int getMemoryCapacity() {
		return memoryCapacity;
	}
	public void setMemoryCapacity(int memoryCapacity) {
		this.memoryCapacity = memoryCapacity;
	}
	public int getClock() {
		return clock;
	}
	public void setClock(int clock) {
		this.clock = clock;
	}
	public int getVoltage() {
		return voltage;
	}
	public void setVoltage(int voltage) {
		this.voltage = voltage;
	}
	public Date getProductDate() {
		return productDate;
	}
	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
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
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getFilesize() {
		return filesize;
	}
	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}
}
