package dto;

import java.sql.Date;

public class CafeDto {

	private int id;
	private String name;
	private int price;
	private Date reg_date;
	
	public CafeDto( String name, int price, Date reg_date) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.reg_date = reg_date;
	}

	public CafeDto(int id, String name, int price, Date reg_date) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.reg_date = reg_date;
	}
	
	public CafeDto(int id, String name, int price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	
	
	
	
	
	
	
}
