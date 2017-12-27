package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.EmpSelectActionForm;
import form.EmployeeActionForm;
import form.EmployeePassWordActionForm;
import salemanagement.SaMgHelper;

public class EmployeePassWordAction extends Action {
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

			EmployeePassWordActionForm epwf = (EmployeePassWordActionForm)form;
			String mode = (String)session.getAttribute("mode");

			//画面の情報をコピー
			if(mode == null) {
				EmployeeActionForm ef = (EmployeeActionForm)session.getAttribute("EmpForm");
				BeanUtils.copyProperties(epwf, ef);
			} else if(mode.equals("NameSearch") || mode.equals("BossSearch")) {
				EmpSelectActionForm esf = (EmpSelectActionForm)session.getAttribute("EmpSelectForm");
				BeanUtils.copyProperties(epwf, esf);
			}
			session.setAttribute("password", "on");
			session.setAttribute("mode", "PassWord");

			return (mapping.findForward("employee"));
		}catch (Exception e) {
	        req.setAttribute("error", e.getMessage());
		    return (mapping.findForward("error"));
		}
	}

}
