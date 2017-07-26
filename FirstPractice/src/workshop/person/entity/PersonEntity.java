package workshop.person.entity;

public class PersonEntity {
	
	private String name;
	private	char gender;
	private	String ssn;
	private	String address;
	private	String phone;
	
	public PersonEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PersonEntity(String name, String ssn, String address, String phone) {
		super();
		setName(name);
		setGender(this.gender);
		setSsn(ssn);
		setAddress(address);
		setPhone(phone);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
		if(ssn.charAt(6)=='1' ||ssn.charAt(6)=='3')this.gender = '��';
		else if(ssn.charAt(6)=='2' ||ssn.charAt(6)=='4')this.gender = '��';
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "[�̸�] " + name + "      [����] " + gender + "      [��ȭ��ȣ] " + phone;
	}
	
	
	
	
}
