package ProductCountByyType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductCountByType {
	public static void main(String[] args) {
		 String jdbcUrl = "jdbc:mysql://localhost:3306/ecommerce";
		 String username = "root";
		 String password = "root";
		 try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
		 // Fetch the count of products for Type A
		 String productType = "Type A";
		 int count = getCountOfProductsByType(connection, productType);
		 System.out.println("Number of products of Type " + productType + ": " + count);
		 } catch (SQLException e) {
		 e.printStackTrace();
		 }
		 }
		 private static int getCountOfProductsByType(Connection connection, String productType) throws SQLException {
		 int count = 0;
		 String query = "SELECT COUNT(*) AS count FROM products WHERE product_type = ?";
		 try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
		 preparedStatement.setString(1, productType);
		 try (ResultSet resultSet = preparedStatement.executeQuery()) {
		 if (resultSet.next()) {
		 count = resultSet.getInt("count");
		 }
		 }
		 }
		 return count;
		 }


}
