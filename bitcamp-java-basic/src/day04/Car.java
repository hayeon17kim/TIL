package day04;

public class Car {
	// �ʵ带 ������
	// �ʵ�� ������ �����ϵ� ����� �ش�.
	private String plateNumber;
	private String color;
	private String type;
	private int price;
	private int year;
	
	// �޼ҵ带 ������
	// 1. ���� Ÿ��, �Ķ���Ͱ� ���� �޼ҵ�
	void startEngine() {
		System.out.println("�õ��� �̴ϴ�.");
	}
	// 2. ���� Ÿ���� ������ �Ķ���Ͱ� ���� �޼ҵ�
	public String getPlateNumber() {
		return plateNumber;
	}
	
	// 3. ���� Ÿ���� ������ �Ķ���Ͱ� �ִ� �޼ҵ�
	public void setPlateNumber(String plateNumber) {
		// �Ķ���ʹ� �ܺο��� �����ִ� ����
		// �޼ҵ� ���ο��� ������ ����ó�� �ٷ� �� �ְ� �ȴ�.
		// �� ���� userNumber�� �ܺο��� ���� �Ѱ��� ������
		// �ٸ� �̸��� String �����̰ų� ""�� ������� �ӽ� String ������ �� ������
		// setPlateNumber��� �޼ҵ� ���ο����� ������
		// userNumber�� �Ҹ��� �ȴ�!
		
		// �׷��� �Ķ���͸� �������� "�Ű� ����"��� �θ��⵵ �ߴ�.
		this.plateNumber = plateNumber;
	}
	
	// 4. ���� Ÿ�԰� �Ķ���Ͱ� �ִ� �޼ҵ�
	boolean checkPlateNumber(String userNumber) {
		if(plateNumber.equals(userNumber)) {
			return true;
		} else {
			return false;
		}
	}
	
	// �Ķ���� �ִ� �����ڸ� �����ô�.
	public Car(String plateNumber, String color, String type, int price, int year){
		this.plateNumber = plateNumber;
		this.color = color;
		this.type = type;
		this.price = price;
		this.year = year;
	}
	
	// �Ķ���Ͱ� �ִ� �����ڸ� ����ٸ� �Ķ���Ͱ� ���� �����ڸ�
	// ȣ���� �� ���� ������
	// �Ķ���Ͱ� ���� �����ڵ� ������־�� �Ѵ�.
	Car(){
		plateNumber = new String();
		color = new String();
		type = new String();
		price = 0;
		year = 0;
	}
	
	//equals �޼ҵ带 �������̵� �غ���
	public boolean equals(Object o) {
		// instanceof ��� ��ɾ ���ؼ�
		// �Ķ���ͷ� �Ѿ�� o�� Car Ŭ������ ��ü���� Ȯ���Ѵ�.
		if(o instanceof Car) {
			// ���� o instanceof Car�� �ǹ̴�
			// o�� Car Ŭ���� ��ü�̸� true, �ƴϸ� false�� ���ϵǴ� Ű���带
			// ����� ���̴�.

			// �� if�� ���Դ� ���� o�� Car Ŭ���� ��ü�̴� ��� �ǹ��̹Ƿ�
			// o�� ����� ����ȯ�� ���ؼ� Car Ŭ���� ��ü�� ���� ������ �� �ִٶ�� �ǹ̰� �ȴ�.
			Car c = (Car)o;
			// ����ȯ�� c�� �ʵ尪��� �� �޼ҵ带 ȣ���ϴ� ��ü�� �ʵ尪����
			// ���ؼ� "�ֿ� ����"�� ������ return true ���ش�.
			if(plateNumber.contentEquals(c.plateNumber)
					&&
					type.equals(c.type)) {
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		// return�� ���
		// ���� ������ �ƴ϶�
		// ���� return�� �� ���� �ִ�.
		return "���� ��ȣ: " + plateNumber
				+ ", ���� ����: " + type
				+ ", ���� ����: " + color
				+ ", ���� ����: " + year
				+ ", ���� ����: " + price;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	
}
