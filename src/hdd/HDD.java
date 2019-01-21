package hdd;

import java.util.Date;

public class HDD {

	private int id;					// PK
	private String productCode;		// 제품코드
	private String productName;     // 제품
	private String productCompany;  // 제조회사
	private String interFace;		// 인터페이스
	private String diskSize;		// 디스크 크기
	private int diskCapacity;		// 디스크 용량
	private String bufferCapacity;  // 버퍼 용량
	private String rotation;		// 회전수
	private Date productDate;  		// 제품 등록일
	private Date regDate;			// 제품 입력일
	private int price; 				// 가격
	private int count;				// 수량
	private String filename;		// 파일 이름
	private int filesize;			// 파일 사이즈
	
	@Override
	public String toString() {
		return "HDD [id=" + id + ", productCode=" + productCode + ", productName=" + productName + ", productCompany="
				+ productCompany + ", interFace=" + interFace + ", diskSize=" + diskSize + ", diskCapacity="
				+ diskCapacity + ", bufferCapacity=" + bufferCapacity + ", rotation=" + rotation + ", productDate="
				+ productDate + ", regDate=" + regDate + ", price=" + price + ", count=" + count + ", filename="
				+ filename + ", filesize=" + filesize + "]";
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
	public String getInterFace() {
		return interFace;
	}
	public void setInterFace(String interFace) {
		this.interFace = interFace;
	}
	public String getDiskSize() {
		return diskSize;
	}
	public void setDiskSize(String diskSize) {
		this.diskSize = diskSize;
	}
	public int getDiskCapacity() {
		return diskCapacity;
	}
	public void setDiskCapacity(int diskCapacity) {
		this.diskCapacity = diskCapacity;
	}
	public String getBufferCapacity() {
		return bufferCapacity;
	}
	public void setBufferCapacity(String bufferCapacity) {
		this.bufferCapacity = bufferCapacity;
	}
	public String getRotation() {
		return rotation;
	}
	public void setRotation(String rotation) {
		this.rotation = rotation;
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
