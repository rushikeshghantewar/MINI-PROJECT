import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Color;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.lang.Math;

public class ChatBot extends JFrame implements KeyListener{

	JPanel p=new JPanel();
	JButton b = new  JButton(5,20);

	JTextArea dialog=new JTextArea(20,80);
	JTextArea input=new JTextArea(5,80);
	JScrollPane scroll=new JScrollPane(
		dialog,
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
	);
	
	String[][] chatBot={
		//standard greetings
		{"hi","hello","Hello"},//0
		{"hi","hello","hey"},//1 //length=3
		//question greetings
		{"how are you","how r you","how r u","how are u"},//2
		{"good","doing well"},//3 //length=2
		//
		{"Help","help","what you can do"},//4
		{"How can I help you","what you want","anything I can do"},
		//yes
		{"yes","Yes!","YES"},//6
		{"Thank you","Sure","Ok!"},
		//
		{"Good Morning","gud mrng","gud morning","good morning"},//8
		{"Very Good Morning","Have Nice Morning","Great Morning"},
		//
		{"gud noon","gud afternoon","good nun","good noon","good afternoon"},//10
		{"good afternoon","Have Nice Day","Have a great day"},
		//default
		{"It is Hard","What is this?","pardon","Ask again",
		"(Bot is unavailable)"}//12
	};
	
	public static void main(String[] args){
		new ChatBot();
	}
	
	public ChatBot(){
		super("Console Chat");
		setSize(1000,600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		dialog.setEditable(false);
		input.addKeyListener(this);
	
		p.add(scroll);
		p.add(input);
		p.setBackground(new Color(255,200,0));
		add(p);
		
		setVisible(true);
	}
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			input.setEditable(false);
			//-----grab quote-----------
			String quote=input.getText();
			input.setText("");
			addText("-->You:\t"+quote);
			quote.trim();
			while(
				quote.charAt(quote.length()-1)=='!' ||
				quote.charAt(quote.length()-1)=='.' ||
				quote.charAt(quote.length()-1)=='?'
			){
				quote=quote.substring(0,quote.length()-1);
			}
			quote.trim();
			byte response=0;
			//-----check for matches----
			int j=0;//which group we're checking
			while(response==0){
				if(inArray(quote.toLowerCase(),chatBot[j*2])){
					response=2;
					int r=(int)Math.floor(Math.random()*chatBot[(j*2)+1].length);
					//System.out.println(chatBot[(j*2)+1].length);
					addText("\n-->ChatBot\t"+chatBot[(j*2)+1][r]);
					//int o=(j*2)+1;
					//System.out.println(o+" "+r);
				}
				j++;//1
				if(j*2==chatBot.length-1 && response==0){
					response=1;
				}
			}
			
			//-----default--------------
			if(response==1){
				int r=(int)Math.floor(Math.random()*chatBot[chatBot.length-1].length);
				addText("\n-->Chat Bot\t"+chatBot[chatBot.length-1][r]);
			}
			addText("\n");
		}
	}
	
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			input.setEditable(true);
		}
	}
	
	public void keyTyped(KeyEvent e){}
	
	public void addText(String str){
		dialog.setText(dialog.getText()+str);
	}
	
	public boolean inArray(String in,String[] str){
		boolean match=false;
		for(int i=0;i<str.length;i++){
			if(str[i].equals(in)){
				match=true;
			}
		}
		return match;
	}
}