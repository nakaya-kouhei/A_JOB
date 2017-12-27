package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

     /**
     * DBと接続する
     *
     * @return DBコネクション
     * @throws ClassNotFoundException
     * @throws SQLException
     */
     public static Connection getConn() throws ClassNotFoundException, SQLException {
         // JDBCドライバクラスをJVMに登録
         Class.forName("oracle.jdbc.driver.OracleDriver");
         // DBへ接続：引数の指定(DBの場所, ユーザ名, パスワード)
         // パスワードは伏字
         Connection conn = DriverManager.getConnection(
                 "jdbc:oracle:thin:@localhost:1521:orcl",
                 "user", "****");
         System.out.println("DBに接続しました");
         return conn;
     }
}
