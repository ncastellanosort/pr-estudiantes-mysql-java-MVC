/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Controller;

import Model.DAO.StudentDAOImpl;
import Model.Student;
import View.StudentView;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Nicolas
 */
public class StudentController {

    private StudentDAOImpl dao;
    private StudentView view;

    public StudentController(StudentDAOImpl dao, StudentView view) {
        this.dao = dao;
        this.view = view;
        start();

    }

    public void start() {
        view.setVisible(true);
        view.setResizable(false);
        view.setLocationRelativeTo(null);
        view.fillTable();

        view.btnAddStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    addStudent();
                    view.cleanEntries();
                    view.fillTable();
                } catch (HeadlessException d) {
                    JOptionPane.showMessageDialog(null, d);
                }

            }
        });

        view.btnDeleteStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteStudent();
                    view.fillTable();
                } catch (HeadlessException d) {
                    JOptionPane.showMessageDialog(null, d);
                }
            }
        });

        view.btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    selectStudent();
                } catch (Exception d) {
                    JOptionPane.showMessageDialog(null, d);
                }
            }
        });

        view.btnEditStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    editStudent();
                    JOptionPane.showMessageDialog(null, "Editado!");
                    view.fillTable();
                } catch (HeadlessException d) {
                    JOptionPane.showMessageDialog(null, d);
                }
            }
        });

    }

    public void addStudent() {
        Student st = new Student();

        st.setId(Integer.parseInt(view.txtfID.getText()));
        st.setFirst_name(view.txtfFirst_name.getText().toUpperCase());
        st.setLast_name(view.txtfLast_name.getText().toUpperCase());
        st.setAge(Integer.parseInt(view.txtfAge.getText()));
        st.setGpa(Double.parseDouble(view.txtfGPA.getText()));

        dao.addStudentDB(st);
    }

    public void deleteStudent() {
        Student st = new Student();

        String idStr = JOptionPane.showInputDialog(null, "ID:");

        st.setId(Integer.parseInt(idStr));

        dao.deleteStudentDB(st);

    }

    public void selectStudent() {

        String idStr = JOptionPane.showInputDialog(null, "ID:");

        try {
            int id = Integer.parseInt(idStr);

            Student st = dao.selectStudentdDB(id);

            view.txtfID.setText(idStr);
            view.txtfFirst_name.setText(st.getFirst_name());
            view.txtfLast_name.setText(st.getLast_name());
            view.txtfAge.setText(String.valueOf(st.getAge()));
            view.txtfGPA.setText(String.valueOf(st.getGpa()));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID no existente");
        }
    }

    public void editStudent() {
        String idStr = JOptionPane.showInputDialog(null, "ID:");

        try {
            int id = Integer.parseInt(idStr);

            String firstName = JOptionPane.showInputDialog(null, "Nuevo nombre:");
            String lastName = JOptionPane.showInputDialog(null, "Nuevo apellido:");
            String ageStr = JOptionPane.showInputDialog(null, "Nueva edad:");
            String gpaStr = JOptionPane.showInputDialog(null, "Nuevo GPA:");

            int age = Integer.parseInt(ageStr);
            double gpa = Double.parseDouble(gpaStr);

            Student st = new Student();
            st.setId(id);
            st.setFirst_name(firstName);
            st.setLast_name(lastName);
            st.setAge(age);
            st.setGpa(gpa);

            dao.editStudentDB(st, id);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID, edad o GPA no valido");
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el estudiante");
        }
    }

}
