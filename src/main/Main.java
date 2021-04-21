package main;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import dao.CafeDao;
import dao.ManagerDao;
import dto.CafeDto;
import dto.ManagerDto;
import util.Utils;

public class Main {
	public static void main(String[] args) throws Exception {
         CafeDao c = new CafeDao();
		ManagerDao m = new ManagerDao();
		Utils u = new Utils();
		Scanner sc = new Scanner(System.in);
		System.out.println("카페메뉴 관리 시스템");
		while (true) {
			System.out.println("<<로그인>>");
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			System.out.println("3. 종료");
			int menu = Integer.parseInt(sc.nextLine());
			if (menu == 1) {

				System.out.print("id: ");
				String id = sc.nextLine();

				System.out.print("pw: ");
				String pw = sc.nextLine();

				String encrypt_pw = u.getSHA512(pw);
         boolean result = m.login(new ManagerDto(id,encrypt_pw));
         
         if(result == false) {
        	 System.out.println("아이디 또는 비밀번호 확인");
        	 continue;
         } else {
         System.out.println("로그인 성공");
         System.out.println(id+" 님 안녕하세요");
         break;
         }
			} else if(menu==2) {
				System.out.print("회원 가입 할 아이디: ");
				String id = sc.nextLine();
				System.out.print("회원 가입 할 비빌번호: ");
				String pw= sc.nextLine();
				System.out.print("회원 가입 할 이름: ");
				String name = sc.nextLine();
				String encrypt_pw = u.getSHA512(pw);
				try {
				int result = m.signUp(id,encrypt_pw,name);
				if(result>0) {
					System.out.println("회원가입 성공");
				}
				} catch(Exception e) {
					e.printStackTrace();
					System.out.println("회원 가입 실패");
				}
			} else if(menu == 3) {
				System.out.println("종료합니다.");
				System.exit(0);
			} else {
				System.out.println("다시확인");
			}
		}
		
		while(true) {
			System.out.println("<<메뉴>>");
			System.out.println("1. 메뉴 등록");
			System.out.println("2. 메뉴 목록");
			System.out.println("3.메뉴 삭제 (id로)");
			System.out.println("4. 메뉴 수정");
			System.out.println("5. 종료");
			int menu = Integer.parseInt(sc.nextLine());
			if(menu==1) {
				System.out.print("이름: ");
				String name = sc.nextLine();
				System.out.print("가격: ");
				int price = Integer.parseInt(sc.nextLine());
				System.out.print("날짜(yyyy/mm/dd): ");
				String date = sc.nextLine();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				Date reg_date = new Date(sdf.parse(date).getTime());
				
				try {
				int result = c.insert(name,price,reg_date);
				if(result>0) {
					System.out.println("입력 성공");
				}
				} catch(Exception e) {
					e.printStackTrace();
					System.out.println("입력 실패");
				}
			} else if(menu == 2) {
				System.out.println("메뉴 목록");
				
				for(String x : c.showAll()) {
					System.out.println(x);
				}		
			} else if(menu == 3) {
				System.out.println("==메뉴 삭제==");
				System.out.print("삭제 할 id: ");
				int id = Integer.parseInt(sc.nextLine());
				
				try {
					int result = c.delete(id);
					if(result>0) {
						System.out.println("삭제 성공");
					} 
				}catch(Exception e) {
						e.printStackTrace();
						System.out.println("삭제 실패");
					}
				
			} else if(menu==4) {
				System.out.print("수정할 id: ");
				int id = Integer.parseInt(sc.nextLine());
				
				System.out.print("이름 변경: ");
				String name = sc.nextLine();
				System.out.print("가격 변경: ");
				int price = Integer.parseInt(sc.nextLine());
				System.out.print("날짜 변경: ");
				String date = sc.nextLine();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				Date reg_date =new Date(sdf.parse(date).getTime());
				
				
				try {
				int result = c.update (new CafeDto(id,name,price,reg_date));
				if(result>0) {
					System.out.println("변경 완료");
				}
				} catch(Exception e) {
					e.printStackTrace();
					System.out.println("변경 실패");
				}
				
			} else {
				System.out.println("잘못 입력 하셨습니다.");
				continue;
			}
		}
		
		
		
		
		
		
		
		
		
		
		
	}
}