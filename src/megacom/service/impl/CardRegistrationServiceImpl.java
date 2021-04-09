package megacom.service.impl;

import megacom.models.Client;
import megacom.service.AddAdressService;
import megacom.service.AddClientService;
import megacom.service.CardRegistrationService;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CardRegistrationServiceImpl implements CardRegistrationService, AddClientService {

    Connection connection;
    Statement statement;


    public void ConnectionSQLite() {
        Connection connection = getConnection();
        System.out.println("Connection");
    }

    private Connection getConnection() {

        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(
                    "jdbc:sqlite:D:\\Azamat\\Javaitschool\\5month\\streamAPImethods\\sqlite3.db");
            //System.out.println("Connection");
            return connection;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void close() {
        try{
            connection.close();
            System.out.println("Close connection");
        }catch(Exception e)
        {
            System.out.print(e.getMessage());
        }
    }

    Scanner scanner = new Scanner(System.in);

    @Override
    public void addCard() {

    }


    // Добавление клиента в Базу Данных
    @Override
    public void addClient() {

        try {
            System.out.print("Введите имя: ");
            String fname = scanner.next();
            System.out.print("Введите фамилию: ");
            String sname = scanner.next();

            Client client = new Client(fname,sname);

            String query = "INSERT INTO clients(client_id, firstname, secondname)" +
                " VALUES ('"+client.getClientId()+"','"+client.getFirstname()+"', '"+client.getSecondname()+"')";

            statement = connection.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException throwables) {
            throw new RuntimeException("Ошибка при добавлении 'Клиента'");
        }

    }

    @Override
    public void selectClientFromFS(){
        System.out.print("Введите имя: ");
        String fname = scanner.next();
        System.out.print("Введите фамилию: ");
        String sname = scanner.next();

        int fio = selectClient(fname,sname);
        System.out.println(fio);
    }

    // Вывод клиентов из Базы данных по Имени и Фамилии
    public int selectClient(String firstname, String secondname) {
        try {

        }catch (SQLException throwables){
            throw new RuntimeException("Ошибка при запросе выводе 'Клиента'");
        }
    }
}
