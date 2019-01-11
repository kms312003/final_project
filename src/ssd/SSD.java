package ssd;

import java.util.Date;

public class SSD {

	private Long id;				// PK
	private String productName;     // 제품
	private String productCompany;  // 제조회사
	private String diskType;		// 디스크 타입
	private int diskCapacity;		// 디스크 용량
	private String interFace;		// 인터페이스
	private String memoryType;		// 메모리 타입
	private int readSpeed;			// 읽기 속도
	private int writeSpeed;			// 쓰기 속도
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
	public String getDiskType() {
		return diskType;
	}
	public void setDiskType(String diskType) {
		this.diskType = diskType;
	}
	public int getDiskCapacity() {
		return diskCapacity;
	}
	public void setDiskCapacity(int diskCapacity) {
		this.diskCapacity = diskCapacity;
	}
	public String getInterFace() {
		return interFace;
	}
	public void setInterFace(String interFace) {
		this.interFace = interFace;
	}
	public String getMemoryType() {
		return memoryType;
	}
	public void setMemoryType(String memoryType) {
		this.memoryType = memoryType;
	}
	public int getReadSpeed() {
		return readSpeed;
	}
	public void setReadSpeed(int readSpeed) {
		this.readSpeed = readSpeed;
	}
	public int getWriteSpeed() {
		return writeSpeed;
	}
	public void setWriteSpeed(int writeSpeed) {
		this.writeSpeed = writeSpeed;
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
