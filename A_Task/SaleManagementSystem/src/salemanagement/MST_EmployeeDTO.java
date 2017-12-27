package salemanagement;

import java.sql.Timestamp;

public class MST_EmployeeDTO {

	private int EmpID;
	private int BranchID;
	private int DepartmentID;
	private int EmpNo;
	private String FullName;
	private String KanaName;
	private String LogInID;
	private String PassWord;
	private int Enable;
	private String EMail;
	private String UserRole;
	private String MailPWD;
	private Timestamp PWUPDay;
	private int BossID;

	public int getEmpID() {
		return EmpID;
	}

	public void setEmpID(int empID) {
		EmpID = empID;
	}

	public int getBranchID() {
		return BranchID;
	}

	public void setBranchID(int branchID) {
		BranchID = branchID;
	}

	public int getDepartmentID() {
		return DepartmentID;
	}

	public void setDepartmentID(int departmentID) {
		DepartmentID = departmentID;
	}

	public int getEmpNo() {
		return EmpNo;
	}

	public void setEmpNo(int empNo) {
		EmpNo = empNo;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public String getKanaName() {
		return KanaName;
	}

	public void setKanaName(String kanaName) {
		KanaName = kanaName;
	}

	public String getLogInID() {
		return LogInID;
	}

	public void setLogInID(String logInID) {
		LogInID = logInID;
	}

	public String getPassWord() {
		return PassWord;
	}

	public void setPassWord(String passWord) {
		PassWord = passWord;
	}

	public int getEnable() {
		return Enable;
	}

	public void setEnable(int enable) {
		Enable = enable;
	}

	public String getEMail() {
		return EMail;
	}

	public void setEMail(String eMail) {
		EMail = eMail;
	}

	public String getUserRole() {
		return UserRole;
	}

	public void setUserRole(String userRole) {
		UserRole = userRole;
	}

	public String getMailPWD() {
		return MailPWD;
	}

	public void setMailPWD(String mailPWD) {
		MailPWD = mailPWD;
	}

	public Timestamp getPWUPDay() {
		return PWUPDay;
	}

	public void setPWUPDay(Timestamp pwUPDay) {
		PWUPDay = pwUPDay;
	}

	public int getBossID() {
		return BossID;
	}

	public void setBossID(int bossID) {
		BossID = bossID;
	}

}
