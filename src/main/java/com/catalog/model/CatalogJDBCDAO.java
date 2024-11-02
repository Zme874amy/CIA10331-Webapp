package com.catalog.model;

import java.util.*;
import java.sql.*;


import com.event.model.EventVO;

public class CatalogJDBCDAO implements CatalogDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/testg4?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "zme874amy";

	private static final String INSERT_STMT = "INSERT INTO evt_catalog (name) VALUES (?)";
	private static final String GET_ALL_STMT = "SELECT id , name FROM evt_catalog";
	private static final String GET_ONE_STMT = "SELECT id , name FROM evt_catalog where id = ?";
	private static final String GET_Events_ById_STMT = "SELECT id, business_id, name, start_date, end_date, delay_date, catalog_id, price, description, status, maximum, minimum, enrolled FROM event where catalog_id = ? order by id";
	
	private static final String DELETE_EVENTs = "DELETE FROM event where catalog_id = ?";
	private static final String DELETE_CATALOG = "DELETE FROM evt_catalog where id = ?";	
	
	private static final String UPDATE = "UPDATE catalog set name=? where id = ?";

	@Override
	public void insert(CatalogVO catalogVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, catalogVO.getName());
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
	public void update(CatalogVO catalogVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, catalogVO.getName());
			pstmt.setInt(2, catalogVO.getId());

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
		int updateCount_EVENTs = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			// 先刪除活動
			pstmt = con.prepareStatement(DELETE_EVENTs);
			pstmt.setInt(1, id);
			updateCount_EVENTs = pstmt.executeUpdate();
			// 再刪除種類
			pstmt = con.prepareStatement(DELETE_CATALOG);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除活動種類" + id + "時,共有活動" + updateCount_EVENTs
					+ "筆同時被刪除");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public CatalogVO findByPrimaryKey(Integer id) {

		CatalogVO catalogVO = null;
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
				// deptVO 也稱為 Domain objects
				catalogVO = new CatalogVO();
				catalogVO.setId(rs.getInt("id"));
				catalogVO.setName(rs.getString("name"));
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
		return catalogVO;
	}

	@Override
	public List<CatalogVO> getAll() {
		List<CatalogVO> list = new ArrayList<CatalogVO>();
		CatalogVO catalogVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				catalogVO = new CatalogVO();
				catalogVO.setId(rs.getInt("id"));
				catalogVO.setName(rs.getString("name"));
				list.add(catalogVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

	@Override
	public Set<EventVO> getEventsById(Integer id) {
		Set<EventVO> set = new LinkedHashSet<EventVO>();
		EventVO eventVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Events_ById_STMT);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
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
				set.add(eventVO); // Store the row in the vector
			}
	
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return set;
	}

	public static void main(String[] args) {

		CatalogJDBCDAO dao = new CatalogJDBCDAO();

		// 新增
//		DeptVO deptVO1 = new DeptVO();
//		deptVO1.setDname("製造部");
//		deptVO1.setLoc("中國江西");
//		dao.insert(deptVO1);

		// 修改
//		DeptVO deptVO2 = new DeptVO();
//		deptVO2.setDeptno(10);
//		deptVO2.setDname("財務部2");
//		deptVO2.setLoc("臺灣台北2");
//		dao.update(deptVO2);

		// 刪除
//		dao.delete(30);

		// 查詢
		CatalogVO catalogVO3 = dao.findByPrimaryKey(10);
		System.out.print(catalogVO3.getId() + ",");
		System.out.print(catalogVO3.getName());
		System.out.println("---------------------");

		// 查詢部門
		List<CatalogVO> list = dao.getAll();
		for (CatalogVO aCatalog : list) {
			System.out.print(aCatalog.getId() + ",");
			System.out.print(aCatalog.getName());
			System.out.println();
		}
		
		// 查詢某部門的員工
		Set<EventVO> set = dao.getEventsById(2);
		for (EventVO aEvent : set) {
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