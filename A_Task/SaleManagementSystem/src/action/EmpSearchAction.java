package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dao.SaleManagementDAO;
import form.EmpSearchActionForm;
import salemanagement.SaMgHelper;

public class EmpSearchAction extends Action {
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

			EmpSearchActionForm esf = (EmpSearchActionForm)form;
			//DBから条件に一致する従業員リストを取得
			esf.setGrdList(SaleManagementDAO.getInstance().EListSearch(esf));
			req.setAttribute("mode", "EmpSearchForm");

			return (mapping.findForward("employeelist"));
		}catch (Exception e) {
	        req.setAttribute("error", e.getMessage());
		    return (mapping.findForward("error"));
		}
	}

}
