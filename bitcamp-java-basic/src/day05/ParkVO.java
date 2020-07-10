package day05;
// MVC 패턴
// Model: 데이터를 담는 클래스
// View: 데이터를 보여주는 클래스
// Controller: 데이터를 컨트롤하는 클래스

public class ParkVO {
	// VO 란 Value Object 라는 뜻이며
	// 대표적인 Model 클래스의 접미사가 된다.
	// (또다른 접미사는 Data Transfer Object DTO 가 있다.)
	// VO, DTO에는
	// 필드. 겟터/셋터, equals, toString() 정도의 메소드만 들어가게 된다.
	
	// 차량 번호
	private String plateNumber;
	// 들어온 시간
	private int inTime;
	private int outTime;
	
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public int getInTime() {
		return inTime;
	}
	public void setInTime(int inTime) {
		this.inTime = inTime;
	}
	public int getOutTime() {
		return outTime;
	}
	public void setOutTime(int outTime) {
		this.outTime = outTime;
	}

	
	public boolean equals(Object obj) {
		if(obj instanceof ParkVO) {
			ParkVO p = (ParkVO)obj;
			if(plateNumber.equals(p.plateNumber)) {
				return true;
			}
		}
		
		return false;
	}
	
}
