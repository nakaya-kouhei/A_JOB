package form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import salemanagement.EmployeeListDTO;

/**
 * Servlet implementation class EmpSearchBossActionForm
 */
public class EmpSearchBossActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private List<EmployeeListDTO> grdList;
	private String searchType;
	private String txtNameSearch;
	private String dlstBranchSearch;
	private String dlstDepSearch;
	private String rdoDelete;
	private String mode;

	public EmpSearchBossActionForm() {
		this.grdList = new ArrayList<>();
		this.searchType = "";
		this.txtNameSearch = "";
		this.dlstBranchSearch = "";
		this.dlstDepSearch = "";
		this.rdoDelete = "";
		this.mode = "";
	}

	public List<EmployeeListDTO> getGrdList() {
		return grdList;
	}

	public void setGrdList(List<EmployeeListDTO> grdList) {
		this.grdList = grdList;
	}

	public String getTest() {
		return searchType;
	}

	public void setTest(String searchType) {
		this.searchType = searchType;
	}

	public String getTxtNameSearch() {
		return txtNameSearch;
	}

	public void setTxtNameSearch(String txtNameSearch) {
		this.txtNameSearch = txtNameSearch;
	}

	public String getDlstBranchSearch() {
		return dlstBranchSearch;
	}

	public void setDlstBranchSearch(String dlstBranchSearch) {
		this.dlstBranchSearch = dlstBranchSearch;
	}

	public String getDlstDepSearch() {
		return dlstDepSearch;
	}

	public void setDlstDepSearch(String dlstDepSearch) {
		this.dlstDepSearch = dlstDepSearch;
	}

	public String getRdoDelete() {
		return rdoDelete;
	}

	public void setRdoDelete(String rdoDelete) {
		this.rdoDelete = rdoDelete;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

}
