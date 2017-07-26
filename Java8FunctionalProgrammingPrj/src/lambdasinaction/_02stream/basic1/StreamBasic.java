package lambdasinaction._02stream.basic1;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class StreamBasic {

    public static void main(String...args){
        // Java 7
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

        System.out.println(getLowCaloricDishesNamesInJava8(Dish.menu));
        System.out.println("---");
        
        System.out.println(getGroupingMenu(Dish.menu));

        System.out.println(getMaxCaloryDish(Dish.menu));
        // Java 8


    }

    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes){
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish d: dishes){
            if(d.getCalories() <= 400){
                lowCaloricDishes.add(d);
            }
        }
        List<String> lowCaloricDishesName = new ArrayList<>();
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish d1, Dish d2){
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        for(Dish d: lowCaloricDishes){
            lowCaloricDishesName.add(d.getName());
        }
        List<String> lowCaloricLimit3DishesName = new ArrayList<>();
        lowCaloricLimit3DishesName = lowCaloricDishesName.subList(0,3);

        return lowCaloricLimit3DishesName;
    }

    //Java 8
    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes){
    	
//        return Dish.menu.stream()
//        		.filter(dish->dish.getCalories()<400)
//        		.sorted(Comparator.comparing(dish->dish.getCalories()))
//        		.map(dish->dish.getName())
//        		.collect(Collectors.toList());
        
        return Dish.menu.stream()
        		.filter(dish->dish.getCalories()<400)
        		.sorted(Comparator.comparing(Dish::getCalories))
        		.map(Dish::getName)
        		.collect(Collectors.toList());
    	
    			
    }
    
    //400Į�θ� ������ �޴��� ���̾�Ʈ��, �ƴ� ��� �Ϲ����� �׷����ض�.
    public static Map<String, List<Dish>>  getGroupingMenu(List<Dish> dishes){
    	return dishes.stream()
    			.collect(Collectors.groupingBy(dish->{
    				if(dish.getCalories()<=400)
    					return "���̾�Ʈ";
    				else
    					return "�Ϲ�";
    			}));
    			
 
    }
    
 
    //���� Į�θ��� ���� �޴��� ã�ƶ�
    public static Dish getMaxCaloryDish (List<Dish> dishes) {
    	return dishes.stream()
    			.max(Comparator.comparing(Dish::getCalories))
    			.get();  			
    	
    	
    }
}