package jdbc_test;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * JDBC
 * == Java DataBase Connection
 */

public class MainClass01 {
	public static void main(String[] args) {
		DB db = new DB();
		
		Scanner scan = new Scanner(System.in);
		int num, age=0;
		String id=null,name=null;
		
		while(true) {
			System.out.println("======================");
			System.out.println("1. 모든 사용자 보기");
			System.out.println("2. 검색");
			System.out.println("3. 회원 가입");
			System.out.println("4. 회원 삭제");
			System.out.println("5. 프로그램 종료");
			System.out.println("======================");
			num=scan.nextInt();
			
			switch(num) {
			case 1 :
				ArrayList<MemberDTO> list = db.select_2();
				System.out.println("id\tname\tage");
				System.out.println("-----------------------");
				for(MemberDTO m : list) {
					System.out.print(m.getId()+"\t" );
					System.out.print(m.getName()+"\t" );
					System.out.println(m.getAge());
				}
				break;
			case 2 :
				System.out.println("검색할 ID 입력");
				id = scan.next();
				MemberDTO d = db.search(id);
				if(d==null) {
					System.out.println("-----------------------");
					System.out.println("아이디가 존재하지 않습니다.");
				}else {
					System.out.println("-----------------------");
					System.out.println("ID : " +d.getId());
					System.out.println("이름 : " +d.getName());
					System.out.println("나이 : " +d.getAge());
				}
				System.out.println("-----------------------");
				break;
			case 3 :
				System.out.println("아이디를 입력하세요.");
				id=scan.next();
				System.out.println("이름을 입력하세요.");
				name=scan.next();
				System.out.println("나이를 입력하세요.");
				age=scan.nextInt();
				
				MemberDTO dt = new MemberDTO();
				dt.setId(id);
				dt.setName(name);
				dt.setAge(age);
				
				int result = db.register(dt);
				if(result == 0) {
					System.out.println("동일한 아이디가 존재합니다.");
				}else {
					System.out.println("회원 가입을 축하합니다!");
				}
				System.out.println("-----------------------");
				break;
			case 4 :
				System.out.println("아이디를 입력하세요.");
				id=scan.next();
				int result1 = db.delete(id);
				if(result1 == 0) {
					System.out.println("존재하지 않는 회원입니다.");
				}else {
					System.out.println(id+"님을 삭제합니다.");
				}
				System.out.println("-----------------------");
				break;
			case 5 :
				System.out.println("종료합니다.");
				System.exit(0);
			}
		}

	}
}
