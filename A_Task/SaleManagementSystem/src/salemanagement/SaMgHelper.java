package salemanagement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.util.LabelValueBean;

import dao.MST_EmployeeDAO;
import form.EmployeeActionForm;

/**
 * @author nakaya-k
 * アクションクラスで使用するメソッドを集めたヘルパークラス
 * 煩雑な処理はここに実装
 *
 */
public class SaMgHelper {

	public static SaMgHelper getInstance() {
		return new SaMgHelper();
	}

	/**
	 * ユーザーと権限をチェック
	 * @param session
	 * @return check 正しいならばtrue、不正ならばfalse
	 */
	public boolean userCheck(HttpSession session) {
		boolean check = true;
		String login = (String)session.getAttribute("login");
		UserDataBeans udb = (UserDataBeans)session.getAttribute("udb");
		String role = udb.getChRole();
		char cr = role.charAt(role.length() - 1);
		if(login == null || !login.equals(udb.getTxtName()) || cr != '1') {
			session.invalidate();
			check = false;
		}

		return check;
	}

	/**
	 * 不正なアクセスをチェック
	 * @param session
	 * @param req
	 * @return check 正しいならばtrue、不正ならばfalse
	 */
	public boolean accessCheck(HttpSession session, HttpServletRequest req) {
		boolean check = true;
		String accesschk = req.getParameter("ac");
        if(accesschk == null || (Integer)session.getAttribute("ac") != Integer.parseInt(accesschk)){
			session.invalidate();
			check = false;
        }
		return check;
	}

	/**
	 * DBの権限を画面側に変換処理
	 * @param chRoleList
	 * @return sb 画面側に変換された権限
	 */
	public String[] RoleConTxt(String role) {
		String[] roleList = new String[role.length()];
		char cr;
		int num = 0;
		for(int i=1; i<=role.length(); i++) {
			cr = role.charAt(role.length() - i);
			if(cr == '1') {
				roleList[num] = String.valueOf(i);
				num++;
			}
		}
		return roleList;
	}

	/**
	 * 画面から入力された権限をDB側に変換処理
	 * @param chRoleList
	 * @return sb 従業員マスタ側に変換された権限
	 */
	public String RoleConMST_E(String[] chRoleList) {
		String chrole = "0000000000";
		StringBuilder sb = new StringBuilder(chrole);
		for(String role : chRoleList) {
			int num = 10 - Integer.valueOf(role);
			sb.setCharAt(num, '1');
		}

		return sb.toString();
	}

	/**
	 * EmployeeFormからDTOへの変換処理
	 * @param ef
	 * @param enable
	 * @return edd 従業員マスタに対応したDTO
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public MST_EmployeeDTO EDTOMapping(EmployeeActionForm ef, int enable) throws SQLException, ClassNotFoundException {
		MST_EmployeeDTO edd = new MST_EmployeeDTO();

		edd.setBranchID(Integer.valueOf(ef.getDlstBranch()));
		edd.setDepartmentID(Integer.valueOf(ef.getDlstDepartment()));
		edd.setEmpNo(Integer.valueOf(ef.getTxtEmpNo()));
		edd.setFullName(ef.getTxtName());
		edd.setKanaName(ef.getTxtKanaName());
		edd.setLogInID(ef.getTxtLogInID());
		edd.setPassWord(ef.getTxtPW());
		edd.setEnable(enable);
		edd.setEMail(ef.getTxtMail());
		String[] chRoleList = ef.getChRole();
		edd.setUserRole(RoleConMST_E(chRoleList));
		edd.setBossID(MST_EmployeeDAO.getInstance().BossSearch(ef.getTxtBoss()));

		return edd;
	}

	/**
	 * 支店マスタから支店IDと支店名のリストを取得処理
	 * @return optionlist IDと名前のリスト
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<LabelValueBean> getBranchList(HashMap<String, List<ListCollectionDTO>> resultMap) throws ClassNotFoundException, SQLException {
		List<LabelValueBean> optionlist = new ArrayList<>();
		List<ListCollectionDTO> brlist = resultMap.get("branch");
		for(int i=0; i<brlist.size(); i++) {
			optionlist.add(new LabelValueBean(brlist.get(i).getBranchName(), String.valueOf(brlist.get(i).getBranchID())));
		}
		return optionlist;
	}

	/**
	 * 部署マスタから部署IDと部署名のリストを取得処理
	 * @return optionlist IDと名前のリスト
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<LabelValueBean> getDepertmentList(HashMap<String, List<ListCollectionDTO>> resultMap) throws ClassNotFoundException, SQLException {
		List<LabelValueBean> optionlist = new ArrayList<>();
		List<ListCollectionDTO> delist = resultMap.get("department");
		for(int i=0; i<delist.size(); i++) {
			optionlist.add(new LabelValueBean(delist.get(i).getDepartmentName(), String.valueOf(delist.get(i).getDepartmentID())));
		}
		return optionlist;
	}

	/**
	 * 権限マスタから権限Noと権限名のリストを取得処理
	 * @return optionlist Noと名前のリスト
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<LabelValueBean> getRoleList(HashMap<String, List<ListCollectionDTO>> resultMap) throws ClassNotFoundException, SQLException {
		List<LabelValueBean> optionlist = new ArrayList<>();
		List<ListCollectionDTO> rolist = resultMap.get("role");
		for(int i=0; i<rolist.size(); i++) {
			optionlist.add(new LabelValueBean(rolist.get(i).getRoleName(), String.valueOf(rolist.get(i).getRoleNo())));
		}
		return optionlist;
	}

}
