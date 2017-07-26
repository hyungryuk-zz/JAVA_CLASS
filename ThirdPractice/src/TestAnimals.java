
public class TestAnimals {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Fish fishFish = new Fish();
		Pet petFish = new Fish();
		Animal animalFish = new Fish();
		Cat catCat = new Cat("catCat");
		Pet petCat = new Cat("petCat");
		Animal animalCat = new Cat("animalCat");
		Animal animalSpider = new Spider();
		Spider spiderSpider = new Spider();		

		fishFish.play();
		fishFish.eat();
		fishFish.walk();
		
		petFish.play();
		
		animalFish.eat();
		animalFish.walk();
		
		catCat.play();
		catCat.eat();
		catCat.walk();
		
		petCat.play();
		
		animalCat.eat();
		animalCat.walk();
		
		animalSpider.eat();
		animalSpider.walk();
		
		spiderSpider.eat();
		spiderSpider.walk();
	}

}
