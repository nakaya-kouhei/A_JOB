package form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.util.LabelValueBean;
import org.apache.struts.validator.ValidatorForm;

public class LogInActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String userName;
	private String logInID;
	private String passWord;
	private String dlstBranch;
	private List<LabelValueBean> dBranchList;
	private String dlstDepartment;
	private List<LabelValueBean> dDepartmentList;
	private String[] chRole;
	private List<LabelValueBean> chRoleList;
	private String dlstBranchSearch;
	private List<LabelValueBean> dlstBranchList;
	private String dlstDepSearch;
	private List<LabelValueBean> dlstDepList;

	public LogInActionForm() {
		userName = "";
		logInID = "";
		passWord = "";
		dlstBranch = "";
		dBranchList = new ArrayList<>();
		dlstDepartment = "";
		dDepartmentList = new ArrayList<>();
		chRole = null;
		chRoleList = new ArrayList<>();
		dlstBranchSearch = "";
		dlstBranchList = new ArrayList<>();
		dlstDepSearch = "";
		dlstDepList = new ArrayList<>();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLogInID() {
		return logInID;
	}

	public void setLogInID(String logInID) {
		this.logInID = logInID;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getDlstBranch() {
		return dlstBranch;
	}

	public void setDlstBranch(String dlstBranch) {
		this.dlstBranch = dlstBranch;
	}

	public List<LabelValueBean> getdBranchList() {
		return dBranchList;
	}

	public void setdBranchList(List<LabelValueBean> dBranchList) {
		this.dBranchList = dBranchList;
	}

	public String getDlstDepartment() {
		return dlstDepartment;
	}

	public void setDlstDepartment(String dlstDepartment) {
		this.dlstDepartment = dlstDepartment;
	}

	public List<LabelValueBean> getdDepartmentList() {
		return dDepartmentList;
	}

	public void setdDepartmentList(List<LabelValueBean> dDepartmentList) {
		this.dDepartmentList = dDepartmentList;
	}

	public String[] getChRole() {
		return chRole;
	}

	public void setChRole(String[] chRole) {
		this.chRole = chRole;
	}

	public List<LabelValueBean> getChRoleList() {
		return chRoleList;
	}

	public void setChRoleList(List<LabelValueBean> chRoleList) {
		this.chRoleList = chRoleList;
	}

	public String getDlstBranchSearch() {
		return dlstBranchSearch;
	}

	public void setDlstBranchSearch(String dlstBranchSearch) {
		this.dlstBranchSearch = dlstBranchSearch;
	}

	public List<LabelValueBean> getDlstBranchList() {
		return dlstBranchList;
	}

	public void setDlstBranchList(List<LabelValueBean> dlstBranchList) {
		this.dlstBranchList = dlstBranchList;
	}

	public String getDlstDepSearch() {
		return dlstDepSearch;
	}

	public void setDlstDepSearch(String dlstDepSearch) {
		this.dlstDepSearch = dlstDepSearch;
	}

	public List<LabelValueBean> getDlstDepList() {
		return dlstDepList;
	}

	public void setDlstDepList(List<LabelValueBean> dlstDepList) {
		this.dlstDepList = dlstDepList;
	}

}
