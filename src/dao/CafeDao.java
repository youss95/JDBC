package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.CafeDto;
import dto.ManagerDto;
import util.Utils;

public class CafeDao {
Utils u = new Utils();
	
	public int insert(String name, int price, Date reg_date) throws SQLException, Exception {
		String sql ="insert into cafe values(cafe_seq.nextval,?,?,?)";
		try(Connection con = u.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				) {
			pstat.setString(1, name);
			pstat.setInt(2, price);
			pstat.setDate(3, reg_date);
			int result = pstat.executeUpdate();
			
			con.commit();
			con.close();
			return result;
		}
		
		
	}

	public ArrayList<String> showAll() throws Exception {
		ArrayList<String> list = new ArrayList<>();
		String a="";
		String sql="select * from cafe";
		try(
		Connection con = u.getConnection();
		PreparedStatement pstat = con.prepareStatement(sql);
		ResultSet rs = pstat.executeQuery();
		){
		while(rs.next()){
			int id=rs.getInt("id");
			String name = rs.getString("name");
			int price = rs.getInt("price");
			Date reg_date = rs.getDate("reg_date");
			
			a=String.format("%d %s %d %s",id,name,price,reg_date);
			list.add(a);
		}
		
		con.close();
		return list;
		
		}
		
	}

	public int delete(int id) throws Exception {
		String sql = "delete from cafe where id =?";
		try(
		Connection con = u.getConnection();
		PreparedStatement pstat = con.prepareStatement(sql);
				){
		pstat.setInt(1, id);
		
		int result = pstat.executeUpdate();
		
		con.commit();
		con.close();
		return result;
		
		}
		
	}

	public boolean isIdExit(int id) throws Exception {
	String sql = "select * from cafe where id=?";
	Connection con = u.getConnection();
	PreparedStatement pstat = con.prepareStatement(sql);
	
	ResultSet rs = pstat.executeQuery();
	
	boolean result = rs.next();
	con.close();
	return result;
	
		
	}

	public int update(CafeDto cdt) throws Exception {
	String sql ="update cafe set name=?,price=?,reg_date=? where id=?";
	try(
	Connection con = u.getConnection();
	PreparedStatement pstat = con.prepareStatement(sql);
	){
	pstat.setString(1, cdt.getName());
	pstat.setInt(2, cdt.getPrice());
	pstat.setDate(3, cdt.getReg_date());
	pstat.setInt(4, cdt.getId());
	
	int result = pstat.executeUpdate();
	
	con.commit();
	con.close();
	return result;
	}
		
	}
	
	
}
