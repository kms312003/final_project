package user;

import java.sql.Timestamp;

public class User {
	private Long id; // PK
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

	@Override
	public String toString() {
		return "UserLogDataBean [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password
				+ ", gender=" + gender + ", birth=" + birth + ", job=" + job + ", reg_date=" + reg_date + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

}
