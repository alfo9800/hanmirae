package kr.or.member;

public class MemberVO {
	// 멤버변수 만들기(아래)
	private String name;
	private int age;
	private String phoneNum;

	// 멤버변수는 보안 때문에 private.----- get,set은 public으로 생성됨.(class안에서 읽어들이기 편하게)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	@Override
	public String toString() {
		return "디버그용 MemberVO [name=" + name + ", age=" + age + ", phoneNum=" + phoneNum + "]";
	}
}
