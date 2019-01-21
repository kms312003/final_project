package cpu;

import java.util.Date;

public class Cpu {
	
	private int id;					// PK
	private String productCode;		// 제품코드
	private String productName;     // 제품명
	private String productCompany;  // 제조회사
	private String brand;	        // 브랜드 분류
	private String socket;          // 소켓 구분
	private String core;            // 코어
	private int thread;             // 쓰레드 형태
	private int clockSpeed;         // 동작속도
	private int tdp;				// 설계전력
	private Date productDate;  		// 제품 등록일
	private Date regDate;			// 데이터 등록일
	private int price; 				// 가격
	private int count;				// 수량
	private String filename;		// 파일 첨부
	private int filesize;			// 파일 사이즈

	@Override
	public String toString() {
		return "Cpu [id=" + id + ", productCode=" + productCode + ", productName=" + productName + ", productCompany="
				+ productCompany + ", brand=" + brand + ", socket=" + socket + ", core=" + core + ", thread=" + thread
				+ ", clockSpeed=" + clockSpeed + ", tdp=" + tdp + ", productDate=" + productDate + ", regDate="
				+ regDate + ", price=" + price + ", count=" + count + ", filename=" + filename + ", filesize="
				+ filesize + "]";
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
	public String getProductCompany() {
		return productCompany;
	}
	public void setProductCompany(String productCompany) {
		this.productCompany = productCompany;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSocket() {
		return socket;
	}
	public void setSocket(String socket) {
		this.socket = socket;
	}
	public String getCore() {
		return core;
	}
	public void setCore(String core) {
		this.core = core;
	}
	public int getThread() {
		return thread;
	}
	public void setThread(int thread) {
		this.thread = thread;
	}
	public int getClockSpeed() {
		return clockSpeed;
	}
	public void setClockSpeed(int clockSpeed) {
		this.clockSpeed = clockSpeed;
	}
	public int getTdp() {
		return tdp;
	}
	public void setTdp(int tdp) {
		this.tdp = tdp;
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
