package service;

import static config.DBConnectionUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.transform.Result;

public class InviteService {
	// public boolean inviteFriend(String inviterEmail, String inviteeEmail){
	// 	if(!memberExists(inviterEmail) || memberExists(inviteeEmail)) {
	// 		return false;
	// 	}
	// }

	// 추천인 존재 확인
	private boolean memberExists(String email){
		String sql = "SELECT COUNT(*) FROM member WHERE email= ? " ;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getInt(1)>0;		// 결과가 0보다 크면 멤버 존재.
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	// 카페 사장님이 동의했다는 가정하에 친구들 초대하면 stamp도 주지만 같이 방문했을 때 신규 방문자가 확인되면 커피도 할인
	// 위의 내용을 활용해서 프로모션 진행도 가능
	private boolean giveStamp(String email){
		String getMemberId = "Select member_id FROM member WHERE email = ?" ;
		String updateStamp = "UPDATE stamp SET count = count + 1 WHERE member_id = ? AND   ";

		try {
			Connection conn = getConnection();
			PreparedStatement selectPstmt = conn.prepareStatement(getMemberId);
			PreparedStatement updatePstmt = conn.prepareStatement(updateStamp);


			selectPstmt.setString(1, email);
			ResultSet rs = selectPstmt.executeQuery();
			if (!rs.next()) {
				return false; // 멤버를 찾지 못함
			}
			int memberID = rs.getInt("member_id");



		} catch (SQLException e){
			e.printStackTrace();
		}

		return false;
	}

}
