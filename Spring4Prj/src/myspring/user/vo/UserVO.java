package myspring.user.vo;

public class UserVO {
	private String userid;
	private String name;
	private String gender;
	private String city;
	public String getUserid() {
		return userid;
	}
	public String getName() {
		return name;
	}
	public UserVO(String userid, String name, String gender, String city) {
		super();
		this.userid = userid;
		this.name = name;
		this.gender = gender;
		this.city = city;
	}
	public String getGender() {
		return gender;
	}
	public String getCity() {
		return city;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public UserVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserVO [userid=" + userid + ", name=" + name + ", gender=" + gender + ", city=" + city + "]";
	}
	
	
	
}
