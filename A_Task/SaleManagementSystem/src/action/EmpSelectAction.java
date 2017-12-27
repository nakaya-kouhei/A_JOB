package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dao.MST_EmployeeDAO;
import form.EmpSelectActionForm;
import salemanagement.MST_EmployeeDTO;
import salemanagement.SaMgHelper;

public class EmpSelectAction extends Action {
	public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest req,
            HttpServletResponse res) {

		HttpSession session = req.getSession();

		try {
			//ユーザーではない、適切な権限を持たない、不正なアクセスならばログイン画面へ
			if(!SaMgHelper.getInstance().userCheck(session)) {
				return (mapping.findForward("login"));
			} else if(!SaMgHelper.getInstance().accessCheck(session, req)) {
				return (mapping.findForward("login"));
			}

			EmpSelectActionForm esf = (EmpSelectActionForm)form;
			MST_EmployeeDAO eda = new MST_EmployeeDAO();
			MST_EmployeeDTO edd = eda.EmpNoSearch((String)req.getParameter("empSlNo"));

			String mode = (String)session.getAttribute("mode");
			if(mode.equals("NameSearch")) {
				//選択された従業員を取得
				esf.setTxtEmpNo(String.valueOf(edd.getEmpNo()));
				esf.setTxtName(edd.getFullName());
				esf.setTxtKanaName(edd.getKanaName());
				esf.setTxtLogInID(edd.getLogInID());
				esf.setTxtMail(edd.getEMail());
				esf.setDlstBranch(String.valueOf(edd.getBranchID()));
				esf.setDlstDepartment(String.valueOf(edd.getDepartmentID()));
				MST_EmployeeDTO eddBoss = eda.EmpIDSearch(String.valueOf(edd.getBossID()));
				esf.setTxtBoss(eddBoss.getFullName());
				esf.setChRole(SaMgHelper.getInstance().RoleConTxt(edd.getUserRole()));
				esf.setTxtPW(edd.getPassWord());
			} else if(mode.equals("BossSearch")){
				esf = (EmpSelectActionForm)session.getAttribute("EmpSelectForm");
				esf.setTxtBoss(edd.getFullName());
			}
			session.setAttribute("EmpSelectForm", esf);

			return (mapping.findForward("employee"));
		}catch (Exception e) {
	        req.setAttribute("error", e.getMessage());
		    return (mapping.findForward("error"));
		}
	}

}
