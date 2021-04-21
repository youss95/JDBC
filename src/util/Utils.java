package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;

import org.apache.commons.dbcp2.BasicDataSource;

public class Utils {

	//암호화된 패스워드
	
	private BasicDataSource bds = new BasicDataSource();

	public Utils() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

		bds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		bds.setUsername("kh");
		bds.setPassword("kh");
		bds.setInitialSize(10); // 울타리
	}

	public Connection getConnection() throws Exception {
		return bds.getConnection();
	}
	
	
	
	
		public  String getSHA512(String input){

			String toReturn = null;
			try {
			    MessageDigest digest = MessageDigest.getInstance("SHA-256");
			    digest.reset();
			    digest.update(input.getBytes("utf8"));
			    toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
			} catch (Exception e) {
			    e.printStackTrace();
			}
			
			return toReturn;
		    }

		
}
