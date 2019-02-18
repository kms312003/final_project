package user;

import java.sql.Timestamp;

public class User {
	private int id; // PK
	private String email; // email(아이디로 사용)
	private String name; // 이름
	private String password; // 비밀번호

	public enum Gender {
		MALE, FEMALE;
	};

	private Gender gender; // 성별 (enum -'m','f')
	private int birth; // 생년월일(6자리)

	public enum Job {
		STUDENT, EMPLOYED, UNEMPLOYED, ETC;
	};

	private Job job; // 직업(enum -'student' 'employed' 'unemployed', 'etc')
	private Timestamp reg_date; // 등록일
	
	private String postcode;  //우편번호
	private String roadAddress;  //도로명주소
	private String jibunAddress; //지번 주소
	private String detailAddress; //상세주소
	private String extraAddress; //참고사항 (ex: ~동, 빌라이름...)
	private String phoneNum; //전화번호
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getBirth() {
		return birth;
	}

	public void setBirth(int birth) {
		this.birth = birth;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getRoadAddress() {
		return roadAddress;
	}

	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}

	public String getJibunAddress() {
		return jibunAddress;
	}

	public void setJibunAddress(String jibunAddress) {
		this.jibunAddress = jibunAddress;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getExtraAddress() {
		return extraAddress;
	}

	public void setExtraAddress(String extraAddress) {
		this.extraAddress = extraAddress;
	}
	
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password + ", gender="
				+ gender + ", birth=" + birth + ", job=" + job + ", reg_date=" + reg_date + ", postcode=" + postcode
				+ ", roadAddress=" + roadAddress + ", jibunAddress=" + jibunAddress + ", detailAddress=" + detailAddress
				+ ", extraAddress=" + extraAddress + ", phoneNum=" + phoneNum + "]";
	}

	
}
