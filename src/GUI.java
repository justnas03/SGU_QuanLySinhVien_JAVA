import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.*;

public class GUI extends JFrame {
	
	private static JLabel idLB, nameLB, ageLB, addLB, gpaLB;
	
	private static JTextField idTF, nameTF, ageTF, gpaTF, addTF;
	
	private static JButton addButton, editButton, delButton, clearButton, sortName, sortGPA;
	
	private static JPanel panel, inputPanel, tablePanel, sortButtonsPanel, buttonPanel, buttonPanel2;
	
	private static JTable table;
	
	private static database db;
	
	private static control control;
	
	private static DefaultTableModel model;
	
	public GUI() {
		setTitle("Student Information");
		setPreferredSize(new Dimension(700,310));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		panel = new JPanel();
		panel.setLayout(new GridLayout(0,2));
		
		try {	  
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch (Exception e) {
		    System.out.println("Look and Feel is not set!");
		}
		
		//Connect Database
		try {
			db = new database();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		buildInputPanel();
		
		add(panel);
		pack();
		setVisible(true);
	}
	 	
	private void buildInputPanel() {
		
		//setup input panel
		inputPanel = new JPanel(new GridLayout(0,2,20,10));
	
		//create components
			//labels
		idLB = new JLabel("   ID");
		nameLB = new JLabel("   Name");
		ageLB = new JLabel("   Age");
		addLB = new JLabel("   Address");
		gpaLB = new JLabel("   GPA");
			//Text Field
		idTF = new JTextField();
		idTF.setEditable(false);
		nameTF = new JTextField();
		ageTF = new JTextField();
		addTF = new JTextField();
		gpaTF = new JTextField();
			//Buttons
		addButton = new JButton("Add");
		editButton = new JButton("Edit");
		delButton = new JButton("Delete");
		clearButton = new JButton("Clear");
		sortName = new JButton("Sort By Name");
		sortGPA = new JButton("Sort by GPA");
		
			//table
		table = new JTable();
		
		//adding components to inputPanel
		inputPanel.add(idLB);
		inputPanel.add(idTF);
		inputPanel.add(nameLB);
		inputPanel.add(nameTF);
		inputPanel.add(ageLB);
		inputPanel.add(ageTF);
		inputPanel.add(addLB);
		inputPanel.add(addTF);
		inputPanel.add(gpaLB);
		inputPanel.add(gpaTF);
		
//		buttonPanel = new JPanel();
//		buttonPanel.add(addButton);
//		buttonPanel.add(editButton);
//		
//		buttonPanel2 = new JPanel();
//		buttonPanel2.add(delButton);
//		buttonPanel2.add(clearButton);
//		
//		inputPanel.add(buttonPanel);
//		inputPanel.add(buttonPanel2);
		
		inputPanel.add(addButton);
		inputPanel.add(editButton);
		inputPanel.add(delButton);
		inputPanel.add(clearButton);
		
		//Event Listener
		addButton.addActionListener(new control.ButtonListener());
		editButton.addActionListener(new control.ButtonListener());;
		delButton.addActionListener(new control.ButtonListener());
		clearButton.addActionListener(new control.ButtonListener());
		sortGPA.addActionListener(new control.ButtonListener());
		sortName.addActionListener(new control.ButtonListener());
		
		//setting JTable
		model = new DefaultTableModel();
		table = new JTable(model);
		JScrollPane TBscrollpane = new JScrollPane(table);
		
        // Add columns to the table
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Age");
        model.addColumn("Address");
        model.addColumn("GPA");
   
        //Getting Data from database and display it to table
		database.displayTableData();

        //setting tablePanel
        tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(TBscrollpane);
        
        sortButtonsPanel = new JPanel(new FlowLayout());
        sortButtonsPanel.add(sortName);
        sortButtonsPanel.add(sortGPA);
        
        tablePanel.add(sortButtonsPanel, BorderLayout.SOUTH);
        table.getSelectionModel().addListSelectionListener(new control.TableListener());
        
		//adding inputPanel to panel
		panel.add(inputPanel);
		panel.add(tablePanel);
	}
	
	public static JLabel getIdLB() {
		return idLB;
	}

	public void setIdLB(JLabel idLB) {
		this.idLB = idLB;
	}

	public static JLabel getNameLB() {
		return nameLB;
	}

	public void setNameLB(JLabel nameLB) {
		this.nameLB = nameLB;
	}

	public static JLabel getAgeLB() {
		return ageLB;
	}

	public void setAgeLB(JLabel ageLB) {
		this.ageLB = ageLB;
	}

	public static JLabel getAddLB() {
		return addLB;
	}

	public void setAddLB(JLabel addLB) {
		this.addLB = addLB;
	}

	public static JLabel getGpaLB() {
		return gpaLB;
	}

	public void setGpaLB(JLabel gpaLB) {
		this.gpaLB = gpaLB;
	}

	public static JTextField getIdTF() {
		return idTF;
	}

	public void setIdTF(JTextField idTF) {
		this.idTF = idTF;
	}

	public static JTextField getNameTF() {
		return nameTF;
	}

	public void setNameTF(JTextField nameTF) {
		this.nameTF = nameTF;
	}

	public static JTextField getAgeTF() {
		return ageTF;
	}

	public void setAgeTF(JTextField ageTF) {
		this.ageTF = ageTF;
	}

	public static JTextField getGpaTF() {
		return gpaTF;
	}

	public void setGpaTF(JTextField gpaTF) {
		this.gpaTF = gpaTF;
	}

	public static JTextField getAddTF() {
		return addTF;
	}

	public void setAddTF(JTextField addTF) {
		this.addTF = addTF;
	}

	public static JButton getSortName() {
		return sortName;
	}

	public void setSortName(JButton sortName) {
		this.sortName = sortName;
	}

	public static JButton getSortGPA() {
		return sortGPA;
	}

	public void setSortGPA(JButton sortGPA) {
		this.sortGPA = sortGPA;
	}

	public static JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public static JPanel getInputPanel() {
		return inputPanel;
	}

	public void setInputPanel(JPanel inputPanel) {
		this.inputPanel = inputPanel;
	}

	public static JPanel getTablePanel() {
		return tablePanel;
	}

	public void setTablePanel(JPanel tablePanel) {
		this.tablePanel = tablePanel;
	}

	public static JPanel getSortButtonsPanel() {
		return sortButtonsPanel;
	}

	public void setSortButtonsPanel(JPanel sortButtonsPanel) {
		this.sortButtonsPanel = sortButtonsPanel;
	}


	public static control getControl() {
		return control;
	}

	public void setControl(control control) {
		this.control = control;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public static JButton getAddButton() {
		return addButton;
	}

	public void setAddButton(JButton addButton) {
		this.addButton = addButton;
	}

	public static JButton getEditButton() {
		return editButton;
	}

	public void setEditButton(JButton editButton) {
		this.editButton = editButton;
	}

	public static JButton getDelButton() {
		return delButton;
	}

	public void setDelButton(JButton delButton) {
		this.delButton = delButton;
	}

	public static JButton getClearButton() {
		return clearButton;
	}

	public void setClearButton(JButton clearButton) {
		this.clearButton = clearButton;
	}
	public static JTable getTable() {
		return table;
	}

	public static DefaultTableModel getModel() {
		return model;
	}

	public static void setModel(DefaultTableModel model) {
		GUI.model = model;
	}

	public static database getDb() {
		return db;
	}

	public static void setDb(database db) {
		GUI.db = db;
	}
	
	
	
}
