package workshop.broker.ui;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import workshop.broker.dao.Database;
import workshop.broker.exception.DuplicateIDException;
import workshop.broker.exception.InvalidTransactionException;
import workshop.broker.exception.RecordNotFoundException;
import workshop.broker.vo.CustomerRec;
import workshop.broker.vo.SharesRec;
import workshop.broker.vo.StockRec;

/**
 <PRE>
 * Database Ŭ�������� ������ ����� GUI�� �ѷ��ִ� ����� ����ϴ� class.

 * <B>-- �ʱ� ȭ�� ���� --</B>
 
 * 1)event ó���� ������ GUI setting : addListener() method call
 * 2)customer list�� ������ : showList(CustomerRec,List) method call
 * 3)stock list�� ������ : showList(StockRec,List) method call
 * 4)< button��  �ʱ� Ȱ��ȭ ���� >
 *   add,update,delete : Ȱ��ȭ �Ǿ�� ��
 *   apply,cancel,buy,sell : ��Ȱ��ȭ�Ǿ�� ��
 * 5)< textfield�� textarea�� �ʱ� �������� >
 *   ��� textfield�� textarea�� �����������̾���� 

 * <B>-- �����Ǿ�� �� ��� --</B>
 * 1)customer list �׸� �ϳ��� click�ϸ� port list�� �����ֽ��� ��Ÿ����
 *   name,ssn,addr tf�� �ش��׸� ������ �����ٰ�
 * 2)stock list �׸� �ϳ��� click�ϸ� buy,stock,price tf�� �ش������� 
 *   �����ְ� �ش� �ֽ��� ���(buy)�־�� ��
 * 3)port list �׸� �ϳ��� click �ϸ� buy,sell tf�� �ش������� �����ְ�
 *   �ֽ��� �Ȱų�(sell) ���(buy)�־�� ��
 * 4)add button : ���ο� ���� customer table�� �Է� 
 *                - button(add,delete,update,buy,sell)�� ��Ȱ��ȭ
 *                - button(apply,cancel)�� Ȱ��ȭ
 *                - textfield(name,ssn,addr)�� ��������
 * 5)delete button : �ش� ssn���� customer table���� ����
 *                   - dialog â���� confirm������
 * 6)update button : Ư�� �������� ������
					 - button(add,delete,update,buy,sell)�� ��Ȱ��ȭ
 *                   - button(apply,cancel)�� Ȱ��ȭ
 *                   - textfield(name,ssn,addr)�� ��������
 * 7)buy,sell button : �ֽ��� ��� ��
 * 8)apply button : dialog â���� ���� confirm �ް���
 * 9)stock list�� �뷫 15�ʸ��� DB Stock table���� ������ ���ο� ������ ���ŵǾ����
 * ActionListener �������̽� : actionPerformed(ActionEvent e) method�����ؾ���
 * ItemListener �������̽� : itemStateChanged(ItemEvent e) method�����ؾ���
 * TextListener �������̽� : textValueChanged(TextEvent e) method�����ؾ���
 </PRE>
 */

public class Broker implements ActionListener, ItemListener, TextListener, Runnable {

	Database 		db;
	
	Frame frame;
	Dialog		dialog;
	Panel pc = new Panel();
	Panel pe = new Panel();
	Panel pe_1 = new Panel();
	Panel pe_2 = new Panel();
	Panel pe_3 = new Panel();

	Panel p1 = new Panel();
	Panel p2 = new Panel();
	Panel p3 = new Panel();
	
	Panel p1_1 = new Panel();
	Panel p1_2 = new Panel();
	
	Panel p2_1 = new Panel();
	Panel p2_2 = new Panel();
	Panel p2_3 = new Panel();
	
	Panel p2_2_1 = new Panel();
	Panel p2_2_2 = new Panel();
	Panel p2_3_1 = new Panel();
	Panel p2_3_2 = new Panel();

	Label cusL = new Label(" Customer Information ");
	Label stockiL = new Label(" Stock Information ");

	Label nameL = new Label("NAME");
	Label ssnL = new Label("SSN");
	Label addrL = new Label("ADDRESS");
	Label stockpL = new Label("Stock Portfolio");
	Label allcusL = new Label("All Customers");
	Label allstockL=new Label("Available Stocks");
	Label stocknameL=new Label("Stock");
	Label stockpriceL=new Label("Current Price");

	TextField nameTf=new TextField();
	TextField ssnTf=new TextField();
	TextField symbolTf=new TextField(15);
	TextField quanTf=new TextField(15);	
	TextField stockTf=new TextField(15);
	TextField priceTf=new TextField(15);

	TextArea addrTa=new TextArea();

	List custList=new List(15,false);
	List stockList=new List();
	List portList=new List(15,false);
	
	Button addB=new Button("ADD");
	Button deleteB=new Button("DELETE");
	Button updateB=new Button("UPDATE");
	Button viewB=new Button("VIEW");
	Button applyB=new Button("apply");
	Button cancelB=new Button("CANCEL");
	Button buyB=new Button("BUY");
	Button sellB=new Button("SELL");
	Button stockB=new Button("Get Current Stock Price");

	int flag=0;   //add--1,update--2,cancel--0

	/**
	 * database ��ü ����<BR>
	 * init() call
	 */
	
	public Broker() {
	    try	{	    
	     	db =  new Database("127.0.0.1");
  	    }catch(SQLException e)	{
			e.printStackTrace();
		}
	
		init();
	}

	public void init() 
	{	
		frame = new Frame("ABC Stock");
		//annoymous Inner class ����(type4)
		frame.addWindowListener(
		new WindowAdapter()
		{	public void windowClosing(WindowEvent we)
			{	
				frame.dispose();
				db.close();
				System.exit(0);
			}
		}
		);
		
		pc.setBackground(Color.orange);
		pe.setBackground(Color.orange);
		pe_1.setBackground(Color.orange);
		pe_2.setBackground(Color.orange);
		pe_3.setBackground(Color.orange);
		p1.setBackground(Color.orange);
		p2.setBackground(Color.orange);
		p3.setBackground(Color.orange);
		p1_1.setBackground(Color.orange);
		p1_2.setBackground(Color.orange);
		p2_1.setBackground(Color.orange);
		p2_2.setBackground(Color.orange);
		p2_3.setBackground(Color.orange);
		custList.setBackground(Color.orange);
		stockList.setBackground(Color.gray);
		portList.setBackground(Color.orange);
		
		p1.setLayout(new GridLayout(2,1));

		p1_1.add(cusL);
		p1_2.add(addB);
		p1_2.add(deleteB);
		p1_2.add(updateB);
		p1_2.add(viewB);
		p1_2.add(applyB);
		p1_2.add(cancelB);
		p1.add(p1_1);
		p1.add(p1_2);
	
		p2_1.setLayout(new GridLayout(7,1));
		p2_1.add(nameL);
		p2_1.add(nameTf);
		p2_1.add(ssnL);
		p2_1.add(ssnTf);
		p2_1.add(addrL);
		p2_1.add(addrTa);
	
		p2_2_1.add(stockpL);
		p2_2_2.add(portList);
		p2_2.setLayout(new BorderLayout());
		p2_2.add(p2_2_1,"North");
		p2_2.add(p2_2_2,"Center");

		p2_3_1.add(allcusL);
		p2_3_2.add(custList);
		p2_3.setLayout(new BorderLayout());
		p2_3.add(p2_3_1,"North");
		p2_3.add(p2_3_2,"Center");

		p2.setLayout(new GridLayout(1,3));
		p2.add(p2_3);
		p2.add(p2_1);
		p2.add(p2_2);

		p3.add(buyB);
		p3.add(symbolTf);
		p3.add(quanTf);
		p3.add(sellB);

		pc.setLayout(new BorderLayout());
		pc.add(p1,"North");
		pc.add(p2,"Center");
		pc.add(p3,"South");

		pe_1.add(stockiL);
		pe_2.setLayout(new BorderLayout());
		pe_2.add(allstockL,"North");
		pe_2.add(stockList,"Center");
		pe_2.add(stockB,"South");
		pe_3.setLayout(new GridLayout(2,2));
		pe_3.add(stocknameL);
		pe_3.add(stockTf);
		pe_3.add(stockpriceL);
		pe_3.add(priceTf);

		pe.setLayout(new BorderLayout());
		pe.add(pe_1,"North");
		pe.add(pe_2,"Center");
		pe.add(pe_3,"South");

		frame.add(pc,"Center");
		frame.add(pe,"East");
		//frame.add(tt,"North");

		frame.setSize(800,500);
		frame.setLocation(100, 100);
		frame.setVisible(true);
		//db���� data�� �����ͼ� List�� ������
		showList(db.getAllCustomers(),custList);
		
		showStockList(db.getAllStocks(),stockList);

		initButton(true);
		buyB.setEnabled(false);
		sellB.setEnabled(false);

		editableText(false);
		stockTf.setEditable	(false);
		priceTf.setEditable	(true);
		symbolTf.setEditable (true);
		addListener();
	}

	/**
	<PRE>
	 * Listener���� ��� �ִ� �޼ҵ�<BR>
	 * all Listener�� �ش� component�� add ��ų��<BR>
	 * < recommanded Listener ><BR>
	 * ActonListener : �ַ� Button event ó���� ���<BR>
	 * ItemListener : �ַ� List event ó���� ���<BR>
	 * TextListener : �ַ� TextField or TextArea event ó���� ���<BR>
	 * WindowListener : window closing�� ���<BR>
	 </PRE>
	 */
	public void addListener()
	{
		//�ڱ��ڽ��� Event Handler�� ����(type2)
		addB.addActionListener(this);
		deleteB.addActionListener(this);
		updateB.addActionListener(this);
		viewB.addActionListener(this);
		applyB.addActionListener(this);
		cancelB.addActionListener(this);
		buyB.addActionListener(this);
		sellB.addActionListener(this);
		stockB.addActionListener(this);
		custList.addItemListener(this);
		stockList.addItemListener(this);
		portList.addItemListener(this);               
		ssnTf.addTextListener(this);


	}
    /**
	 * ��� ������(ssn,name,addr)�� ���ʿ� �ִ� custList�� ������
	 * 1)customer List area�� �ִ� ���� �����<BR>
	 * 2)CustomerRec[]�� �ִ� ��� CustomerRec ��ü ������ List�� �ѷ��ش�<BR>
	 *   getSSN(), getName(), getAddr()
	 */
	public void showList(ArrayList<CustomerRec> custs, List custlist)
    {
    	System.out.println("customer list ȣ��");
		custlist.removeAll();
    	for(CustomerRec cust:custs)
		{
        	custlist.add(cust.getSSN().trim() + " " + cust.getName().trim() + " " + cust.getAddr().trim());
        }  
    }
     /**
	 * ��� �ֽ�����(symbol�� price)�� �����ʿ� �ִ� stockList�� ������
	 * 1)stock List area�� �ִ� ���� �����.<BR>
	 * 2)StockRec[]�� �ִ� ��� StockRec ��ü ������ List�� �ѷ��ش�.<BR>
	 *    getSymbol(), getPrice()
	 */
    public void showStockList(Set<StockRec> stocks, List stocklist) {
		System.out.println("stock list ȣ��");
		stocklist.removeAll();
		stocklist.setForeground(Color.yellow);
		for(StockRec stock : stocks) {
			stocklist.add(stock.getSymbol() + " " + stock.getPrice());
		}
    }
    /**
	 * custList���� �� ���� Ŭ���ϸ� portList�� �� ���� ������ �ֽļ��� portList�� �����ش�.
	 * showCustomer()���� ȣ��Ǿ� ����.
	 * 1)Vector �� �ִ� ��� ������ SharesRec���� casting ���� List�� �ѷ��ش�
	 *  getSymbol(), getQuantity()
	 */        
    public void showList(Vector<SharesRec> shares, List portlist)  {
		System.out.println("port list ȣ��");
		portlist.removeAll();
		SharesRec sr = null;
		for(int i=0;i<shares.size();i++) {
			sr = shares.elementAt(i);
			portlist.add(sr.getSymbol() + " " + sr.getQuantity());
		}
    }      
	/**
	<PRE>
	 * 1)customer List���� ���õ� �׸��߿��� ssn�� Tokenize�Ѵ� 
	 * StringTokenizer(String str) ������ ȣ��
	 * 2)�߶��� ssn���� DB�� customer table ���� �ش� ssn�� ������ �����´�
	 *  ==> public CustomerRec getCustomer(String ssn) 
	 *             throws RecordNotFoundException {} call �Ѵ�.
	 * 3)������ ������ ssnTf,nameTf,addrTa �� port List�� �Ѹ���.
	 *	 getName(), getSSN(), getAddr(), getPortfolio()
	 *   ssnTf.setText(cr.getSSN().trim());
	 *  itemStateChanged(ItemEvent e) ���� call�Ǿ� ����.
	 </PRE>
	 */    
   	public void showCustomer() {
		
		System.out.println("showCustomer() ȣ��");
		String s = custList.getSelectedItem();        
		
		StringTokenizer st = new StringTokenizer(s);
		String ssn = st.nextToken();
		CustomerRec cr = null;
		
		try {
			cr = db.getCustomer(ssn);
		}catch(RecordNotFoundException e) {}
		
		nameTf.setText(cr.getName().trim());
		ssnTf.setText(cr.getSSN().trim());	
		addrTa.setText(cr.getAddr().trim());
		
		if(cr.getPortfolio() != null) {
			showList(cr.getPortfolio(),portList);
		}else {
			portList.removeAll();
		}
   	}
    /**
	<PRE>
	 * 1)stock List �߿��� ���õ� �׸��� Token�Ѵ�.
	 * 2)symbol,price�� �ش� stockTf,priceTf �� setting �Ѵ�.
	 </PRE>
	 */
   	public void showStock(){
		System.out.println("showStock() ȣ��");
		String stock = stockList.getSelectedItem();
		StringTokenizer st = new StringTokenizer(stock);
		String symbol = st.nextToken();
		String price = st.nextToken();
		stockTf.setText(symbol.trim());
		priceTf.setText(price.trim());
     }

	/**
	 * 1)port List���� ���õ� �׸��� Token�Ѵ�.<BR>
	 * 2)symbol,quantity�� �ش� symbolTf, quanTf �� setting�Ѵ�<BR>
	 */
  	public void showPortfolio() {
		System.out.println("showPortfolio() ȣ��");
		String portfolio = portList.getSelectedItem();
		StringTokenizer st = new StringTokenizer(portfolio);
		String symbol = st.nextToken();
		String quan = st.nextToken();
		symbolTf.setText(symbol.trim());
		quanTf.setText(quan.trim());
	}
        
    /**
	 * database�� buyShares(String ssn, String symbol, int quantity)call
	 *                  throws RecordNotFoundException ȣ���Ѵ�.
	 * String -> int �� ��ȯ���־����
	 */
	public void buyStock(){
		try {
			String ssn = ssnTf.getText().trim();
			String symbol = symbolTf.getText().trim();
			int quantity = Integer.parseInt(quanTf.getText().trim());
			SharesRec shares = new SharesRec(ssn, symbol, quantity);
			db.buyShares(shares);
		}catch(RecordNotFoundException e) {}
		showCustomer();
   	} 
  
    /**
	 * database�� sellShares(String ssn, String symbol, int quantity)
	 *           throws RecordNotFoundException, InvalidTransactionException ȣ���Ѵ�
	 */
	public void sellStock() {
		try {
			String ssn = ssnTf.getText().trim();
			String symbol = symbolTf.getText().trim();
			int quantity = Integer.parseInt(quanTf.getText().trim());
			SharesRec shares = new SharesRec(ssn, symbol, quantity);
			db.sellShares(shares);
		}catch(RecordNotFoundException e) {
		}catch(InvalidTransactionException e) {}
		showCustomer();
   	} 
  
    /**
	 * 1)database�� addCustomer(String name, String ssn, String address) 
	 *         throws DuplicateIDException ȣ��
	 * 2)showList(CustomerRec[],List) call<BR>
	 * return type �� CustomerRec[] �� getAllCustomers()�� ȣ���Ѵ�.
	 */
	public void addCustomer(){
		try {
			CustomerRec customer = new CustomerRec(ssnTf.getText().trim(),nameTf.getText().trim(),addrTa.getText().trim());					
			db.addCustomer(customer);
		}catch(DuplicateIDException e) {}

		showList(db.getAllCustomers(),custList);
   	} 
        
    /**
	 * 1)database�� updateCustomer(String name, String ssn, String address)
	 *				throws RecordNotFoundException ȣ��
	 * 2)showList(CustomerRec[],List) call<BR>
	 */
	public void updateCustomer(){
		try {
			CustomerRec customer = new CustomerRec(ssnTf.getText().trim(),nameTf.getText().trim(),addrTa.getText().trim());
			db.updateCustomer(customer);
		}catch(RecordNotFoundException e) {}

		showList(db.getAllCustomers(),custList);
   	} 
	
    /**
	 * 1)database�� deleteCustomer(String ssn) 
	 *				throws RecordNotFoundException ȣ��
	 * 2)showList(CustomerRec[],List) call<BR>
	 */
	public void deleteCustomer(){
		try {
			db.deleteCustomer(ssnTf.getText().trim());
		}catch(RecordNotFoundException e) {}

		showList(db.getAllCustomers(),custList);
   	} 

  
	/**
	<PRE>
	 * button Ȱ��ȭ�� ���� method�� 
 	 * �� �޼ҵ�� 1���� 3���� Ȱ��ȭ �Ǹ� 2���� 2���� ��Ȱ��ȭ �ǰ�,
	 * 2���� 2���� Ȱ��ȭ �Ǹ� 1���� 3���� ��Ȱ��ȭ �� 
	 * < �ʱ� ���� >
	 * 1)add,delete,update,view : Ȱ��ȭ
	 * 2)apply,cancel : ��Ȱ��ȭ
	 </PRE>
	 */
    public void initButton(boolean b){
		addB.setEnabled(b);
		deleteB.setEnabled(b);
		updateB.setEnabled(b);
		viewB.setEnabled(b);
		applyB.setEnabled(!b);
		cancelB.setEnabled(!b);
   	}
   
	/**
	<PRE>
	 * ssn,name,address textfield�� ����/������ ���� �����ϴ� �޼ҵ�
	 * true : ��� ��������
	 * false : ��� ����������
	 </PRE>
	 */
	public void editableText(boolean b)	{
		nameTf.setEditable(b);
		ssnTf.setEditable(b);
		addrTa.setEditable(b);
	}

	/**
	 * ssn,name,address�� textfiled���¸� null("")�� setting ��Ŵ
	 */
    public void clearText()  {
		nameTf.setText("");
		ssnTf.setText("");
		addrTa.setText("");
    }
  
	/**
	<PRE>
	 * TextListener �� method : TextField�� TextArea�� ������ ����Ǵ� event �߻��� call
	 * 1)ssn textfield�� ������ null�̸� 2���� button(buy,sell)�� ��Ȱ��ȭ ��ų��
	 * 2)ssn textfield�� ������ not null�̸� 2���� button(buy,sell)�� Ȱ��ȭ ��ų��
    </PRE>
	 */
	public void textValueChanged(TextEvent e){
		if(ssnTf.getText().trim() != null) {
			buyB.setEnabled(true);
			sellB.setEnabled(true);
		}else {
			buyB.setEnabled(false);
			sellB.setEnabled(false);
		}
	}

	/**
	<PRE>
	 * List������ �ٸ� �׸��� �����ϸ� call
	 * 1)�� �޼ҵ� ȣ��� ������ 2���� button(buy,sell)�� ���¸� null�� �����
	 * 2)�� �޼ҵ带 ȣ���Ų event source��
	 *   customer List�� ��� : showCustomer() method call
	 *   portfolio List�� ��� : showPortfolio() method call
	 *   stock List �� ��� : showStock() method call
	</PRE>
	 */
    public void itemStateChanged(ItemEvent e) {

	    symbolTf.setText("");
		quanTf.setText("");
		if(e.getSource()==custList) {
			showCustomer();
		}else if(e.getSource()==stockList) {
			showStock();
		}else {
			showPortfolio();
		}
	}

	/**
	 * getActionCommand() or getSource() �� ����Ͽ� event ó��
	 * ��ư�� command �� ADD�̸� 
	 *  ==> clearText();
	 *		editableText(true);
	 *		initButton(false);
	 */
   	public void actionPerformed(ActionEvent e)	{
		//if(e.getSource()==addB){	}
		char command = e.getActionCommand().charAt(0);
		System.out.println("button " + command);
		switch(command) {
			case 'A' :			//ADD
				clearText();
				editableText(true);
				initButton(false);
				flag = 1;
				break;
			case 'D' :			//DELETE
				showDialog("DELETE");
				break;
			case 'U' :			//UPDATE
				flag = 2;
				editableText(true);
				ssnTf.setEditable(false);
				initButton(false);
				
				break;
			case 'V' :			//VIEW
				showList(db.getAllCustomers(),custList);
				break;
			case 'a' :			//apply
				if(flag == 1) {
					addCustomer();
				}else if(flag == 2) {
					updateCustomer();
				}
				clearText();
				editableText(false);
				initButton(true);
				break;
			case 'C' :			//cancel
				editableText(false);
				initButton(true);
				flag =0;
				break;
			case 'B' :
				buyStock();
				break;
			case 'S' :
				sellStock();
				break;
			case 'c' :
				dialog.dispose();
				break;
			case 'O' :
				deleteCustomer();
				clearText();
				editableText(false);
				initButton(true);
				dialog.dispose();
				break;
			case 'G' :
				showStockList(db.getAllStocks(), stockList);
				break;
			default :	
		}

	}
    /**
	 * update�� delete�� Ȯ�������� �ʿ�
	 */
	public void showDialog(String str)
	{

		dialog		= new Dialog(frame, true);
		Panel 	pc	= new Panel();
		Panel 	ps	= new Panel();
		Button 	okB 	= new Button("Ok");
			okB.addActionListener(this);
		Button 	canB 	= new Button("cancel");
			canB.addActionListener(this);

		dialog.addWindowListener(
			new WindowAdapter()
			{	public void windowClosing(WindowEvent we)
				{	dialog.dispose();
				}
			}
		);

		pc.setLayout(new GridLayout(3, 1));
		pc.add(new Label("     SSN         = " + ssnTf.getText()));
		pc.add(new Label("     Name      = " + nameTf.getText()));
		pc.add(new Label("     Address   = " + addrTa.getText()));

		ps.add(okB);
		ps.add(canB);

		dialog.add(pc, "Center");
		dialog.add(ps, "South");

		dialog.setTitle(str);
		dialog.setSize(300, 150);
		dialog.setLocation(300, 300);
		dialog.setVisible(true);
	}
    /**
	 * 15�ʸ��� showList(StockRec[],List) call
	 */
	public void run(){
		while(true)	{
			showStockList(db.getAllStocks(), stockList);
			try	{	
				Thread.sleep(15000);
			}catch(InterruptedException e){}
		}
	}

	public static void main(String[] args) {
		Broker broker = new Broker();
		Thread t = new Thread(broker);
		t.start();
	}
}