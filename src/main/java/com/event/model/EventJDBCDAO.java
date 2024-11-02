package com.event.model;

import java.util.*;
import java.sql.*;

public class EventJDBCDAO implements EventDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/testg4?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "zme874amy";

	private static final String INSERT_STMT = 
		"INSERT INTO event (business_id, name, start_date, end_date, delay_date, catalog_id, price, description, status, maximum, minimum, enrolled) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT id, business_id, name, start_date, end_date, delay_date, catalog_id, price, description, status, maximum, minimum, enrolled FROM event order by id";
	private static final String GET_ONE_STMT = 
		"SELECT id, business_id, name, start_date, end_date, delay_date, catalog_id, price, description, status, maximum, minimum, enrolled FROM event where id = ?";
	private static final String DELETE = 
		"DELETE FROM event where id = ?";
	private static final String UPDATE = 
		"UPDATE event set business_id=?, name=?, start_date=?, end_date=?, delay_date=?, catalog_id=?, price=?, description=?, status=?, maximum=?, minimum=?, enrolled=? where id = ?";

	@Override
	public void insert(EventVO eventVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, eventVO.getBusinessId());
			pstmt.setString(2, eventVO.getName());
			pstmt.setDate(3, eventVO.getStartDate());
			pstmt.setDate(4, eventVO.getEndDate());
			pstmt.setDate(5, eventVO.getDelayDate());
			pstmt.setInt(6, eventVO.getCatalogId());
			pstmt.setInt(7, eventVO.getPrice());
			pstmt.setString(8, eventVO.getDescription());
			pstmt.setInt(9, eventVO.getStatus());
			if(eventVO.getMaximum()!=null) {
				pstmt.setInt(10, eventVO.getMaximum());
			} else {
				pstmt.setNull(10, Types.INTEGER);
			}
			if(eventVO.getMinimum()!=null) {
				pstmt.setInt(11, eventVO.getMinimum());
			} else {
				pstmt.setNull(11, Types.INTEGER);
			}
			pstmt.setInt(12, 0);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(EventVO eventVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, eventVO.getBusinessId());
			pstmt.setString(2, eventVO.getName());
			pstmt.setDate(3, eventVO.getStartDate());
			pstmt.setDate(4, eventVO.getEndDate());
			pstmt.setDate(5, eventVO.getDelayDate());
			pstmt.setInt(6, eventVO.getCatalogId());
			pstmt.setInt(7, eventVO.getPrice());
			pstmt.setString(8, eventVO.getDescription());
			pstmt.setInt(9, eventVO.getStatus());
			pstmt.setInt(10, eventVO.getMaximum());
			pstmt.setInt(11, eventVO.getMinimum());
			pstmt.setInt(12, eventVO.getEnrolled());
			pstmt.setInt(13, eventVO.getId());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public EventVO findByPrimaryKey(Integer id) {

		EventVO eventVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				eventVO = new EventVO();
				eventVO.setId(rs.getInt("id"));
				eventVO.setBusinessId(rs.getInt("business_id"));
				eventVO.setName(rs.getString("name"));
				eventVO.setStartDate(rs.getDate("start_date"));
				eventVO.setEndDate(rs.getDate("end_date"));
				eventVO.setDelayDate(rs.getDate("delay_date"));
				eventVO.setCatalogId(rs.getInt("catalog_id"));
				eventVO.setPrice(rs.getInt("price"));
				eventVO.setDescription(rs.getString("description"));
				eventVO.setStatus(rs.getInt("status"));
				eventVO.setMaximum(rs.getInt("maximum"));
				eventVO.setMinimum(rs.getInt("minimum"));
				eventVO.setEnrolled(rs.getInt("enrolled"));
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return eventVO;
	}

	@Override
	public List<EventVO> getAll() {
		List<EventVO> list = new ArrayList<EventVO>();
		EventVO eventVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				eventVO = new EventVO();
				eventVO.setId(rs.getInt("id"));
				eventVO.setBusinessId(rs.getInt("business_id"));
				eventVO.setName(rs.getString("name"));
				eventVO.setStartDate(rs.getDate("start_date"));
				eventVO.setEndDate(rs.getDate("end_date"));
				eventVO.setDelayDate(rs.getDate("delay_date"));
				eventVO.setCatalogId(rs.getInt("catalog_id"));
				eventVO.setPrice(rs.getInt("price"));
				eventVO.setDescription(rs.getString("description"));
				eventVO.setStatus(rs.getInt("status"));
				eventVO.setMaximum(rs.getInt("maximum"));
				eventVO.setMinimum(rs.getInt("minimum"));
				eventVO.setEnrolled(rs.getInt("enrolled"));
				list.add(eventVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		EventJDBCDAO dao = new EventJDBCDAO();

		// 新增
//		EmpVO empVO1 = new EmpVO();
//		empVO1.setEname("吳永志1");
//		empVO1.setJob("MANAGER");
//		empVO1.setHiredate(java.sql.Date.valueOf("2005-01-01"));
//		empVO1.setSal(new Double(50000));
//		empVO1.setComm(new Double(500));
//		empVO1.setDeptno(10);
//		dao.insert(empVO1);

		// 修改
//		EmpVO empVO2 = new EmpVO();
//		empVO2.setEmpno(7001);
//		empVO2.setEname("吳永志2");
//		empVO2.setJob("MANAGER2");
//		empVO2.setHiredate(java.sql.Date.valueOf("2002-01-01"));
//		empVO2.setSal(new Double(20000));
//		empVO2.setComm(new Double(200));
//		empVO2.setDeptno(20);
//		dao.update(empVO2);

		// 刪除
//		dao.delete(7014);

		// 查詢
//		EmpVO empVO3 = dao.findByPrimaryKey(7001);
//		System.out.print(empVO3.getEmpno() + ",");
//		System.out.print(empVO3.getEname() + ",");
//		System.out.print(empVO3.getJob() + ",");
//		System.out.print(empVO3.getHiredate() + ",");
//		System.out.print(empVO3.getSal() + ",");
//		System.out.print(empVO3.getComm() + ",");
//		System.out.println(empVO3.getDeptno());
//		System.out.println("---------------------");

		// 查詢
		List<EventVO> list = dao.getAll();
		for (EventVO aEvent : list) {
			System.out.print(aEvent.getId() + ",");
			System.out.print(aEvent.getBusinessId() + ",");
			System.out.print(aEvent.getName() + ",");
			System.out.print(aEvent.getStartDate() + ",");
			System.out.print(aEvent.getEndDate() + ",");
			System.out.print(aEvent.getDelayDate() + ",");
			System.out.print(aEvent.getCatalogId() + ",");
			System.out.print(aEvent.getPrice() + ",");
			System.out.print(aEvent.getDescription() + ",");
			System.out.print(aEvent.getStatus() + ",");
			System.out.print(aEvent.getMaximum() + ",");
			System.out.print(aEvent.getMinimum() + ",");
			System.out.print(aEvent.getEnrolled());

			System.out.println();
		}
	}
}