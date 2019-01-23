package user;

import java.sql.Timestamp;

public class User {
	private int id; 	  // PK
	private String email; // email(���̵�� ���)
	private String name; // �̸�
	private String password; // ��й�ȣ

	public enum Gender {
		MALE, FEMALE;
	};

	private Gender gender; // ���� (enum -'m','f')
	private int birth; // �������(6�ڸ�)

	public enum Job {
		STUDENT, EMPLOYED, UNEMPLOYED, ETC;
	};

	private Job job; // ����(enum -'student' 'employed' 'unemployed', 'etc')
	private Timestamp reg_date; // �����

	@Override
	public String toString() {
		return "UserLogDataBean [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password
				+ ", gender=" + gender + ", birth=" + birth + ", job=" + job + ", reg_date=" + reg_date + "]";
	}

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

}
