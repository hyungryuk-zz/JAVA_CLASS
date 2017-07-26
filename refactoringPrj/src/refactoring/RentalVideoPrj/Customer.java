package refactoring.RentalVideoPrj;
import java.util.Enumeration;
import java.util.Vector;



public class Customer {
	private String name;
	private Vector<Rental> _rentals = new Vector<Rental>();
	
	public Customer(String name) {
		this.name = name;
	}
	public void addRental(Rental arg){
		_rentals.addElement(arg);
	}
	public String getName() {
		return name;
	}
	public String statement() {
		double totalAmount =0;
		int frequentRenterPoints =0;
		Enumeration<Rental> rentals = _rentals.elements();
		String result = "Rental Record for " + getName() + "\n";
		while(rentals.hasMoreElements()){
			double thisAmount = 0;
			Rental each = rentals.nextElement();
			
			//�� ��ȭ�� ���� ��� ����
			switch(each.getMovie().getPriceCode()) {
				case Movie.REGULAR:
					thisAmount +=2;
					if(each.getDaysRented() > 2)
						thisAmount += (each.getDaysRented() - 2) * 1.5;
					break;
				case Movie.NEW_RELEASE:
					thisAmount += each.getDaysRented() * 3;
					break;
				case Movie.CHILDREN:
					thisAmount += 1.5;
					if (each.getDaysRented() > 3)
						thisAmount += (each.getDaysRented() - 3) * 1.5;
					break;
			}//
			//����Ʈ (frequent renter points) �߰�
			frequentRenterPoints ++;
			//�ֽ� (new release) �� ��Ʋ �̻� �뿩�ϴ� ��� �߰�����Ʈ ����
			if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) &&
				each.getDaysRented() > 1) frequentRenterPoints ++;
			//�� �뿩�� ���� ��� ����� ǥ��
			result += "\t" + each.getMovie().getTitle() + "\t"+
				String.valueOf(thisAmount) + "\n";
			totalAmount += thisAmount;
		}
		//ǲ�� (footer) �߰�
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)+
		"frequent renter points";
		return result;
	}
	
}
