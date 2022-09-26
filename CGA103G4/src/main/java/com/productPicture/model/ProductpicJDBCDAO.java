package com.productPicture.model;
import static com.util.Common.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ProductpicJDBCDAO implements ProductpicDAO_interface {
		
	private static final String INSERT_PICTURE = 
		"Insert into productpic (pdpic,pdid) value (?,(SELECT pdid from product order by pdid desc limit 1))";
	private static final String INSERT_PIC_TO＿EXISTED_PRODUCT =
		"Insert into productpic (pdid, pdpic)value(?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT pdPicid, pdid, pdPic from Productpic order by pdPicid";
	private static final String GET_ONE_STMT = 
		"SELECT pdPicid, pdid, pdPic from productpic where pdPicid = ?";
	private static final String UPDATE = 
		"UPDATE Productpic SET pdPic = ?, pdid = ? WHERE pdPicid = ?";
	private static final String DELETE = 
		"DELETE FROM pdPic where pdPicid = ?";
    private static final String GET_PICTURES_BY_PDID =
	    	"SELECT pdPicid, pdid, pdPic from productpic where pdid = ?";

	
	
	@Override
	public void insert(ProductpicVO productpicVO) {
		
		try {

			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(INSERT_PICTURE);
			
			pstmt.setBytes(1, productpicVO.getPdPic());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void existedInsert(ProductpicVO productpicVO) {
		
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(INSERT_PIC_TO＿EXISTED_PRODUCT);
			
			pstmt.setInt(1, productpicVO.getPdid());
			pstmt.setBytes(2, productpicVO.getPdPic());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(ProductpicVO productpicVO) {

		try {
		
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(UPDATE);	
			
		    pstmt.setBytes(1, productpicVO.getPdPic());
		    pstmt.setInt(2, productpicVO.getPdid());
		    pstmt.setInt(3, productpicVO.getPdPicid());
			pstmt.executeUpdate();
			int rowCount = pstmt.executeUpdate();
			// Handle any driver errors
			System.out.println(rowCount + " row(s) updated!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void delete(Integer pdPicid) {
		
 
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, pdPicid);

			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	@Override
	public ProductpicVO findByPrimaryKey(Integer pdPicid) {
		
		ProductpicVO productpicVO = null;

		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT);
		){	
			pstmt.setInt(1, pdPicid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
			}
				// empVo �]�٬� Domain objects
			productpicVO = new ProductpicVO();
			productpicVO.setPdPicid(rs.getInt("pdPicid"));
			productpicVO.setPdid(rs.getInt("pdid"));
			productpicVO.setPdPic(rs.getBytes("pdPic"));
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productpicVO;
	}

	@Override
	public List<ProductpicVO> getAll() {
		List<ProductpicVO> list = new ArrayList<ProductpicVO>();
		
		ProductpicVO productpicVO = null;

		try (	
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);
			){
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				
				productpicVO = new ProductpicVO();
				productpicVO.setPdPicid(rs.getInt("pdpicid"));
				productpicVO.setPdid(rs.getInt("pdid"));
				productpicVO.setPdPic(rs.getBytes("pdPic"));
				list.add(productpicVO); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<ProductpicVO> getOneProductPics(Integer pdid) {
	
		List<ProductpicVO> list = new ArrayList<>();
		ProductpicVO productpicVO = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		
	try {
		
		con = DriverManager.getConnection(URL, USER, PASSWORD); 
			pstmt = con.prepareStatement(GET_PICTURES_BY_PDID, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			pstmt.setInt(1, pdid);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				
				productpicVO = new ProductpicVO();
				productpicVO.setPdPicid(rs.getInt("pdPicid"));

				list.add(productpicVO);
			}

			// Handle any driver errors
		
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
			
		}
		return list;
	}
	
	
	
	
	
	
	
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	
	public static void main(String[] args) {

		ProductpicJDBCDAO dao = new ProductpicJDBCDAO();

//		 新增
		ProductpicVO productpicVO1 = new ProductpicVO();
		byte[] pic;
		try {
			productpicVO1.setPdid(4004);
			pic = getPictureByteArray("image02.png");
			productpicVO1.setPdPic(pic);
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		dao.existedInsert(productpicVO1);
		System.out.println("succeed to insert");
		
//		 修改
//		ProductpicVO productpicVO2 = new ProductpicVO();
		
//		byte[] pic2;
//		try {
//			pic2 = getPictureByteArray("image03.jpg");
//			productpicVO2.setPdPic(pic2);
//			System.out.println("succeed to update");
//		} catch (IOException e) {
//			System.out.println(e);
//			e.printStackTrace();
//		}
//		productpicVO2.setPdid(3);
//		productpicVO2.setPdPicid(31);
//		
//		dao.update(productpicVO2);
//		
//		ProductpicVO productpicVO3 = dao.findByPrimaryKey(31);
//		System.out.print(productpicVO3.getPdPicid() + ",");
//		System.out.print(productpicVO3.getPdid() + ",");
//		System.out.print(productpicVO3.getPdPic() + ",");
//		
//		
//		List<ProductpicVO> list = dao.getAll();
//		for (ProductpicVO aproductpicVO : list) {
//			System.out.print(aproductpicVO.getPdPicid() + ",");
//			System.out.print(aproductpicVO.getPdid() + ",");
//			System.out.print(aproductpicVO.getPdPic() + ",");
//		


	}
	@Override
	public ProductpicVO GetOnePicBypdPicid(Integer pdPicid) {
		// TODO Auto-generated method stub
		return null;
	}
}

	
	

