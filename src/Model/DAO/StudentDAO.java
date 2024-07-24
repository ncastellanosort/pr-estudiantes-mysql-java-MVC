/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model.DAO;

import Model.Student;
import java.util.List;

/**
 *
 * @author Nicolas
 */
public interface StudentDAO {
    List<Student> getStudentsDB();
    void addStudentDB(Student st);
    void deleteStudentDB(Student st);
    Student selectStudentdDB(int id_s);
    void editStudentDB(Student st, int id_s);
}
