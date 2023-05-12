package jdbc_test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import common.DBConnection;

public class DB {
	
		Connection con; // 연결객체
		PreparedStatement ps; // DB 명령어 전송 객체
		ResultSet rs; // 명령어 전송 후 결과를 얻어오는 객체
		
		public DB() {
			try {
				con = DBConnection.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*
		public DB() {
			// 드라이브 등록
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("드라이브 로드 성공");
				// 1.  드라이브 로드 => 오라클 관련 기능 사용 가능
				String id="java", pw="1234";
				String url = "jdbc:oracle:thin:@localhost:1521/xe";
							// 여기까지는 동일한 코드: 여기부터는 PC따라 
				con = DriverManager.getConnection(url,id,pw);
				System.out.println("연결 성공");
				// 2. 오라클에 연결
				// ; 데이터 베이스 접근을 위해 id,pwd,호스트이름,포트 입력 필요
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		*/
		
		public void select() {
			// 명령어 입력 및 입력 결과 얻어오기
			String sql ="select * from newst";
			try {
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				// executeQuery ; select 사용문
				while(rs.next()) {
					//System.out.println(rs.next());
					// next를 만나면 다음 위치로 이동 => 있으면 true, 아니면 false
					System.out.println(rs.getString("id"));
					System.out.println(rs.getString("name"));
					System.out.println(rs.getInt("age"));
					// get으로 데이터 가져오기
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public ArrayList<MemberDTO> select_2() {
			String sql ="select * from newst";
			ArrayList<MemberDTO> list = new ArrayList<>();
			try {
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()) {
					MemberDTO dto = new MemberDTO();
					
					dto.setId(rs.getString("id"));
					dto.setName(rs.getString("name"));
					dto.setAge(rs.getInt("age"));
					
					list.add(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		
		public MemberDTO search(String id) {
			String sql = "select * from newst where id ='"+id +"'";
			MemberDTO dto = null;
			try {
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				if(rs.next()) {
					dto = new MemberDTO();
					dto.setId(rs.getString("id"));
					dto.setName(rs.getString("name"));
					dto.setAge(rs.getInt("age"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dto;
		}

		public int register(MemberDTO dt) {
			String sql="insert into newst(id, name, age) values (?,?,?)";
			int result = 0;
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, dt.getId());
				ps.setString(2, dt.getName());
				ps.setInt(3, dt.getAge());
				result = ps.executeUpdate();
				// rs = ps.executeQuery();
				/*
				 *select ; executeQuery() 사용
				 * 나머지 ; update 사용
				 */
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		public int delete(String id) {
			String sql="delete from newst where id=?";		
			int result = 0;
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1,id);
				result = ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		
	}

