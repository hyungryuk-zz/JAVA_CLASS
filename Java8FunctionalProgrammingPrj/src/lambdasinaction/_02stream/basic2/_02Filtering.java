package lambdasinaction._02stream.basic2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lambdasinaction._02stream.basic1.Dish;

public class _02Filtering {

    public static void main(String...args){

        // 1. Filtering with predicate ( isVegeterian() )
    	//List<Dish> vegeList = 
    	List<Dish> vegeList = Dish.menu.stream()
    							.filter(Dish::isVegetarian)
    							.collect(Collectors.toList());
    	
    	System.out.println();
    	vegeList.stream().forEach(dish->System.out.println(dish.getName()));
        // 2. Filtering unique elements
    	
    	List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);

    	System.out.println();
    	numbers.stream().distinct().forEach(num->System.out.println(num));
    	
        //3. Truncating 3 stream ( d.getCalories() > 300 )
    	//List<Dish> dishesLimit3 = 
    	
    	System.out.println();
    	List<Dish> dishesLimit3 = Dish.menu.stream()
    								.filter(dish->dish.getCalories()>300)
    								.limit(3)
    								.collect(Collectors.toList());
    	
    	dishesLimit3.stream().forEach(dish->System.out.println(dish.getName()));
    	

        //4. Skipping elements
    	List<Dish> dishesSkip2 = Dish.menu.stream()
    								.skip(2)
    								.collect(Collectors.toList());
    	
    	System.out.println();
    	dishesSkip2.stream().forEach(data->System.out.println(data.getName()));

    	


    }
}
