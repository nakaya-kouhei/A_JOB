package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import base.DBManager;
import salemanagement.MST_EmployeeDTO;

/**
 * MST_Employee_tに対して操作処理を行う
 * DB接続系はDBManagerクラスに一任
 * @author nakaya-k
 *
 */
public class MST_EmployeeDAO {

	public static MST_EmployeeDAO getInstance() {
		return new MST_EmployeeDAO();
	}

	/**
	 * ログイン時に該当ユーザーを検索処理
	 * @param edd MST_Employee_tに対応したデータを保持
	 * @return 検索結果
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public MST_EmployeeDTO LogInSearch(MST_EmployeeDTO edd) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement st = null;

		try {
			//DBを検索するSQL文　該当件数を取得
			String sql = "Select Count(*) From Employee_t Where Enable = 1 AND FullName = ? And LogInID = ? And PassWord = ?";
			int count = 0;
			con = DBManager.getConn();
			st = con.prepareStatement(sql);
			st.setString(1, edd.getFullName());
			st.setString(2, edd.getLogInID());
			st.setString(3, edd.getPassWord());
			ResultSet rs = null;

			try {
				//該当件数を取得
				rs = st.executeQuery();
				rs.next();
				count = rs.getInt(1);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new SQLException(e);
			} finally {
				if(rs != null) {
					rs.close();
				}
			}

			st.close();
			MST_EmployeeDTO redd = new MST_EmployeeDTO();
			if(count == 1) {
				//DBを検索するSQL文　該当ユーザーを取得
				sql = "Select * From Employee_t Where FullName = ? And LogInID = ? And PassWord = ?";
				st = con.prepareStatement(sql);
				st.setString(1, edd.getFullName());
				st.setString(2, edd.getLogInID());
				st.setString(3, edd.getPassWord());
				rs = null;

				try {
					//該当ユーザーを取得
					rs = st.executeQuery();
					rs.next();
					redd.setEmpID(rs.getInt(1));
					redd.setBranchID(rs.getInt(2));
					redd.setDepartmentID(rs.getInt(3));
					redd.setEmpNo(rs.getInt(4));
					redd.setFullName(rs.getString(5));
					redd.setKanaName(rs.getString(6));
					redd.setLogInID(rs.getString(7));
					redd.setPassWord(rs.getString(8));
					redd.setEnable(rs.getInt(9));
					redd.setEMail(rs.getString(10));
					redd.setUserRole(rs.getString(11));
					redd.setPWUPDay(rs.getTimestamp(13));
					redd.setBossID(rs.getInt(14));
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					throw new SQLException(e);
				} finally {
					if(rs != null) {
						rs.close();
					}
				}
			}

			return redd;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			con.close();
			st.close();
		    System.out.println("DBとの接続を解除しました");
		}
	}

	/**
	 * EmpIDに該当するレコードの検索処理
	 * @param empid 検索する従業員ID
	 * @return 該当レコード
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public MST_EmployeeDTO EmpIDSearch(String empid) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement st = null;

		try {
			MST_EmployeeDTO edd = new MST_EmployeeDTO();
			//従業員IDが存在するならば検索処理
			if(!empid.equals("") && !empid.equals("0")) {
				//DBを検索するSQL文　該当レコードを取得
				String sql = "Select * From Employee_t Where EmpID = ?";
				con = DBManager.getConn();
				st = con.prepareStatement(sql);
				st.setString(1, empid);
				ResultSet rs = null;

				try {
					//該当レコードを取得
					rs = st.executeQuery();
					rs.next();
					edd.setEmpID(rs.getInt(1));
					edd.setBranchID(rs.getInt(2));
					edd.setDepartmentID(rs.getInt(3));
					edd.setEmpNo(rs.getInt(4));
					edd.setFullName(rs.getString(5));
					edd.setKanaName(rs.getString(6));
					edd.setLogInID(rs.getString(7));
					edd.setPassWord(rs.getString(8));
					edd.setEnable(rs.getInt(9));
					edd.setEMail(rs.getString(10));
					edd.setUserRole(rs.getString(11));
					edd.setBossID(rs.getInt(14));
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					throw new SQLException(e);
				} finally {
					if(rs != null) {
						rs.close();
					}
				}
			}

			return edd;
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

	/**
	 * 従業員Noに該当するレコードの件数検索処理
	 * @param empno 検索する従業員No
	 * @return 該当件数
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int EmpNoCountSearch(String empno) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement st = null;

		try {
			//DBを検索するSQL文　該当件数を取得
			String sql = "Select Count(*) From Employee_t Where EmpNo = ?";
			int count = 0;
			con = DBManager.getConn();
			st = con.prepareStatement(sql);
			st.setInt(1, Integer.valueOf(empno));
			ResultSet rs = null;

			try {
				//該当件数を取得
				rs = st.executeQuery();
				rs.next();
				count = rs.getInt(1);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new SQLException(e);
			} finally {
				if(rs != null) {
					rs.close();
				}
			}

			return count;
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

	/**
	 * 従業員Noに該当するレコードの検索処理
	 * @param empno 検索する従業員No
	 * @return 該当レコード
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public MST_EmployeeDTO EmpNoSearch(String empno) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement st = null;

		try {
			//DBを検索するSQL文　該当レコードを取得
			String sql = "Select * From Employee_t Where EmpNo = ?";
			con = DBManager.getConn();
			st = con.prepareStatement(sql);
			st.setInt(1, Integer.valueOf(empno));
			ResultSet rs = null;
			MST_EmployeeDTO edd = new MST_EmployeeDTO();
			try {
				//該当レコードを取得
				rs = st.executeQuery();
				rs.next();
				edd.setEmpID(rs.getInt(1));
				edd.setBranchID(rs.getInt(2));
				edd.setDepartmentID(rs.getInt(3));
				edd.setEmpNo(rs.getInt(4));
				edd.setFullName(rs.getString(5));
				edd.setKanaName(rs.getString(6));
				edd.setLogInID(rs.getString(7));
				edd.setPassWord(rs.getString(8));
				edd.setEnable(rs.getInt(9));
				edd.setEMail(rs.getString(10));
				edd.setUserRole(rs.getString(11));
				edd.setBossID(rs.getInt(14));
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new SQLException(e);
			} finally {
				if(rs != null) {
					rs.close();
				}
			}

			return edd;
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

	/**
	 * LogInIDに該当するレコードの件数検索処理
	 * @param loginid 検索するログインID
	 * @return 該当件数
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int LogInIDCountSearch(String loginid) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement st = null;

		try {
			//DBを検索するSQL文　該当件数を取得
			String sql = "Select Count(*) From Employee_t Where LogInID = ?";
			int count = 0;
			con = DBManager.getConn();
			st = con.prepareStatement(sql);
			st.setString(1, loginid);
			ResultSet rs = null;

			try {
				//該当件数を取得
				rs = st.executeQuery();
				rs.next();
				count = rs.getInt(1);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new SQLException(e);
			} finally {
				if(rs != null) {
					rs.close();
				}
			}

			return count;
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

	/**
	 * LogInIDに該当するレコードの検索処理
	 * @param loginid 検索するログインID
	 * @return 該当レコード
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public MST_EmployeeDTO LogInIDSearch(String loginid) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement st = null;

		try {
			//DBを検索するSQL文　該当レコードを取得
			String sql = "Select * From Employee_t Where LogInID = ?";
			con = DBManager.getConn();
			st = con.prepareStatement(sql);
			st.setString(1, loginid);
			ResultSet rs = null;
			MST_EmployeeDTO edd = new MST_EmployeeDTO();

			try {
				//該当レコードを取得
				rs = st.executeQuery();
				rs.next();
				edd.setEmpID(rs.getInt(1));
				edd.setBranchID(rs.getInt(2));
				edd.setDepartmentID(rs.getInt(3));
				edd.setEmpNo(rs.getInt(4));
				edd.setFullName(rs.getString(5));
				edd.setKanaName(rs.getString(6));
				edd.setLogInID(rs.getString(7));
				edd.setPassWord(rs.getString(8));
				edd.setEnable(rs.getInt(9));
				edd.setEMail(rs.getString(10));
				edd.setUserRole(rs.getString(11));
				edd.setBossID(rs.getInt(14));
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new SQLException(e);
			} finally {
				if(rs != null) {
					rs.close();
				}
			}

			return edd;
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

	/**
	 * ログインIDが一致するレコードを更新処理
	 * @param edd 更新情報
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void EmployeeUpdate(MST_EmployeeDTO edd) throws SQLException, ClassNotFoundException{
		Connection con = null;
		PreparedStatement st = null;

		try {
			//DBを更新するSQL文
			String sql = "Update Employee_t Set BranchID=?," +
											"DepartmentID=?," +
											"FullName=?," +
											"KanaName=?," +
											"PassWord=?," +
											"Enable=?," +
											"EMail=?," +
											"UserRole=?," +
											"PWUPDay=?," +
											"BossID=? " +
						"Where LogInID=?";
			con = DBManager.getConn();
			st = con.prepareStatement(sql);
			st.setInt(1, edd.getBranchID());
			st.setInt(2, edd.getDepartmentID());
			st.setString(3, edd.getFullName());
			st.setString(4, edd.getKanaName());
			st.setString(5, edd.getPassWord());
			st.setInt(6, edd.getEnable());
			st.setString(7, edd.getEMail());
			st.setString(8, edd.getUserRole());
			st.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
			st.setInt(10, edd.getBossID());
			st.setString(11, edd.getLogInID());
			//DBを更新
			st.executeUpdate();
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

	/**
	 * 新規レコードをを挿入処理
	 * @param edd 新規情報
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void EmployeeInsert(MST_EmployeeDTO edd) throws SQLException, ClassNotFoundException{
		Connection con = null;
		PreparedStatement st = null;

		try {
			//DBを更新するSQL文
			String sql = "Insert Into Employee_t (BranchID,DepartmentID,EmpNo,FullName,KanaName,LogInID,PassWord,Enable,EMail,UserRole,PWUPDay,BossID) " +
							"Values(?,?,?,?,?,?,?,?,?,?,?,?)";
			con = DBManager.getConn();
			st = con.prepareStatement(sql);
			st.setInt(1, edd.getBranchID());
			st.setInt(2, edd.getDepartmentID());
			st.setInt(3, edd.getEmpNo());
			st.setString(4, edd.getFullName());
			st.setString(5, edd.getKanaName());
			st.setString(6, edd.getLogInID());
			st.setString(7, edd.getPassWord());
			st.setInt(8, edd.getEnable());
			st.setString(9, edd.getEMail());
			st.setString(10, edd.getUserRole());
			st.setTimestamp(11, new Timestamp(System.currentTimeMillis()));
			st.setInt(12, edd.getBossID());
			//DBを更新
			st.executeUpdate();
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

	/**
	 * 名前から従業員IDを検索処理
	 * @param name 検索する名前
	 * @return 従業員ID
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int BossSearch(String name) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement st = null;

		try {
			//DBを検索するSQL文
			String sql = "Select EmpID From Employee_t Where FullName = ?";
			int empid = 0;
			con = DBManager.getConn();
			st = con.prepareStatement(sql);
			st.setString(1, name);
			ResultSet rs = null;

			try {
				//従業員IDを取得
				rs = st.executeQuery();
				rs.next();
				empid = rs.getInt(1);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new SQLException(e);
			} finally {
				if(rs != null) {
					rs.close();
				}
			}

			return empid;
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

	/**
	 * 有効な従業員を検索処理
	 * @param edd MST_Employee_tに対応したデータを保持
	 * @return 検索結果
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<MST_EmployeeDTO> EmployeeSearch(MST_EmployeeDTO edd) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement st = null;

		try {
			//DBを検索するSQL文　有効な従業員を取得
			String sql = "Select * From Employee_t";
			if(edd != null) {
			sql += " Where BranchID=? And " +
						"DepartmentID=? And " +
						"FullName=? And " +
						"KanaName=? And " +
						"LogInID=? And " +
						"PassWord=? And " +
						"Enable=1 And " +
						"EMail=? And " +
						"UserRole=? And " +
						"BossID=?";
			}
			con = DBManager.getConn();
			st = con.prepareStatement(sql);
			if(edd != null) {
				st.setInt(1, edd.getBranchID());
				st.setInt(2, edd.getDepartmentID());
				st.setString(3, edd.getFullName());
				st.setString(4, edd.getKanaName());
				st.setString(5, edd.getLogInID());
				st.setString(6, edd.getPassWord());
				st.setString(7, edd.getEMail());
				st.setString(8, edd.getUserRole());
				st.setInt(9, edd.getBossID());
			}
			ResultSet rs = null;
			List<MST_EmployeeDTO> resultlist = new ArrayList<>();

			try {
				//有効な従業員を取得
				rs = st.executeQuery();
				while(rs.next()){
					MST_EmployeeDTO resultset = new MST_EmployeeDTO();
					resultset.setEmpID(rs.getInt(1));
					resultset.setBranchID(rs.getInt(2));
					resultset.setDepartmentID(rs.getInt(3));
					resultset.setEmpNo(rs.getInt(4));
					resultset.setFullName(rs.getString(5));
					resultset.setKanaName(rs.getString(6));
					resultset.setLogInID(rs.getString(7));
					resultset.setPassWord(rs.getString(8));
					resultset.setEnable(rs.getInt(9));
					resultset.setEMail(rs.getString(10));
					resultset.setUserRole(rs.getString(11));
					resultset.setPWUPDay(rs.getTimestamp(13));
					resultset.setBossID(rs.getInt(14));
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
			con.close();
			st.close();
		    System.out.println("DBとの接続を解除しました");
		}
	}
}
