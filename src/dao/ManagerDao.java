package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

import dto.ManagerDto;
import util.Utils;

public class ManagerDao {

	Utils u = new Utils();
                
	public boolean login(ManagerDto md) throws Exception {
		String sql = "select * from cafe_manager where id=? and pw=?";
		try (Connection con = u.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, md.getId());
			pstat.setString(2, md.getPw());
			try (ResultSet rs = pstat.executeQuery();) {
				boolean result = rs.next();	
				con.close();
				return result;
			}
		}
	}

	public int signUp(String id, String encrypt_pw, String name) throws SQLException, Exception {
		String sql = "insert into cafe_manager values(?, ? ,?)";

		try (Connection con = u.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, id);
			pstat.setString(2, encrypt_pw);
			pstat.setString(3, name);
			int result = pstat.executeUpdate();
			con.commit();
			con.close();
			return result;

		}
	}

}