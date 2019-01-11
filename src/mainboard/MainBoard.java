package mainboard;

import java.util.Date;

public class MainBoard {

	private Long id;				// PK
	private String productName;     // 제품
	private String productCompany;   // 제조회사
	private String cpuSocket;		// cpu 소켓
	private String chipSet;			// 세부 칩셋
	private String formFactor;		// 폼팩터
	private String memoryType;		// 메모리 종류
	private String productSort;		// 제품 분류
	private int memorySlot;			// 메모리 슬롯 수
	private Date productDate;  		// 제품 등록일
	private Date regDate;			// 제품 입력일
	private int price; 				// 가격
	private int count;				// 수량
	private String filename;		// 파일 이름
	private int filesize;			// 파일 사이즈
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getCpuSocket() {
		return cpuSocket;
	}
	public void setCpuSocket(String cpuSocket) {
		this.cpuSocket = cpuSocket;
	}
	public String getChipSet() {
		return chipSet;
	}
	public void setChipSet(String chipSet) {
		this.chipSet = chipSet;
	}
	public String getFormFactor() {
		return formFactor;
	}
	public void setFormFactor(String formFactor) {
		this.formFactor = formFactor;
	}
	public String getMemoryType() {
		return memoryType;
	}
	public void setMemoryType(String memoryType) {
		this.memoryType = memoryType;
	}
	public String getProductSort() {
		return productSort;
	}
	public void setProductSort(String productSort) {
		this.productSort = productSort;
	}
	public int getMemorySlot() {
		return memorySlot;
	}
	public void setMemorySlot(int memorySlot) {
		this.memorySlot = memorySlot;
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
