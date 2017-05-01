import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

public class GUI extends JFrame{
	
	private JTextField textResult;
	
	//Constructor
	public GUI() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //X button
		this.setSize(440,260); //Window size
		setResizable(false); //Not re-sizable
		setTitle("Image analysis"); //Title
		getContentPane().setLayout(null);
		
		setLables(); //Create lables
		//setCombos(); //Create combo-boxes
		setCompareBtn(); //Compare button 
		setCloseBtn(); //Close button
		setResetBtn(); //Create Reset button
		setTxtField(); //Create text field
	
		this.setVisible(true);
	}
	
	//Methods
	
	//Set labels
	private void setLables(){
		
		JLabel lblSelectImageOne = new JLabel("Select image:");
		lblSelectImageOne.setBounds(36, 26, 79, 14);
		getContentPane().add(lblSelectImageOne);
		
		JLabel lblSelectImageTwo = new JLabel("Select image:");
		lblSelectImageTwo.setBounds(265, 26, 79, 14);
		getContentPane().add(lblSelectImageTwo);
		
		JLabel lblVs = new JLabel("VS");
		lblVs.setBounds(208, 54, 21, 14);
		getContentPane().add(lblVs);
	}
	
	//Set combo-boxes
	public void setCombos(Object[] arrayList){
		
		JComboBox comboImageOne = new JComboBox();
		comboImageOne.setModel(new DefaultComboBoxModel(arrayList));
		comboImageOne.setBounds(36, 51, 135, 20);
		getContentPane().add(comboImageOne);
		
		JComboBox comboImageTwo = new JComboBox();
		comboImageTwo.setModel(new DefaultComboBoxModel(arrayList));
		comboImageTwo.setBounds(265, 51, 135, 20);
		getContentPane().add(comboImageTwo);
	}
	
	//Create Compare button
	private void setCompareBtn(){
		
		JButton btnCompare = new JButton("Compare");
		btnCompare.setBounds(169, 171, 89, 23);
		getContentPane().add(btnCompare);
	}
	
	//Create Close button
	private void setCloseBtn(){
		JButton btnClose = new JButton("Close");
		//Event handler
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
			}
		});
		btnClose.setBounds(306, 171, 89, 23);
		getContentPane().add(btnClose);
	}
	
	//Create Reset button
	private void setResetBtn(){
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(36, 171, 89, 23);
		getContentPane().add(btnReset);
	}
	
	//Create text field
	private void setTxtField(){
		
		textResult = new JTextField();
		textResult.setEditable(false);
		textResult.setBounds(36, 108, 359, 29);
		getContentPane().add(textResult);
		textResult.setColumns(10);
	}
	
	//End
}
