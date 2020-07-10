package day05;



public class Park {
	
	public String plateNumber;
	public int time;
	
	public Park(String plateNumber, int time) {
		this.plateNumber = plateNumber;
		this.time = time;
	}
	
	public Park() {
		plateNumber = new String();
		time = 0;
	}
	
	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public boolean equals(Object o) {
		if(o instanceof Park) {
			Park p = (Park)o;
			if(plateNumber.contentEquals(p.plateNumber)) {
				return true;
			}
		}
		return false;
	}
	
	
}
