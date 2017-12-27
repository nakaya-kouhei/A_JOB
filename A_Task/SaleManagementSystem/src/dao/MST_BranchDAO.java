package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import base.DBManager;
import salemanagement.MST_BranchDTO;

public class MST_BranchDAO {

	public static MST_BranchDAO getInstance() {
		return new MST_BranchDAO();
	}

	/**
	 * 支店マスタテーブルを全件検索処理
	 * @return resultlist 検索結果
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<MST_BranchDTO> BranchSearch() throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement st = null;

		try {
			//DBを検索するSQL文 全件検索
			String sql = "Select * From Branch_t";
			con = DBManager.getConn();
			st = con.prepareStatement(sql);
			ResultSet rs = null;
			List<MST_BranchDTO> resultlist = new ArrayList<>();

			try {
				rs = st.executeQuery();
				while(rs.next()) {
					MST_BranchDTO resutset = new MST_BranchDTO();
					resutset.setBranchID(rs.getInt(1));
					resutset.setBranchName(rs.getString(2));
					resutset.setBranchKbn(rs.getInt(3));
					resutset.setTEL(rs.getString(4));
					resutset.setFAX(rs.getString(5));
					resutset.setZipCD(rs.getString(6));
					resutset.setAdress1(rs.getString(7));
					resutset.setAdress2(rs.getString(8));
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
