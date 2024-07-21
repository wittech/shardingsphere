package org.apache.shardingsphere.proxy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class Test {

    public static void main(String[] args) throws Exception {

        String url = "jdbc:postgresql://localhost:3307/demo_ds";
        // String url = "jdbc:postgresql://localhost:5432/demo_ds";

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "root");
        // props.setProperty("user", "postgres");
        // props.setProperty("password", "postgres");
        // props.setProperty("binaryTransferEnable", "16");
        try (Connection conn = DriverManager.getConnection(url, props)) {
            conn.setAutoCommit(true);
            // conn.unwrap(PGConnection.class).setPrepareThreshold(-1);
            try (PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO \"public\".\"user\" (\"id\",\"email\",\"deleted\",\"createdAt\") VALUES ('edbe0722f5bf40d7a2602cbf4ab5d943', 'Rowena.Wiza@yahoo.com', true, '2024-07-18') RETURNING \"public\".\"user\".\"id\", \"public\".\"user\".\"email\", \"public\".\"user\".\"deleted\", \"public\".\"user\".\"createdAt\"")) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    // 取出元素
                    String id = resultSet.getString(1);
                    String email = resultSet.getString(2);
                    boolean deleted = resultSet.getBoolean(3);
                    System.out.println(id + "-" + email + "-" + deleted);
                }
                statement.close();
                conn.close();
            }
        }
    }
}
