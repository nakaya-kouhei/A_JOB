package form;

import org.apache.struts.validator.ValidatorForm;

public class EmployeeActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String txtEmpNo;
	private String txtName;
	private String txtKanaName;
	private String txtLogInID;
	private String txtMail;
	private String dlstBranch;
	private String dlstDepartment;
	private String txtBoss;
	private String[] chRole;
	private String txtPW;
	private String txtLogInPW1;
	private String txtLogInPW2;

	public EmployeeActionForm() {
		this.txtEmpNo = "";
		this.txtName = "";
		this.txtKanaName = "";
		this.txtLogInID = "";
		this.txtMail = "";
		this.dlstBranch = "";
		this.dlstDepartment = "";
		this.txtBoss = "";
		this.chRole = null;
		this.txtPW = "";
		this.txtLogInPW1 = "";
		this.txtLogInPW2 = "";
	}

	public String getTxtEmpNo() {
		return txtEmpNo;
	}

	public void setTxtEmpNo(String txtEmpNo) {
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

	public String getDlstBranch() {
		return dlstBranch;
	}

	public void setDlstBranch(String dlstBranch) {
		this.dlstBranch = dlstBranch;
	}

	public String getDlstDepartment() {
		return dlstDepartment;
	}

	public void setDlstDepartment(String dlstDepartment) {
		this.dlstDepartment = dlstDepartment;
	}

	public String getTxtBoss() {
		return txtBoss;
	}

	public void setTxtBoss(String txtBoss) {
		this.txtBoss = txtBoss;
	}

	public String[] getChRole() {
		return chRole;
	}

	public void setChRole(String[] chRole) {
		this.chRole = chRole;
	}

	public String getTxtPW() {
		return txtPW;
	}

	public void setTxtPW(String txtPW) {
		this.txtPW = txtPW;
	}

	public String getTxtLogInPW1() {
		return txtLogInPW1;
	}

	public void setTxtLogInPW1(String txtLogInPW1) {
		this.txtLogInPW1 = txtLogInPW1;
	}

	public String getTxtLogInPW2() {
		return txtLogInPW2;
	}

	public void setTxtLogInPW2(String txtLogInPW2) {
		this.txtLogInPW2 = txtLogInPW2;
	}

}
