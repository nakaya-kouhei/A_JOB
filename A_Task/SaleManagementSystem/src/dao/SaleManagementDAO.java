package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import base.DBManager;
import form.EmpSearchActionForm;
import salemanagement.EmployeeListDTO;
import salemanagement.ListCollectionDTO;

/**
 * MST_Employee,MST_Branch,MST_Department
 * に対して操作処理を行う
 * DB接続系はDBManagerクラスに一任
 * @author nakaya-k
 *
 */
public class SaleManagementDAO {

	public static SaleManagementDAO getInstance() {
		return new SaleManagementDAO();
	}

	/**
	 * 支店、部署、権限各マスタから画面表示情報を取得
	 * @param esf
	 * @return resultMap 各マスタのデータが入ったマップ
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public HashMap<String, List<ListCollectionDTO>> ListCollection() throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement st = null;
		HashMap<String, List<ListCollectionDTO>> resultMap = new HashMap<>();

		try {
			//支店マスタを検索するSQL文 全件検索
			String sql = "Select * From Branch_t";
			con = DBManager.getConn();
			st = con.prepareStatement(sql);
			ResultSet rs = null;
			List<ListCollectionDTO> resultlist = new ArrayList<>();

			try {
				rs = st.executeQuery();
				while(rs.next()) {
					ListCollectionDTO resutset = new ListCollectionDTO();
					resutset.setBranchID(rs.getInt(1));
					resutset.setBranchName(rs.getString(2));
					resutset.setBranchKbn(rs.getInt(3));
					resultlist.add(resutset);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new SQLException(e);
			} finally {
				if(rs != null) {
					rs.close();
				}
			}
			resultMap.put("branch", resultlist);
			st.close();

			//部署マスタを検索するSQL文 全件検索
			sql = "Select * From Department_t Order By RowNo";
			st = con.prepareStatement(sql);
			rs = null;
			resultlist = new ArrayList<>();
			try {
				rs = st.executeQuery();
				while(rs.next()) {
					ListCollectionDTO resutset = new ListCollectionDTO();
					resutset.setDepartmentID(rs.getInt(1));
					resutset.setRowNo(rs.getInt(2));
					resutset.setDepartmentName(rs.getString(3));
					resultlist.add(resutset);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new SQLException(e);
			} finally {
				if(rs != null) {
					rs.close();
				}
			}
			resultMap.put("department", resultlist);
			st.close();

			//権限マスタを検索するSQL文 全件検索
			sql = "Select * From Role_t";
			st = con.prepareStatement(sql);
			rs = null;
			resultlist = new ArrayList<>();

			try {
				rs = st.executeQuery();
				while(rs.next()) {
					ListCollectionDTO resutset = new ListCollectionDTO();
					resutset.setRoleNo(rs.getInt(1));
					resutset.setRoleName(rs.getString(2));
					resultlist.add(resutset);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new SQLException(e);
			} finally {
				if(rs != null) {
					rs.close();
				}
			}
			resultMap.put("role", resultlist);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if(con != null) {
				con.close();
		        System.out.println("DBとの接続を解除しました");
			}
			if(st != null) {
				st.close();
			}
		}
		return resultMap;
	}

	/**
	 * 従業員を検索処理
	 * @param elf 従業員一覧画面で入力されたデータを保持
	 * @return 該当する従業員のリスト
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<EmployeeListDTO> EListSearch(EmpSearchActionForm esf) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement st = null;

		try {
			//DBを検索するSQL文
			String sql = "select T1.EmpNo, T1.FullName, T2.BranchName, T3.DepartmentName, T4.FullName " +
						"from Employee_t T1, Branch_t T2, Department_t T3, Employee_t T4 " +
						"where T1.BranchID=T2.BranchID(+) and T1.DepartmentID=T3.DepartmentID(+) and T1.BossID = T4.EmpID(+) and T1.Enable = ?";

			List<Integer> list = new ArrayList<>();
			if(esf != null) {
				if(esf.getDlstBranchSearch() != null && !esf.getDlstBranchSearch().equals("")) {
					sql += " And T2.BranchID = ?";
					list.add(1);
				}
				if(esf.getDlstDepSearch() != null && !esf.getDlstDepSearch().equals("")) {
					sql += " And T3.DepartmentID = ?";
					list.add(2);
				}
				if(esf.getTxtNameSearch() != null && !esf.getTxtNameSearch().equals("")) {
					sql += " And (T1.FullName like ? Or T1.KanaName like ?)";
					list.add(3);
				}
			}
			sql += " Order By T1.EmpNo";
			con = DBManager.getConn();
			st = con.prepareStatement(sql);

			//入力がない場合は有効な従業員を検索、ある場合は検索条件を設定
			if(list.isEmpty()) {
				st.setInt(1, 1);
			} else {
				if(esf.getRdoDelete() != null && esf.getRdoDelete().equals("")) {
					st.setInt(1, 1);
				} else {
					st.setInt(1, 0);
				}

				for(int i = 0; i<list.size(); i++) {
					int num = i + 2;
					switch(list.get(i)) {
					case 1:
						st.setInt(num, Integer.valueOf(esf.getDlstBranchSearch()));
						break;
					case 2:
						st.setInt(num, Integer.valueOf(esf.getDlstDepSearch()));
						break;
					case 3:
						st.setString(num, "%" + esf.getTxtNameSearch() + "%");
						st.setString(num+1, "%" + esf.getTxtNameSearch() + "%");
						break;
					default:
						break;
					}
				}
			}

			ResultSet rs = null;
			List<EmployeeListDTO> resultlist = new ArrayList<>();

			try {
				//該当従業員を取得
				rs = st.executeQuery();
				while(rs.next()) {
					EmployeeListDTO resultset = new EmployeeListDTO();
					resultset.setEmpNo(String.valueOf(rs.getInt(1)));
					resultset.setEmpName(rs.getString(2));
					resultset.setEmpBranch(rs.getString(3));
					resultset.setEmpDepartment(rs.getString(4));
					resultset.setEmpBossName(rs.getString(5));
					resultlist.add(resultset);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new SQLException(e);
			} finally {
				if(rs != null) {
					rs.close();
				}
			}

			return resultlist;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if(con != null) {
				con.close();
		        System.out.println("DBとの接続を解除しました");
			}
			if(st != null) {
				st.close();
			}
		}
	}
}
