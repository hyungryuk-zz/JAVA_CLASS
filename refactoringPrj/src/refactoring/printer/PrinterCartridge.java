package refactoring.printer;

public class PrinterCartridge {
	private double capacity;
	private double reductionRate;

	public PrinterCartridge() {
	}

	public PrinterCartridge(double capacity, double reductionRate) {
		this.capacity = capacity;
		this.reductionRate = reductionRate;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public double getReductionRate() {
		return reductionRate;
	}
	
	public void consume() {
		capacity = capacity - reductionRate;
	}
	
	public boolean isAvailable() {
		return (capacity-reductionRate)>=0;
	}
	
}