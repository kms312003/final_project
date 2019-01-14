package cpu;

import java.util.Date;

public class Cpu {
	
	private Long id;				// PK
	private String productName;     // �젣�뭹紐�
	private String productCompany;  // �젣議고쉶�궗
	private String brand;	        // 釉뚮옖�뱶 遺꾨쪟
	private String socket;          // �냼耳볤뎄遺�
	private String core;            // 肄붿뼱
	private int thread;             // �벐�젅�뱶 �삎�깭
	private int clockSpeed;         // �룞�옉�냽�룄
	private int tdp;				// �꽕怨꾩쟾�젰(TDP)
	private Date productDate;  		// �젣�뭹 �벑濡앹씪
	private Date regDate;			// �젣�뭹 �엯�젰�씪
	private int price; 				// 媛�寃�
	private int count;				// �닔�웾
	private String filename;		// �뙆�씪 �씠由�
	private int filesize;			// �뙆�씪 �궗�씠利�
	
	
	
	@Override
	public String toString() {
		return "Cpu [id=" + id + ", productName=" + productName + ", productCompany=" + productCompany + ", brand="
				+ brand + ", socket=" + socket + ", core=" + core + ", thread=" + thread + ", clockSpeed=" + clockSpeed
				+ ", tdp=" + tdp + ", productDate=" + productDate + ", regDate=" + regDate + ", price=" + price
				+ ", count=" + count + ", filename=" + filename + ", filesize=" + filesize + "]";
	}
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
