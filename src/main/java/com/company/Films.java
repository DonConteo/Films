package com.company;

import java.sql.*;

public class Films {

    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String password = "postgres";
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void main(String[] args) throws SQLException {
        SelectTable();
    }

    public static void DeleteRow(){

        String deleteRow = "DELETE FROM films.films WHERE id = 3";

        try {
            Connect();
            statement.executeUpdate(deleteRow);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            Close();
        }
    }

    public static void InsertTable() {

        String insUpdate = "INSERT INTO films.films (id, name, genre, year, watched) " +
                "VALUES ('2', 'Inception', 'SciFi, Action', '2010', '1'), ('3', 'Avengers', 'Action', '2012', '1'), ('4', 'Брат', 'Criminal, Drama', '1997', '1')";

        try {
            Connect();
            statement.executeUpdate(insUpdate);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            Close();
        }
    }

    public static void SelectTable() throws SQLException {

        String query = "SELECT * FROM films.films";

        try {
            Connect();
            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String genre = resultSet.getString(3);
                String year = resultSet.getString(4);
                boolean watched = resultSet.getBoolean(5);
                System.out.printf("id: %d, name: %s, genre: %s, year: %s, watched: %b \n", id, name, genre, year, watched);
            }
            Close();
        }
    }

    public static void Connect() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();
    }

    public static void Close() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}