  import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class CalcFrame extends JFrame implements ActionListener
{
 JButton bDig[];
 JButton bZro,bAdd,bSub,bMul,bDiv,bEql,bClr;
 JTextField tScr;
 int cur,prv;

 String lastop="",op="";

 CalcFrame()
 {
  super("Calculator");
  setSize(350,380);
  setLocation(400,200);

  CalcPanel cp = new CalcPanel(20,10,10,10);
  cp.setLayout(new BorderLayout(10,10));
  add(cp);

  tScr = new JTextField("0");
  tScr.setFont(new Font("lucida console",Font.BOLD,40));
  tScr.setBackground(Color.black);
  tScr.setForeground(Color.white);
  tScr.setEditable(false);
  tScr.setHorizontalAlignment(JTextField.RIGHT);

  bClr = new JButton("C");
  bClr.setFont(new Font("lucida console",Font.PLAIN,22));
  bClr.setBackground(new Color(200,70,70));
  bClr.setForeground(Color.white);

  bZro = new JButton("00");
  bZro.setFont(new Font("lucida console",Font.PLAIN,18));
  bZro.setBackground(new Color(60,60,60));
  bZro.setForeground(Color.white);

  bAdd = new JButton("+");
  bAdd.setFont(new Font("lucida console",Font.PLAIN,18));
  bAdd.setBackground(new Color(60,60,110));
  bAdd.setForeground(Color.white);

  bSub = new JButton("-");
  bSub.setFont(new Font("lucida console",Font.PLAIN,18));
  bSub.setBackground(new Color(60,60,110));
  bSub.setForeground(Color.white);

  bMul = new JButton("*");
  bMul.setFont(new Font("lucida console",Font.PLAIN,18));
  bMul.setBackground(new Color(60,60,110));
  bMul.setForeground(Color.white);

  bDiv = new JButton("/");
  bDiv.setFont(new Font("lucida console",Font.PLAIN,18));
  bDiv.setBackground(new Color(60,60,110));
  bDiv.setForeground(Color.white);

  bEql = new JButton("=");
  bEql.setFont(new Font("lucida console",Font.PLAIN,18));
  bEql.setBackground(new Color(60,60,110));
  bEql.setForeground(Color.white);

  bDig = new JButton[10];
  for(int i=0;i<10;i++)
  {
   bDig[i] = new JButton(i+"");
   bDig[i].setFont(new Font("lucida console",Font.PLAIN,18));
   bDig[i].setBackground(new Color(60,60,60));
   bDig[i].setForeground(Color.white);
   bDig[i].addActionListener(this);
  }
  
  bClr.addActionListener(this);  
  bZro.addActionListener(this);
  bAdd.addActionListener(this);
  bSub.addActionListener(this);
  bMul.addActionListener(this);
  bDiv.addActionListener(this);
  bEql.addActionListener(this);
   
  CalcPanel p1 = new CalcPanel(10,10,10,10);
  p1.setLayout(new BorderLayout(10,10));
  p1.add(tScr,BorderLayout.CENTER);

  CalcPanel p2 = new CalcPanel(10,10,10,10);
  p2.setLayout(new GridLayout(5,4,10,10));
 
  p2.add(new JLabel());
  p2.add(new JLabel());
  p2.add(new JLabel());
  p2.add(bClr); 

  p2.add(bDig[7]);
  p2.add(bDig[8]);
  p2.add(bDig[9]);
  p2.add(bAdd); 
  
  p2.add(bDig[4]);
  p2.add(bDig[5]);
  p2.add(bDig[6]);
  p2.add(bSub);

  p2.add(bDig[1]);
  p2.add(bDig[2]);
  p2.add(bDig[3]);
  p2.add(bMul);
 
  p2.add(bDig[0]);
  p2.add(bZro);
  p2.add(bEql);
  p2.add(bDiv);

  cp.add(p1,BorderLayout.NORTH);
  cp.add(p2,BorderLayout.CENTER);  
 }

 public void evaluate()
 {
  switch(op)
  {
   case "+":cur = prv+cur;
            break;

   case "-":cur = prv-cur;
            break;

   case "*":cur = prv*cur;
            break;

   case "/":cur = prv/cur;
            break;

   case "=":
            break;
  }
  prv = cur;
  cur = 0;
  tScr.setText(prv+"");
 }

 public void actionPerformed(ActionEvent ae)
 {
  if(ae.getSource()==bClr)
  {
   cur=prv=0;
   op = "";
   tScr.setText("0");
  }
  else
  if(ae.getSource()==bAdd)
  {
   if(op=="=")
   {
    cur=prv;
    op="";
   }
   evaluate();
   op="+";
  }
  else
  if(ae.getSource()==bSub)
  {
   if(op=="=")
   {
    cur=prv;
    op="";
   }
   evaluate();
   op="-";
  }
  else
  if(ae.getSource()==bMul)
  {
   if(op=="=")
   {
    cur=prv;
    op="";
   }
   evaluate();
   op="*";
  }
  else
  if(ae.getSource()==bDiv)
  {
   if(op=="=")
   {
    cur=prv;
    op="";
   }
   evaluate();
   op="/";
  }
  else
  if(ae.getSource()==bEql)
  {
   int tmp=cur;
   if(op=="=") op=lastop;

   evaluate();

   cur=tmp;
   if(op!="=") lastop=op;
   op="=";
  }
  else
  if(ae.getSource()==bZro)
  {
   if(op=="=") 
   {
    cur=0;
    op="";
   }
   cur = cur*100;
   tScr.setText(cur+"");
  }
  else
  {
   if(op=="=")
   {
    cur=0;
    op="";
   }

   int i;
   for(i=0;i<10&&ae.getSource()!=bDig[i];i++);
   
   cur = cur*10+i;
   tScr.setText(cur+"");
  }
 }
}

class CalcPanel extends JPanel
{
 int top,left,bottom,right;

 CalcPanel(int top,int left,int bottom,int right)
 {
  this.top = top;
  this.left = left;
  this.bottom = bottom;
  this.right = right;
 }

 public Insets getInsets()
 {
  return new Insets(top,left,bottom,right);
 }
 
 public void paintComponent(Graphics g)
 {
  super.paintComponent(g);
  setBackground(new Color(100,100,100));
 }
}

class Calc
{
 public static void main(String...arg)
 {
  CalcFrame cf = new CalcFrame();
  cf.setVisible(true);
  cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 }
}

