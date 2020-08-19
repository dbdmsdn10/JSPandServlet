package manage;

public class Customer {
	private String id;
	private String name;
	private String tel;
	private String address;
	public Customer() {
		id=null;
		name=null;
		tel=null;
		address=null;
	}
	public void setid(String id) {
		this.id=id;
	}
	public void setname(String name) {
		this.name=name;
	}
	public void settel(String tel) {
		this.tel=tel;
	}
	public void setaddress(String address) {
		this.address=address;
	}
	
	public String getid() {
		return id;
	}
	public String getname() {
		return name;
	}
	public String gettel() {
		return tel;
	}
	public String getaddress() {
		return address;
	}
	
}
