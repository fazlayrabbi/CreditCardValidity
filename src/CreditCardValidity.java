/*******************************************************************************
Title: CreditCardValidity.java
Author: Fazlay Rabbi 
Created on: 01/19/2015
Description:This program checks if a credit card is valid or not and reports the
log into the file log.txt
Dependencies: One file "log.txt" is created if it does not exists. 
Modifications: Comments were added on 01/19/2015
*******************************************************************************/
import javax.swing.*;

//import java.awt.*;
import java.util.*;

import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.*;

public class CreditCardValidity extends JFrame{
	private JTextField number = new JTextField();// field to enter number
	private JTextField validity = new JTextField();// shows if the number is valid or not
	private JButton submit= new JButton("Submit");
	public static void main(String[] args){
		JFrame frame = new CreditCardValidity();
		frame.setTitle("Credit Card");
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 150);
		frame.setVisible(true);
	}
	public CreditCardValidity(){
		JPanel p1 = new JPanel(new GridLayout(2, 2));
		p1.add(new JLabel("Credit Card#"));
		p1.add(number);
		p1.add(new JLabel("Validity"));
		p1.add(validity);
		p1.setBorder(new TitledBorder("Enter a credit card number to check its validity"));
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p2.add(submit);
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.EAST);
		submit.addActionListener(new ButtonListener());
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String string= number.getText();
			boolean correct= validNum(string);
			if (correct)
				validity.setText("Valid!");		
			else
				validity.setText("Invalid!");
			
			try{
				PrintWriter output= new PrintWriter(new FileOutputStream(new File("log.txt"),true));
				Date date= new Date();
				if (correct){
					output.println("Date: "+ date.toString());//prints date
					output.println("Card# "+ string);
					output.println("Validity: Valid");
					output.println();
				}	
				else{
					output.println("Date: "+ date.toString());//prints date
					output.println("Card# "+ string);
					output.println("Validity: Invalid");
					output.println();
				}
				output.close();
			}
			catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}
	/**Returns true if credit card number entered is valid. Otherwise returns false. 
	 s is the number entered by the user. */
	public static boolean validNum(String s){
		if (s.length()<13 && s.length()>16)
			return false;
		/**Not a valid provider*/
		if(s.charAt(0)!='4' && s.charAt(0)!='5' && s.charAt(0)!='3' && s.charAt(0)!='6')
			return false;
		/**Not a valid provider*/
		else if(s.charAt(0)=='3' && s.charAt(1)!='7')
			return false;
		/** sum is used to sum up all single-digit numbers and  all
			digits in the odd places. temp stores temporary sum of  all 
			single-digit numbers, all digits in the odd places and also
			holds modular value of sum/ 10*/
		int sum, temp=0;
		//all single-digit numbers
		for (int i= s.length()-2; i>-1; i= i-2){
			if (s.charAt(i)=='1')
				temp= temp + 1*2;
			else if (s.charAt(i)=='2')
				temp= temp + 2*2;
			else if (s.charAt(i)=='3')
				temp= temp + 3*2;
			else if (s.charAt(i)=='4')
				temp= temp + 4*2;
			else if (s.charAt(i)=='5')
				temp= temp + ((5*2)%10)+1;
			else if (s.charAt(i)=='6')
				temp= temp + ((6*2)%10)+1;
			else if (s.charAt(i)=='7')
				temp= temp + ((7*2)%10)+1;
			else if (s.charAt(i)=='8')
				temp= temp + ((8*2)%10)+1;
			else if (s.charAt(i)=='9')
				temp= temp + ((9*2)%10)+1;
			else if (s.charAt(i)=='0')
				continue;
			else// character is not a digit
				return false;
			
		}
		sum= temp;
		temp= 0;// temp is reset
		//all digits in the odd places
		for (int i= s.length()-1; i>-1; i= i-2){
			if (s.charAt(i)=='1')
				temp= temp + 1;
			else if (s.charAt(i)=='2')
				temp= temp + 2;
			else if (s.charAt(i)=='3')
				temp= temp + 3;
			else if (s.charAt(i)=='4')
				temp= temp + 4;
			else if (s.charAt(i)=='5')
				temp= temp + 5;
			else if (s.charAt(i)=='6')
				temp= temp + 6;
			else if (s.charAt(i)=='7')
				temp= temp + 7;
			else if (s.charAt(i)=='8')
				temp= temp + 8;
			else if (s.charAt(i)=='9')
				temp= temp + 9;
			else if (s.charAt(i)=='0')
				continue;
			else// character is not a digit
				return false;
		}
		sum= sum + temp;
		temp= sum % 10;
		if (temp==0)
			return true;
		else
			return false;
	}
}