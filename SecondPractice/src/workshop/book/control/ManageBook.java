package workshop.book.control;

import workshop.book.entity.*;

public class ManageBook {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Publication[] pubs = new Publication[5];
		pubs[0]=new Magazine("����ũ�μ���Ʈ","2007-10-01",328,9900,"�ſ�");
		pubs[1]=new Magazine("�濵����ǻ��","2007-10-03",316,9000,"�ſ�");
		pubs[2]=new Novel("���߿�","2007-07-01",396,9800,"����������������","����Ҽ�");
		pubs[3]=new Novel("���ѻ꼺","2007-04-14",383,11000,"����","���ϼҼ�");
		pubs[4]=new ReferenceBook("�ǿ��������α׷���","2007-01-14",496,25000,"����Ʈ�������");
		
		
		System.out.println("====Book ���� ���====");
		for(Publication pub : pubs) {
			System.out.println(pub);
		}
		ManageBook mgb = new ManageBook();
		mgb.modifyPrice(pubs[2]);
	}
	
	public void modifyPrice(Publication pub) {
		System.out.println("==== �������� ���� ��====");
		System.out.println(pub.getTitle() + " : " + pub.getPrice());
		if(pub instanceof Magazine)
			pub.setPrice((int)(pub.getPrice()*0.6));
		else if(pub instanceof Novel)
			pub.setPrice((int)(pub.getPrice()*0.8));
		else if(pub instanceof ReferenceBook)
			pub.setPrice((int)(pub.getPrice()*0.9));
		else {}
		System.out.println("==== �������� ���� ��====");
		System.out.println(pub.getTitle() + " : " + pub.getPrice());
	}

}
