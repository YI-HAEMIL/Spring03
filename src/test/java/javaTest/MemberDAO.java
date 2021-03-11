package javaTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import vo.MemberVO;

@Repository
public class MemberDAO {
// ** member Table 의 CURUD
	
	private Connection cn = ex02_DBConnection.getConnection();
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	private String sql;
	
	// selectList
	public List<MemberVO> selectList() {
		sql="select * from member order by id";
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			st = cn.createStatement();
			rs=st.executeQuery(sql);
			if (rs.next()) {
				do {
					MemberVO vo = new MemberVO();
					vo.setId(rs.getString(1));
					vo.setPassword(rs.getString(2));
					vo.setName(rs.getString(3));
					vo.setLev(rs.getString(4));
					vo.setBirthd(rs.getString(5));
					vo.setPoint(rs.getInt(6));
					vo.setWeight(rs.getDouble(7));
					vo.setRid(rs.getString(8));
					vo.setUploadfile(rs.getString(9));
					list.add(vo);
				}while(rs.next());
			}else {
				System.out.println("** selectList : 출력자료가 1건도 없습니다 **");
				list=null;
			}
		} catch (Exception e) {
			System.out.println("** selectList Exception => "+e.toString());
			list=null;
		}
		return list;
	} //selectList
	
	// ** selctOne
	public MemberVO selectOne(MemberVO vo) {
		sql="select * from member where id=?";
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1, vo.getId());
			rs=pst.executeQuery();
			if (rs.next()) {
				vo.setId(rs.getString(1));
				vo.setPassword(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setLev(rs.getString(4));
				vo.setBirthd(rs.getString(5));
				vo.setPoint(rs.getInt(6));
				vo.setWeight(rs.getDouble(7));
				vo.setRid(rs.getString(8));
				vo.setUploadfile(rs.getString(9));
				return vo;
			}else {
				System.out.println("** selctOne NotFound **");
				return null;
			}
		} catch (Exception e) {
			System.out.println("** selctOne Exception => "+e.toString());
			return null;
		} //try
	} //selctOne
	
	// insert
	public int insert(MemberVO vo) {
		sql="insert into member values(?,?,?,?,?,?,?,?,?)";
		try {
			pst=cn.prepareStatement(sql);
	        pst.setString(1,vo.getId());
	        pst.setString(2,vo.getPassword());
	        pst.setString(3,vo.getName());
	        pst.setString(4,vo.getLev());
	        pst.setString(5,vo.getBirthd());
	        pst.setInt(6,vo.getPoint());
	        pst.setDouble(7,vo.getWeight());
	        pst.setString(8,vo.getRid());
	        pst.setString(9,vo.getUploadfile());
	        return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** insert Exception => "+e.toString());
			return 0;
		}
	} //insert
	
	// ** update
	public int update(MemberVO vo) {
		sql="UPDATE member set password=?, name=?, lev=?, birthd=?, "
			+ "point=?, weight=?, rid=?, uploadfile=? WHERE id=?";
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1,vo.getPassword());
	        pst.setString(2,vo.getName());
	        pst.setString(3,vo.getLev());
	        pst.setString(4,vo.getBirthd());
	        pst.setInt(5,vo.getPoint());
	        pst.setDouble(6,vo.getWeight());
	        pst.setString(7,vo.getRid());
	        pst.setString(8,vo.getUploadfile());
	        pst.setString(9,vo.getId());
	        return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** update Exception => "+e.toString());
			return 0;
		}
	} //update
	
	// ** delete
	public int delete(MemberVO vo) {
		sql="DELETE from member where id=?";
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1,vo.getId());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** delete Exception => "+e.toString());
			return 0;
		}
	} //delete
	
} //class
