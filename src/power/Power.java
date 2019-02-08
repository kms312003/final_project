package power;

import java.util.Date;

public class Power {

	private int id;					// PK
	private String code;			// 준시스템 크롤링 코드
	private String productCode;		// 제품코드
	private String productName;     // 제품
	private ProductCompany productCompany;  // 제조회사
	private String productSort;     // 제품 분류
	private int nominalOutput;      // 표기 출력
	private int ratedOutput;		// 정격 출력
	private Date productDate;  		// 제품 등록일
	private Date regDate;			// 제품 입력일
	private int price; 				// 가격
	private int count;				// 수량
	private String filename;		// 파일 이름
	private int filesize;			// 파일 사이즈
	
	public enum ProductCompany {
		MICRONICS, ANTEC, ZALMAN;
	}
	
	@Override
	public String toString() {
		return "Power [id=" + id + ", code=" + code + ", productCode=" + productCode + ", productName=" + productName
				+ ", productCompany=" + productCompany + ", productSort=" + productSort + ", nominalOutput="
				+ nominalOutput + ", ratedOutput=" + ratedOutput + ", productDate=" + productDate + ", regDate="
				+ regDate + ", price=" + price + ", count=" + count + ", filename=" + filename + ", filesize="
				+ filesize + "]";
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
	public int getNominalOutput() {
		return nominalOutput;
	}
	public void setNominalOutput(int nominalOutput) {
		this.nominalOutput = nominalOutput;
	}
	public int getRatedOutput() {
		return ratedOutput;
	}
	public void setRatedOutput(int ratedOutput) {
		this.ratedOutput = ratedOutput;
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
