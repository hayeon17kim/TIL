package day05a;
// MVC ����
// Model: �����͸� ��� Ŭ����
// View: �����͸� �����ִ� Ŭ����
// Controller: �����͸� ��Ʈ�� �ϴ� Ŭ���� 
public class ParkVO {
	//VO �� Value Object ��� ���̸�
	//��ǥ���� Model Ŭ������ ���̻簡 �ȴ�.
	//(�Ǵٸ� ���̻�� Data Transfer Object DTO �� �ִ�.)
	//VO, DTO���� 
	//�ʵ�, ����/����, equals, toString() ������ �޼ҵ常 ���� �ȴ�.
	
	//���� ��ȣ
	private String plateNumber;
	//���� �ð�
	private int inTime;
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















