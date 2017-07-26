
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class haha {
   public static void main(String args[]) {
	  Scanner sc = new Scanner(System.in);
	  List<Integer>arrList = new ArrayList<>();
	  int t = sc.nextInt();
	  int input,start,end;
	  arrList.add(sc.nextInt());
	  for(int i=1;i<t;i++) {
		  input = sc.nextInt();
		  start = 0;
		  end = arrList.size()-1;
		  while(true) {
			  /*
			   * 입력시 바로 정렬
			   */
		  }
	  }
	  for(int i=0;i<arrList.size();i++) {
		  System.out.println(arrList.get(i));
	  }
	  
  }
}

