package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import base.DBManager;
import salemanagement.MST_RoleDTO;

public class MST_RoleDAO {

	public static MST_RoleDAO getInsetance() {
		return new MST_RoleDAO();
	}

	/**
	 * 権限マスタテーブルを全件検索処理
	 * @return resultlist 検索結果
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<MST_RoleDTO> RoleSearch() throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement st = null;

		try {
			//DBを検索するSQL文 全件検索
			String sql = "Select * From Role_t";
			con = DBManager.getConn();
			st = con.prepareStatement(sql);
			ResultSet rs = null;
			List<MST_RoleDTO> resultlist = new ArrayList<>();

			try {
				rs = st.executeQuery();
				while(rs.next()) {
					MST_RoleDTO resutset = new MST_RoleDTO();
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
