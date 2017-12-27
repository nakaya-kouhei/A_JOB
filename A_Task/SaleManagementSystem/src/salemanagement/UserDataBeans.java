package salemanagement;

import java.io.Serializable;

/**
 * Servlet implementation class UserDataBeans
 */
public class UserDataBeans implements Serializable {
	private static final long serialVersionUID = 1L;

	private int txtEmpID;
	private int txtEmpNo;
	private String txtName;
	private String txtKanaName;
	private String txtLogInID;
	private String txtMail;
	private int dlstBranch;
	private int dlstDepartment;
	private int txtBoss;
	private String chRole;
	private String txtPW;

	public UserDataBeans() {
		this.txtEmpID = 0;
		this.txtEmpNo = 0;
		this.txtName = "";
		this.txtKanaName = "";
		this.txtLogInID = "";
		this.txtMail = "";
		this.dlstBranch = 0;
		this.dlstDepartment = 0;
		this.txtBoss = 0;
		this.chRole = "";
		this.txtPW = "";
	}

	public int getTxtEmpID() {
		return txtEmpID;
	}

	public void setTxtEmpID(int txtEmpID) {
		this.txtEmpID = txtEmpID;
	}

	public int getTxtEmpNo() {
		return txtEmpNo;
	}

	public void setTxtEmpNo(int txtEmpNo) {
		this.txtEmpNo = txtEmpNo;
	}

	public String getTxtName() {
		return txtName;
	}

	public void setTxtName(String txtName) {
		this.txtName = txtName;
	}

	public String getTxtKanaName() {
		return txtKanaName;
	}

	public void setTxtKanaName(String txtKanaName) {
		this.txtKanaName = txtKanaName;
	}

	public String getTxtLogInID() {
		return txtLogInID;
	}

	public void setTxtLogInID(String txtLogInID) {
		this.txtLogInID = txtLogInID;
	}

	public String getTxtMail() {
		return txtMail;
	}

	public void setTxtMail(String txtMail) {
		this.txtMail = txtMail;
	}

	public int getDlstBranch() {
		return dlstBranch;
	}

	public void setDlstBranch(int dlstBranch) {
		this.dlstBranch = dlstBranch;
	}

	public int getDlstDepartment() {
		return dlstDepartment;
	}

	public void setDlstDepartment(int dlstDepartment) {
		this.dlstDepartment = dlstDepartment;
	}

	public int getTxtBoss() {
		return txtBoss;
	}

	public void setTxtBoss(int txtBoss) {
		this.txtBoss = txtBoss;
	}

	public String getChRole() {
		return chRole;
	}

	public void setChRole(String chRole) {
		this.chRole = chRole;
	}

	public String getTxtPW() {
		return txtPW;
	}

	public void setTxtPW(String txtPW) {
		this.txtPW = txtPW;
	}

	public void mappingUserData(MST_EmployeeDTO edd) {
		this.txtEmpID = edd.getEmpID();
		this.txtEmpNo = edd.getEmpNo();
		this.txtName = edd.getFullName();
		this.txtKanaName = edd.getKanaName();
		this.txtLogInID = edd.getLogInID();
		this.txtMail = edd.getEMail();
		this.dlstBranch = edd.getBranchID();
		this.dlstDepartment = edd.getDepartmentID();
		this.txtBoss = edd.getEnable();
		this.chRole = edd.getUserRole();
		this.txtPW = edd.getPassWord();
	}

}
