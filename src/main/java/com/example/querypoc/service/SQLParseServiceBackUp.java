package com.example.querypoc.service;

import com.example.querypoc.dao.EmployeeGeoCodeDao;
import com.example.querypoc.models.EmployeeGeoCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.sql.*;
import java.util.Set;

@Service
public class SQLParseServiceBackUp {

    @Autowired
    EmployeeGeoCodeDao empDao;

    public List<Map<String, Object>> parseSqlQuery(String query, String destinedTableName) {
        destinedTableName ="employee_territory";
        List<Map<String, Object>> resultDataset = new ArrayList<>();

        List<EmployeeGeoCode>  employeeGeoCodeList = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/policy_database_2";
        String username = "root";
        String password = "Cincy@fall2021";
        try {
            // Establish the database connection (Update the connection details accordingly)
            Connection connection = DriverManager.getConnection(url, username, password);

            // Create a statement for the SQL query
            Statement statement = connection.createStatement();
            // Execute the SQL query and retrieve the result
            ResultSet resultSet = statement.executeQuery(query);

            // Process the result and convert it to a list of maps
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            Set<String> columnNamesList = new HashSet<>();
            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    columnNamesList.add(columnName);
                    Object value = resultSet.getObject(i);
                    row.put(columnName, value);
                }
                resultDataset.add(row);
            }


            // Drop the table if any exists
//            String dropQuery = "DROP TABLE "+destinedTableName;
//            statement.executeUpdate(dropQuery);

            String selectQueryFromDestinationTable = "SELECT * FROM "+destinedTableName;
            ResultSet selectResultSet = statement.executeQuery(selectQueryFromDestinationTable);
            if(selectResultSet.getMetaData() != null)
            {
                // Drop the table if any exists
                String dropQuery = "DROP TABLE "+destinedTableName;
                statement.executeUpdate(dropQuery);
            }

            Iterator<String> namesIterator = columnNamesList.iterator();

            // Create a new Table with destined table name
            StringBuilder sb = new StringBuilder();
            sb.append("Create Table "+destinedTableName + " ( ");
            sb.append(destinedTableName+"_id INT AUTO_INCREMENT PRIMARY KEY , ");
            while(namesIterator.hasNext()){
                sb.append(namesIterator.next()+" VARCHAR(200) NOT NULL,");
            }
            String createQuery = sb.substring(0, sb.length()-1)+");";
            System.out.println(createQuery);
            statement.executeUpdate(createQuery);





            //truncate the destinationtable before insertion
            //String truncateQuery = "TRUNCATE TABLE employee_territory";
            String truncateQuery = "TRUNCATE TABLE "+destinedTableName;
            statement.executeUpdate(truncateQuery);
            // Insert the data into destination table
            String insertQuery = generateInsertQuery(destinedTableName, resultDataset.get(0));
            // Prepare the insert statement
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            for (Map<String, Object> row : resultDataset) {
                int index = 1;
                for (String column : row.keySet()) {
                    preparedStatement.setObject(index++, row.get(column));
                }
                preparedStatement.addBatch();
            }

            // Execute the batch insert
            preparedStatement.executeBatch();
            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            // Handle exceptions appropriately
            Map<String, Object> mapObj = new HashMap<>();
            mapObj.put("Error", e.getMessage());
            resultDataset = new ArrayList<>();
            resultDataset.add(mapObj);
            e.printStackTrace();
        }
        return resultDataset;

    }

    private static String generateInsertQuery(String tableName, Map<String, Object> row) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("INSERT INTO ");
        queryBuilder.append(tableName);
        queryBuilder.append(" (");

        boolean isFirstColumn = true;
        for (String column : row.keySet()) {
            if (!isFirstColumn) {
                queryBuilder.append(", ");
            }
            queryBuilder.append(column);
            isFirstColumn = false;
        }

        queryBuilder.append(") VALUES (");
        for (int i = 0; i < row.size(); i++) {
            if (i > 0) {
                queryBuilder.append(", ");
            }
            queryBuilder.append("?");
        }
        queryBuilder.append(")");

        return queryBuilder.toString();
    }
}
