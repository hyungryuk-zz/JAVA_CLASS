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
 * Database 클래스에서 가져온 결과를 GUI에 뿌려주는 기능을 담당하는 class.

 * <B>-- 초기 화면 구성 --</B>
 
 * 1)event 처리를 포함한 GUI setting : addListener() method call
 * 2)customer list를 보여줌 : showList(CustomerRec,List) method call
 * 3)stock list를 보여줌 : showList(StockRec,List) method call
 * 4)< button의  초기 활성화 상태 >
 *   add,update,delete : 활성화 되어야 함
 *   apply,cancel,buy,sell : 비활성화되어야 함
 * 5)< textfield와 textarea의 초기 편집상태 >
 *   모든 textfield와 textarea는 비편집상태이어야함 

 * <B>-- 구현되어야 할 기능 --</B>
 * 1)customer list 항목 하나를 click하면 port list에 보유주식이 나타나고
 *   name,ssn,addr tf에 해당항목 정보를 보여줄것
 * 2)stock list 항목 하나를 click하면 buy,stock,price tf에 해당정보를 
 *   보여주고 해당 주식을 살수(buy)있어야 함
 * 3)port list 항목 하나를 click 하면 buy,sell tf에 해당정보를 보여주고
 *   주식을 팔거나(sell) 살수(buy)있어야 함
 * 4)add button : 새로운 고객을 customer table에 입력 
 *                - button(add,delete,update,buy,sell)의 비활성화
 *                - button(apply,cancel)의 활성화
 *                - textfield(name,ssn,addr)의 편집상태
 * 5)delete button : 해당 ssn고객을 customer table에서 지움
 *                   - dialog 창으로 confirm받을것
 * 6)update button : 특정 고객정보를 변경함
					 - button(add,delete,update,buy,sell)의 비활성화
 *                   - button(apply,cancel)의 활성화
 *                   - textfield(name,ssn,addr)의 편집상태
 * 7)buy,sell button : 주식을 사고 팜
 * 8)apply button : dialog 창으로 최종 confirm 받게함
 * 9)stock list는 대략 15초마다 DB Stock table에서 가져온 새로운 정보로 갱신되어야함
 * ActionListener 인터페이스 : actionPerformed(ActionEvent e) method구현해야함
 * ItemListener 인터페이스 : itemStateChanged(ItemEvent e) method구현해야함
 * TextListener 인터페이스 : textValueChanged(TextEvent e) method구현해야함
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
	 * database 객체 생성<BR>
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
		//annoymous Inner class 형태(type4)
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
		//db에서 data를 가져와서 List에 보여줌
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
	 * Listener만을 담고 있는 메소드<BR>
	 * all Listener를 해당 component에 add 시킬것<BR>
	 * < recommanded Listener ><BR>
	 * ActonListener : 주로 Button event 처리에 사용<BR>
	 * ItemListener : 주로 List event 처리에 사용<BR>
	 * TextListener : 주로 TextField or TextArea event 처리에 사용<BR>
	 * WindowListener : window closing에 사용<BR>
	 </PRE>
	 */
	public void addListener()
	{
		//자기자신이 Event Handler로 구현(type2)
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
	 * 모든 고객정보(ssn,name,addr)을 왼쪽에 있는 custList에 보여줌
	 * 1)customer List area에 있는 모든걸 지운다<BR>
	 * 2)CustomerRec[]에 있는 모든 CustomerRec 객체 내용을 List에 뿌려준다<BR>
	 *   getSSN(), getName(), getAddr()
	 */
	public void showList(ArrayList<CustomerRec> custs, List custlist)
    {
    	System.out.println("customer list 호출");
		custlist.removeAll();
    	for(CustomerRec cust:custs)
		{
        	custlist.add(cust.getSSN().trim() + " " + cust.getName().trim() + " " + cust.getAddr().trim());
        }  
    }
     /**
	 * 모든 주식정보(symbol과 price)을 오른쪽에 있는 stockList에 보여줌
	 * 1)stock List area에 있는 모든걸 지운다.<BR>
	 * 2)StockRec[]에 있는 모든 StockRec 객체 내용을 List에 뿌려준다.<BR>
	 *    getSymbol(), getPrice()
	 */
    public void showStockList(Set<StockRec> stocks, List stocklist) {
		System.out.println("stock list 호출");
		stocklist.removeAll();
		stocklist.setForeground(Color.yellow);
		for(StockRec stock : stocks) {
			stocklist.add(stock.getSymbol() + " " + stock.getPrice());
		}
    }
    /**
	 * custList에서 한 고객을 클릭하면 portList에 그 고객이 보유한 주식수를 portList에 보여준다.
	 * showCustomer()에서 호출되어 진다.
	 * 1)Vector 에 있는 모든 내용을 SharesRec으로 casting 한후 List에 뿌려준다
	 *  getSymbol(), getQuantity()
	 */        
    public void showList(Vector<SharesRec> shares, List portlist)  {
		System.out.println("port list 호출");
		portlist.removeAll();
		SharesRec sr = null;
		for(int i=0;i<shares.size();i++) {
			sr = shares.elementAt(i);
			portlist.add(sr.getSymbol() + " " + sr.getQuantity());
		}
    }      
	/**
	<PRE>
	 * 1)customer List에서 선택된 항목중에서 ssn을 Tokenize한다 
	 * StringTokenizer(String str) 생성자 호출
	 * 2)잘라진 ssn으로 DB의 customer table 에서 해당 ssn의 정보를 가져온다
	 *  ==> public CustomerRec getCustomer(String ssn) 
	 *             throws RecordNotFoundException {} call 한다.
	 * 3)가져온 정보를 ssnTf,nameTf,addrTa 와 port List에 뿌린다.
	 *	 getName(), getSSN(), getAddr(), getPortfolio()
	 *   ssnTf.setText(cr.getSSN().trim());
	 *  itemStateChanged(ItemEvent e) 에서 call되어 진다.
	 </PRE>
	 */    
   	public void showCustomer() {
		
		System.out.println("showCustomer() 호출");
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
	 * 1)stock List 중에서 선택된 항목을 Token한다.
	 * 2)symbol,price를 해당 stockTf,priceTf 에 setting 한다.
	 </PRE>
	 */
   	public void showStock(){
		System.out.println("showStock() 호출");
		String stock = stockList.getSelectedItem();
		StringTokenizer st = new StringTokenizer(stock);
		String symbol = st.nextToken();
		String price = st.nextToken();
		stockTf.setText(symbol.trim());
		priceTf.setText(price.trim());
     }

	/**
	 * 1)port List에서 선택된 항목을 Token한다.<BR>
	 * 2)symbol,quantity를 해당 symbolTf, quanTf 에 setting한다<BR>
	 */
  	public void showPortfolio() {
		System.out.println("showPortfolio() 호출");
		String portfolio = portList.getSelectedItem();
		StringTokenizer st = new StringTokenizer(portfolio);
		String symbol = st.nextToken();
		String quan = st.nextToken();
		symbolTf.setText(symbol.trim());
		quanTf.setText(quan.trim());
	}
        
    /**
	 * database의 buyShares(String ssn, String symbol, int quantity)call
	 *                  throws RecordNotFoundException 호출한다.
	 * String -> int 로 변환해주어야함
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
	 * database의 sellShares(String ssn, String symbol, int quantity)
	 *           throws RecordNotFoundException, InvalidTransactionException 호출한다
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
	 * 1)database의 addCustomer(String name, String ssn, String address) 
	 *         throws DuplicateIDException 호출
	 * 2)showList(CustomerRec[],List) call<BR>
	 * return type 이 CustomerRec[] 인 getAllCustomers()을 호출한다.
	 */
	public void addCustomer(){
		try {
			CustomerRec customer = new CustomerRec(ssnTf.getText().trim(),nameTf.getText().trim(),addrTa.getText().trim());					
			db.addCustomer(customer);
		}catch(DuplicateIDException e) {}

		showList(db.getAllCustomers(),custList);
   	} 
        
    /**
	 * 1)database의 updateCustomer(String name, String ssn, String address)
	 *				throws RecordNotFoundException 호출
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
	 * 1)database의 deleteCustomer(String ssn) 
	 *				throws RecordNotFoundException 호출
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
	 * button 활성화에 대한 method임 
 	 * 이 메소드는 1번의 3개가 활성화 되면 2번의 2개가 비활성화 되고,
	 * 2번의 2개가 활성화 되면 1번의 3개가 비활성화 됨 
	 * < 초기 상태 >
	 * 1)add,delete,update,view : 활성화
	 * 2)apply,cancel : 비활성화
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
	 * ssn,name,address textfield의 편집/비편집 상태 결정하는 메소드
	 * true : 모두 편집상태
	 * false : 모두 비편집상태
	 </PRE>
	 */
	public void editableText(boolean b)	{
		nameTf.setEditable(b);
		ssnTf.setEditable(b);
		addrTa.setEditable(b);
	}

	/**
	 * ssn,name,address의 textfiled상태를 null("")로 setting 시킴
	 */
    public void clearText()  {
		nameTf.setText("");
		ssnTf.setText("");
		addrTa.setText("");
    }
  
	/**
	<PRE>
	 * TextListener 의 method : TextField나 TextArea의 내용이 변경되는 event 발생시 call
	 * 1)ssn textfield의 내용이 null이면 2개의 button(buy,sell)을 비활성화 시킬것
	 * 2)ssn textfield의 내용이 not null이면 2개의 button(buy,sell)을 활성화 시킬것
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
	 * List내에서 다른 항목을 선택하면 call
	 * 1)이 메소드 호출시 언제나 2개의 button(buy,sell)의 상태를 null로 만들것
	 * 2)이 메소드를 호출시킨 event source가
	 *   customer List일 경우 : showCustomer() method call
	 *   portfolio List일 경우 : showPortfolio() method call
	 *   stock List 일 경우 : showStock() method call
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
	 * getActionCommand() or getSource() 를 사용하여 event 처리
	 * 버튼의 command 가 ADD이면 
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
	 * update나 delete시 확인절차에 필요
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
	 * 15초마다 showList(StockRec[],List) call
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