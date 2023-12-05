import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class control{
	
	static database db;
	
	public static class ButtonListener implements ActionListener{
		public void clear() {
			GUI.getIdTF().setText("");
			GUI.getNameTF().setText("");
			GUI.getAgeTF().setText("");
			GUI.getAddTF().setText("");
			GUI.getGpaTF().setText("");
		}
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == GUI.getAddButton()) {
				
				String name = GUI.getNameTF().getText();
				String age = GUI.getAgeTF().getText();
				String address = GUI.getAddTF().getText();
				String gpa = GUI.getGpaTF().getText();
				
				new Student(name, Integer.parseInt(age),address, Double.parseDouble(gpa));
				
				try {
					database.Insert(Student.getName(), Integer.toString(Student.getAge()), Student.getAddress(), Double.toString(Student.getGpa()));
					Frame frame = new Frame("Success!");
					JOptionPane.showMessageDialog(frame, "Sucessfully Added!","Success",JOptionPane.PLAIN_MESSAGE);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				database.printTable();
				clear();
				
			} else if (e.getSource() == GUI.getEditButton()) {
				String name = GUI.getNameTF().getText();
				String age = GUI.getAgeTF().getText();
				String address = GUI.getAddTF().getText();
				String gpa = GUI.getGpaTF().getText();
				String id = GUI.getIdTF().getText();
				
				try {
					database.edit(name, age, address, gpa, id);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				database.printTable();
			
			} else if (e.getSource() == GUI.getDelButton()) {			
				String id_String = GUI.getIdTF().getText();
				try {
					database.DeleteData(id_String);
					Frame frame = new Frame("Success!");
					JOptionPane.showMessageDialog(frame, "Sucessfully Deleted!","Success",JOptionPane.PLAIN_MESSAGE);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				database.printTable();
				clear();
				
			} else if (e.getSource() == GUI.getClearButton()) {
				clear();
			} else if (e.getSource() == GUI.getSortName()) {
				try {
					database.Sort("name");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} else if (e.getSource() == GUI.getSortGPA()) {
				try {
					database.Sort("gpa");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			} 
		}
	}
	public static class TableListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			if (!e.getValueIsAdjusting()) {
				
                int selectedRow = GUI.getTable().getSelectedRow();
                int selectedColumn = GUI.getTable().getSelectedColumn();
                if (selectedRow != -1 && selectedColumn != -1) {
                    String value = (String) GUI.getTable().getValueAt(selectedRow, selectedColumn);
                    
                    String id = (String) GUI.getTable().getValueAt(selectedRow, 0);
                    GUI.getIdTF().setText(id);
                    
                    String name = (String) GUI.getTable().getValueAt(selectedRow, 1);
                    GUI.getNameTF().setText(name);
                    
                    String age = (String) GUI.getTable().getValueAt(selectedRow, 2);
                    GUI.getAgeTF().setText(age);
                    
                    String address = (String) GUI.getTable().getValueAt(selectedRow, 3);
                    GUI.getAddTF().setText(address);
                    
                    String gpa = (String) GUI.getTable().getValueAt(selectedRow, 4);
                    GUI.getGpaTF().setText(gpa);
                           	
                }
			}
		}
	}
	
	
	
	
}
