package graphic;

import java.sql.Timestamp;
import java.util.Date;

public class Graphic {

	private int id;					// PK
	private String productCode;		// 제품코드
	private String productName;     // 제품
	private String productCompany;  // 제조회사
	private String chipSetGroup;	// 칩셋 그룹
	private String interFace;		// 인터페이스
	private String powerPort;		// 전원포트
	private int memoryCapacity;		// 메모리 용량
	private String nvidiaChipSet;	// NVIDIA 칩셋
	private int maxPower;			// 최대 사용 전력
	private int maxMonitor;			// 최대 지원 모니터 수
	private int length;				// 길이
	private Date productDate;  		// 제품 등록일
	private Date regDate;			// 제품 입력일
	private int price; 				// 가격
	private int count;				// 수량
	private String filename;		// 파일 이름
	private int filesize;			// 파일 사이즈
	
	@Override
	public String toString() {
		return "Graphic [id=" + id + ", productCode=" + productCode + ", productName=" + productName
				+ ", productCompany=" + productCompany + ", chipSetGroup=" + chipSetGroup + ", interFace=" + interFace
				+ ", powerPort=" + powerPort + ", memoryCapacity=" + memoryCapacity + ", nvidiaChipSet=" + nvidiaChipSet
				+ ", maxPower=" + maxPower + ", maxMonitor=" + maxMonitor + ", length=" + length + ", productDate="
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
	public String getChipSetGroup() {
		return chipSetGroup;
	}
	public void setChipSetGroup(String chipSetGroup) {
		this.chipSetGroup = chipSetGroup;
	}
	public String getInterFace() {
		return interFace;
	}
	public void setInterFace(String interFace) {
		this.interFace = interFace;
	}
	public String getPowerPort() {
		return powerPort;
	}
	public void setPowerPort(String powerPort) {
		this.powerPort = powerPort;
	}
	public int getMemoryCapacity() {
		return memoryCapacity;
	}
	public void setMemoryCapacity(int memoryCapacity) {
		this.memoryCapacity = memoryCapacity;
	}
	public String getNvidiaChipSet() {
		return nvidiaChipSet;
	}
	public void setNvidiaChipSet(String nvidiaChipSet) {
		this.nvidiaChipSet = nvidiaChipSet;
	}
	public int getMaxPower() {
		return maxPower;
	}
	public void setMaxPower(int maxPower) {
		this.maxPower = maxPower;
	}
	public int getMaxMonitor() {
		return maxMonitor;
	}
	public void setMaxMonitor(int maxMonitor) {
		this.maxMonitor = maxMonitor;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
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
