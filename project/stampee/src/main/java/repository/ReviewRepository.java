package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import domain.Cafe;
import domain.Member;
import domain.Review;

public class ReviewRepository {

		public List<Review> findAllReviews(){
			Connection conn=null;
			conn=config.DBConnectionUtil.getConnection();

			String sql="select  "
						+ "from review r "
						+ "join member m "
						+ "on r.member_id=m.member_id "
						+ "join cafe c2 "
						+ "on r.cafe_id=c2.cafe_id ";

			List<Review> reviews=new ArrayList<Review>();

			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();

				while(rs.next()){
					Review review=new Review(rs.getLong("review_id"),
											rs.getInt("rating"),
											rs.getString("contents"),
											rs.getDate("createTime"));

					Member member=new Member(rs.getLong("member_id"),
											 rs.getString("phone_number"),
											 rs.getString("email"),
											 rs.getString("password"));
					review.getMember(member);

					Cafe cafe=new Cafe(rs.getLong("cafe_id"),
										rs.getString("name"),
										rs.getString("address"),
										rs.getString("password_1"),
										rs.getString("email_1"),
										rs.getString("contact")
										);
					review.getCafe(cafe);

					reviews.add(review);
				}

				ps.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			return reviews;
		}

		public int insertReview(Review review){
			return 0;
		}
		public int updateReview(Review review){

			return 0;
		}
		public void deleteReviewByReviewId(long id){
			Connection conn=null;
			conn=config.DBConnectionUtil.getConnection();
			String sql="delete from review where review_id=?";
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setLong(1,id);
				ps.executeUpdate();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		public List<Review> findReviewByPhoneNumber(String phone_number){
			Connection conn=null;
			conn=config.DBConnectionUtil.getConnection();
			List<Review> reviews=new ArrayList<>();
			String sql="select  *"
					+ "from review"
					+ "join member m "
					+ "on m.member_id=m.member_id "
					+ "join cafe c2 "
					+ "on c2.cafe_id=m.member_id "
					+ "where phone_number=?";

			try {
				PreparedStatement ps= conn.prepareStatement(sql);
				ps = conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					Review review=new Review(rs.getLong("review_id"),
						rs.getInt("rating"),
						rs.getString("contents"),
						rs.getDate("createTime"));

					Member member=new Member(rs.getLong("member_id"),
						rs.getString("phone_number"),
						rs.getString("email"),
						rs.getString("password"));
					review.getMember(member);

					Cafe cafe=new Cafe(rs.getLong("cafe_id"),
						rs.getString("name"),
						rs.getString("address"),
						rs.getString("password_1"),
						rs.getString("email_1"),
						rs.getString("contact")
					);
					review.getCafe(cafe);

					reviews.add(review);

				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			return reviews;
		}




		public int countAllReviews(){
			List<Review> reviews=findAllReviews();
			int cnt=reviews.size();
			return cnt;
		}



}
