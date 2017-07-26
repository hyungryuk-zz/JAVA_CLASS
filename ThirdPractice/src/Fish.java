
public class Fish extends Animal implements Pet {
	private String name;
	
	public Fish() {
		super(0);
		this.name = "";
	}
	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("Fish eat somthing!!");
	}
	
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	@Override
	public void play() {
		// TODO Auto-generated method stub
		System.out.println("Fish play!");
	}
	@Override
	public void walk() {
		System.out.println("Fish can't walk! and don't have legs");
	}

}
