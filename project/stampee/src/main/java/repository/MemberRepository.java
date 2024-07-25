package repository;

import static Template.ConnectionClose.*;
import static config.DBConnectionUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Member;
import dto.Authentication;

public class MemberRepository {
	private static final Logger log = LoggerFactory.getLogger(MemberRepository.class);

	public Member userSignUp(Member member) {
		// connection 영역
		Authentication auth = new Authentication(member);
		if (auth.isValid()) {
			Connection conn = getConnection();
			PreparedStatement pstmt = null;
			try {
				String sql =
					"insert into member(member_id, user_name, email, password, phone_number)" +
						"values(MEMBER_SEQ.NEXTVAL,?,?,?,?)";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, member.getPassword());
				pstmt.setString(2, member.getUserName());
				pstmt.setString(3, member.getEmail());
				pstmt.setString(4, member.getPhoneNumber());
				pstmt.executeUpdate();

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				close(conn, pstmt);
			}
			return member;
		}
		return member;
	}

}

