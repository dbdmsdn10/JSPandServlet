package manage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class CustomerSql extends DBConnect {
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet result = null;
	String errMsg = null;
	String tableName = "user";

	public String validCustomerId(String customer_id) {
		if (customer_id == null || !validateNumber(customer_id, 'C')) {
			return "고객번호의 형식이 이상합니다(형식=c-xxxx)";
		}
		return null;
	}

	private boolean validateNumber(String str, char type) {

		if (str.length() == 6) {
			if (str.charAt(0) == type && str.charAt(1) == '-') {
				if (isInteger(str.substring(2, 5))) {
					return true;
				}
			}
		}
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isInteger(String substring) {
		try {
			Integer.parseInt(substring);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

//-----------------------------------------------------------------------------
	public String insertCustomerRecord(Customer customer) {// insert정보삽입
		String sql = "insert into " + tableName + " values(?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, customer.getid());
			pstmt.setString(2, customer.getname());
			pstmt.setString(3, customer.gettel());
			pstmt.setString(4, customer.getaddress());
			pstmt.executeUpdate();
		} catch (Exception e) {
			errMsg = "insert오류" + e.getMessage();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}

		return errMsg;
	}

	public Vector<Customer> selectCustomerRecord(String query) {// 고객정보검색
		Vector<Customer> customers = new Vector<Customer>();
		try {
			stmt = con.createStatement();
			result = stmt.executeQuery(query);

			while (result.next()) {
				Customer aCustomer = new Customer();
				aCustomer.setid(result.getString(1));
				aCustomer.setid(result.getString(2));
				aCustomer.setid(result.getString(3));
				aCustomer.setid(result.getString(4));
				customers.addElement(aCustomer);
			}
		} catch (SQLException ex) {
			System.out.println("SQLException" + ex.getMessage());
		} finally {
			try {
				if (result != null) {
					result.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				System.out.println("검색오류");
			}
		}
		return null;
	}

	public String updateCustomerRecord(Customer customer) {// 정보수정
		String sql = "update " + tableName + " set phone=?,address=? where id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, customer.gettel());
			pstmt.setString(2, customer.getaddress());
			pstmt.setString(3, customer.getid());
			pstmt.executeUpdate();
		} catch (Exception e) {
			errMsg = "고객정보 변경오류" + e.getMessage();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {

			}
		}
		return errMsg;
	}

	public String deleteCustomerRecord(String customer_id) {//정보삭제
		String sql = "delete from " + tableName + " where id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, customer_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			errMsg = "고객정보 삭제오류" + e.getMessage();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}
		return errMsg;
	}

	public String compositeQuerry(String customer_id, String customer_name) {// 검색??
		String query = "select*from " + tableName;
		if (customer_id.length() > 0 && customer_name.length() > 0) {
			query += " where id= " + "" + customer_id + "";
			query += " and name= " + "" + customer_name + "";
		} else if (customer_id.length() > 0 && customer_name.length() == 0) {
			query += " where id= " + "" + customer_id + "";
		} else if (customer_id.length() == 0 && customer_name.length() > 0) {
			query += " where name= " + "" + customer_name + "";
		}
		return customer_name;
	}
	
	public String compositeQuery1(String customer_id) {
		return "selet*from "+tableName+" where id="+customer_id;
	}

}
