package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.EventDispatchAction;

import dao.MST_EmployeeDAO;
import form.EmployeeActionForm;
import form.EmployeePassWordActionForm;
import salemanagement.MST_EmployeeDTO;
import salemanagement.SaMgHelper;

public class EmployeeAction extends EventDispatchAction {

	/**
	 * 登録ボタン押下時のアクション
	 */
	public ActionForward btnRegist(ActionMapping mapping,
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

			EmployeeActionForm ef = (EmployeeActionForm)form;
			MST_EmployeeDAO eda = new MST_EmployeeDAO();

			//従業員NoがDBに存在する場合は従業員登録画面へ遷移
			int result = eda.EmpNoCountSearch(ef.getTxtEmpNo());
			if(result > 0) {
				return (mapping.findForward("employee"));
			}

			//LogInIDがDBに存在する場合はUpdate しない場合はInsert
			session.removeAttribute("mode");
			MST_EmployeeDTO edd = SaMgHelper.getInstance().EDTOMapping(ef, 1);
			result = eda.LogInIDCountSearch(edd.getLogInID());
			if(result > 0) {
				eda.EmployeeUpdate(edd);
			} else {
				eda.EmployeeInsert(edd);
			}

			return (mapping.findForward("employee"));
		} catch (Exception e) {
	        req.setAttribute("error", e.getMessage());
		    return (mapping.findForward("error"));
		}
	}

	/**
	 * 削除ボタン押下時のアクション
	 */
	public ActionForward btnDelete(ActionMapping mapping,
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

			EmployeeActionForm ef = (EmployeeActionForm)form;
			MST_EmployeeDTO edd = SaMgHelper.getInstance().EDTOMapping(ef, 0);
			MST_EmployeeDAO eda = new MST_EmployeeDAO();

			//削除対象がシステム管理者権限を持っているかを確認
			List<MST_EmployeeDTO> resultlist = eda.EmployeeSearch(edd);
			if(resultlist.size() == 0) {
				return (mapping.findForward("employee"));
			}
			MST_EmployeeDTO result = resultlist.get(0);
			int index = 0;
			StringBuilder sb = new StringBuilder(result.getUserRole());
			index = sb.lastIndexOf("1");

			//システム管理者ならば、最後の一人か否かを確認  最後の一人ならば削除しない
			if(index == 9) {
				resultlist = eda.EmployeeSearch(null);
				int count = 0;
				for(MST_EmployeeDTO fedd : resultlist) {
					sb = new StringBuilder(fedd.getUserRole());
					if(sb.lastIndexOf("1") == 9) {
						count++;
					}
					if(count == 2) {
						break;
					}
				}
				if(count < 2) {
					return (mapping.findForward("employee"));
				}
			}
			//対象を削除
			eda.EmployeeUpdate(edd);

			return (mapping.findForward("employee"));
		} catch (Exception e) {
	        req.setAttribute("error", e.getMessage());
		    return (mapping.findForward("error"));
		}
	}

	/**
	 * 登録ボタン(パスワード)押下時のアクション
	 */
	public ActionForward btnPWResist(ActionMapping mapping,
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

			//画面情報をコピー
			EmployeeActionForm ef = (EmployeeActionForm)form;
			String pw1 = ef.getTxtLogInPW1();
			String pw2 = ef.getTxtLogInPW2();
			EmployeePassWordActionForm epwf = (EmployeePassWordActionForm)session.getAttribute("EmpPWForm");
			BeanUtils.copyProperties(ef, epwf);
			//入力条件を満たさなければ元の画面へ、満たせばパスワードを登録
			if(pw1.equals("") || pw2.equals("") || !pw1.equals(pw2) || ef.getTxtPW().equals(pw1)) {
				return (mapping.findForward("employee"));
			} else {
				ef.setTxtPW(pw1);
				ef.setTxtLogInPW1("");
				ef.setTxtLogInPW2("");
			}
			session.removeAttribute("password");
			session.removeAttribute("mode");

			return (mapping.findForward("employee"));
		} catch (Exception e) {
	        req.setAttribute("error", e.getMessage());
		    return (mapping.findForward("error"));
		}
	}

	/**
	 * 閉じるボタン(パスワード)押下時のアクション
	 */
	public ActionForward btnClose(ActionMapping mapping,
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

			//入力されたパスワードを削除
			EmployeeActionForm ef = (EmployeeActionForm)form;
			ef.setTxtLogInPW1("");
			ef.setTxtLogInPW2("");
			session.removeAttribute("password");

			return (mapping.findForward("employee"));
		} catch (Exception e) {
	        req.setAttribute("error", e.getMessage());
		    return (mapping.findForward("error"));
		}
	}
}
