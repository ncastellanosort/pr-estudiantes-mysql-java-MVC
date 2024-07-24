/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nicolas
 */
public class StudentDAO {

    public List<Student> getStudentsDB() {

        List<Student> studentsList = new ArrayList();

        String sql = "SELECT * FROM students";

        try {
            Connection con = ConnectionDB.getConnectionDB();
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                Student st = new Student();
                st.setId(res.getInt("id"));
                st.setFirst_name(res.getString("first_name"));
                st.setLast_name(res.getString("last_name"));
                st.setAge(res.getInt("age"));
                st.setGpa(res.getDouble("gpa"));

                studentsList.add(st);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return studentsList;

    }

    public void addStudentDB(Student st) {
        String sql = "INSERT INTO students VALUES (?,?,?,?,?)";

        try {
            Connection con = ConnectionDB.getConnectionDB();
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, st.getId());
            query.setString(2, st.getFirst_name());
            query.setString(3, st.getLast_name());
            query.setInt(4, st.getAge());
            query.setDouble(5, st.getGpa());

            query.executeUpdate();

            con.close();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void deleteStudentDB(Student st) {
        String sql = "DELETE FROM students WHERE id = ?";

        try {
            Connection con = ConnectionDB.getConnectionDB();

            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, st.getId());

            query.executeUpdate();

            con.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Student selectStudentdDB(int id_s) {
        String sql = "SELECT * FROM students WHERE id = " + String.valueOf(id_s);

        Student new_stud = new Student();

        try {
            Connection con = ConnectionDB.getConnectionDB();

            Statement stat = con.createStatement();

            ResultSet res = stat.executeQuery(sql);

            if (res.next()) {

                new_stud.setId(res.getInt("id"));
                new_stud.setFirst_name(res.getString("first_name"));
                new_stud.setLast_name(res.getString("last_name"));
                new_stud.setAge(res.getInt("age"));
                new_stud.setGpa(res.getDouble("gpa"));

            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return new_stud;
    }

    public void editStudentDB(Student st, int id_s) {
        String sql = "UPDATE students SET id = ?, first_name = ?, last_name = ?, age = ?, gpa = ? WHERE id = " + String.valueOf(id_s);

        try {
            Connection con = ConnectionDB.getConnectionDB();

            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, st.getId());
            query.setString(2, st.getFirst_name());
            query.setString(3, st.getLast_name());
            query.setInt(4, st.getAge());
            query.setDouble(5, st.getGpa());

            query.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

}
