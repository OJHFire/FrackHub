package FracHub;


public class Customer {
	
	public static String filename = "CustomerList.txt";
	
	private Name name;
	private String cust_num;
	private String password;
	private String address;
	private String email;
	private String phone_num;
	
	public Customer() {
		
		name = new Name();
		cust_num = "";
		password = "";
		address = "";
		email = "";
		phone_num = "";
		
	}
	
	public Customer(Name name, String cust_num, String password, 
			String address, String email, String phone_num) {
		
		this.name = name;
		this.cust_num = cust_num;
		this.password = password;
		this.address = address;
		this.email = email;
		this.phone_num = phone_num;
		
	}
	
	public void printCust() {
		System.out.println("Name: " + name.getLastCommaFirst());
		System.out.println("Address: " + address);
		System.out.println("Email: " + email);
		System.out.println("Phone Number: " + phone_num);
		System.out.println("Customer Number: " + cust_num);
		System.out.println("Password: " + password);
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getCust_num() {
		return cust_num;
	}

	public void setCust_num(String cust_num) {
		this.cust_num = cust_num;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	
	

}
