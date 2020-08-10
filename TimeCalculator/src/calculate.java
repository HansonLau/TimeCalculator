import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.*;



public class calculate extends JFrame{

	final private int WIDTH = 500;
	final private int HEIGHT = 400;
	private JButton backward = new JButton("Backward");
	private JButton forward = new JButton("Forward");
	
	ClickListener cl = new ClickListener();
	
	public calculate(){
	
		
		JPanel panel = new JPanel();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((dim.width/2-this.getSize().width/2)-WIDTH/2, 
				(dim.height/2-this.getSize().height/2)-HEIGHT/2);
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("TimeCalculator");
		panel.setLayout(null);
		
		
		backward.setBounds(20, 150, 200, 50);
		forward.setBounds(270, 150, 200, 50);
		
		panel.add(backward);
		panel.add(forward);
		backward.addActionListener(cl);
		forward.addActionListener(cl);
		
		this.add(panel);
		this.setVisible(true);	
		
	}
	
	
	private void findTime(String action) {
		
		int hours = Integer.parseInt(JOptionPane.showInputDialog("How many hours " + action +"?"));
		int minutes = Integer.parseInt(JOptionPane.showInputDialog("How many minutes " + action +"?"));
		
		
		if(action.equals("forward")) {
			int findHours = (LocalDateTime.now().getHour() + hours) % 12;
			int findMinutes = (LocalDateTime.now().getMinute() + minutes);
			
			while(findMinutes > 60) {
				findHours++;
				findMinutes -= 60;
			}
			findHours %= 12;
			findMinutes %= 60;
			display("will be", findHours, findMinutes);
		}
		else {
			int findHours = (LocalDateTime.now().getHour() - hours) % 12;
			int findMinutes = (LocalDateTime.now().getMinute() - minutes);
			
			while(true) {
				if(findMinutes < 0 && findMinutes < -60) {
					findHours--;
					findMinutes += 60;
				}
				else if(findMinutes<0){
					findHours--;
					break;
				}
				else {
					break;
				}
				
			}

			findMinutes = 60 - Math.abs(findMinutes);
			display("was", findHours, findMinutes);
		}
		
	}
	
	private void display(String message, int hour, int min) {
		
		if(min < 10)
			JOptionPane.showMessageDialog(null, "The time " + message + " " + hour + ":0" + min);
		else
			JOptionPane.showMessageDialog(null, "The time " + message + " " + hour + ":" + min);
		
	}
	
	private class ClickListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			if(e.getSource() == forward) {
				findTime("forward");
			}
			else if(e.getSource() == backward) {
				findTime("backward");
			}
		}
		
	}
	
	public static void main(String[] args) {
		new calculate();
	}
	
}
