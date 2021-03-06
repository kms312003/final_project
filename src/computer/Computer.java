package computer;

import java.util.Date;

public class Computer {

	private int id;					// PK
	private String code;			// 준시스템 크롤링 코드
	private String productCode;		// 제품코드
	private Category category; // 컴퓨터 카테고리
	private String productName;  	// 상품명
	private String cpu;				// cpu
	private String mainBoard;		// 메인보드
	private String ram;				// ram
	private String vga;				// 그래픽카드
	private String hdd;				// 하드디스크
	private String ssd;				// ssd
	private String tower;			// 케이스
	private String power;			// 파워
	private Date productDate;  		// 제품 등록일
	private Date regDate;			// 제품 입력일
	private int price; 				// 가격
	private int count;				// 수량
	private String filename;		// 파일 이름
	private int filesize;			// 파일 사이즈
	
	public enum Category {
		OFFICE, GAME, DESIGN, BROADCASTING;
	}
	
	@Override
	public String toString() {
		return "Computer [id=" + id + ", code=" + code + ", productCode=" + productCode + ", category=" + category
				+ ", productName=" + productName + ", cpu=" + cpu + ", mainBoard=" + mainBoard + ", ram=" + ram
				+ ", vga=" + vga + ", hdd=" + hdd + ", ssd=" + ssd + ", tower=" + tower + ", power=" + power
				+ ", productDate=" + productDate + ", regDate=" + regDate + ", price=" + price + ", count=" + count
				+ ", filename=" + filename + ", filesize=" + filesize + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getMainBoard() {
		return mainBoard;
	}
	public void setMainBoard(String mainBoard) {
		this.mainBoard = mainBoard;
	}
	public String getRam() {
		return ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	public String getVga() {
		return vga;
	}
	public void setVga(String vga) {
		this.vga = vga;
	}
	public String getHdd() {
		return hdd;
	}
	public void setHdd(String hdd) {
		this.hdd = hdd;
	}
	public String getSsd() {
		return ssd;
	}
	public void setSsd(String ssd) {
		this.ssd = ssd;
	}
	public String getTower() {
		return tower;
	}
	public void setTower(String tower) {
		this.tower = tower;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
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
