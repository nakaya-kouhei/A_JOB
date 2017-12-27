package action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dao.MST_EmployeeDAO;
import dao.SaleManagementDAO;
import form.LogInActionForm;
import salemanagement.ListCollectionDTO;
import salemanagement.MST_EmployeeDTO;
import salemanagement.SaMgHelper;
import salemanagement.UserDataBeans;

public class LogInAction extends Action  {
	public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest req,
            HttpServletResponse res) {

		HttpSession session = req.getSession();

		try {
			LogInActionForm lof = (LogInActionForm)form;
			MST_EmployeeDTO edt = new MST_EmployeeDTO();
			edt.setFullName(lof.getUserName());
			edt.setLogInID(lof.getLogInID());
			edt.setPassWord(lof.getPassWord());

			//入力情報で従業員マスタを検索
			MST_EmployeeDTO result = MST_EmployeeDAO.getInstance().LogInSearch(edt);
			if(result == null) {
				return (mapping.findForward("login"));
			}
			//ユーザーがシステム管理者であるかを判定
			String role = result.getUserRole();
			String[] roleList = SaMgHelper.getInstance().RoleConTxt(role);
			if(roleList[1].equals("1")) {
				return (mapping.findForward("login"));
			}
			//セッションを破棄
			session.invalidate();
			HttpSession resession = req.getSession();

			//ユーザー情報を格納しログイン状態に
			UserDataBeans udb = new UserDataBeans();
			udb.mappingUserData(result);
			resession.setAttribute("udb", udb);
			resession.setAttribute("login", udb.getTxtName());
			resession.setAttribute("ac", (int) (Math.random() * 1000));

			//DBから支店,部署,権限のリストを取得
			HashMap<String, List<ListCollectionDTO>> resultMap = SaleManagementDAO.getInstance().ListCollection();
			lof.setdBranchList(SaMgHelper.getInstance().getBranchList(resultMap));
			lof.setdDepartmentList(SaMgHelper.getInstance().getDepertmentList(resultMap));
			lof.setChRoleList(SaMgHelper.getInstance().getRoleList(resultMap));
			lof.setDlstBranchList(SaMgHelper.getInstance().getBranchList(resultMap));
			lof.setDlstDepList(SaMgHelper.getInstance().getDepertmentList(resultMap));
			resession.setAttribute("LogInForm", lof);

			return (mapping.findForward("employee"));

		} catch (Exception e) {
	        req.setAttribute("error", e.getMessage());
		    return (mapping.findForward("error"));
		}
	}
}
