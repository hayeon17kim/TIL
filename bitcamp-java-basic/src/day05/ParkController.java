package day05;
// ��Ʈ�ѷ� Ŭ������
// �� MVC ���Ͽ�����
// ������ ���� ��û�� ���� �����͸� �����ͺ��̽��� �ְų� ���ų� �ϰ�
// �׸��� �� ������� �ٽ� ������ �����ִ� ������ �Ѵ�.
// �׷��� �츮�� ���� �����ϱ�
// ���⼭ ArrayList�� �ϳ��� �ʵ�� ������
// ������ �����ͺ��̽� ������ �ñ��.

import java.util.ArrayList;

// �׸��� �信�� ��û�� ������ �����͸� �����ְų�
// �����͸� �޾Ƽ� �ش� ���� �����ϴ� ���� üũ�Ѵٵ����� �����
// ���⿡ �־��ְ� �ȴ�. 
public class ParkController {
	private ArrayList<ParkVO> list;
	
	// �����ڸ� ȣ���ϸ� �� list�� �ʱ�ȭ�ǰ� ���������
	public ParkController() {
		list = new ArrayList<ParkVO>();
		
	}
	
	// ���� ���� list�� ����Ǿ� �ִ� ��ü�� ũ�⸦ ��ȯ�ϴ�
	// size() �޼ҵ带 ������.
	public int size() {
		return list.size();
	}
	
	// �Ķ���ͷ� �Ѿ�� ParkVO ��ü�� �����ϴ��� üũ�ϴ�
	// contains �޼ҵ�
	public boolean contains(ParkVO p) {
		return list.contains(p);
	}
	
	// �Ķ���ͷ� �Ѿ�� ParkVO ��ü�� list�� �Է��ϴ�
	// add �޼ҵ�
	public void add(ParkVO p) {
		list.add(p);
	}
	
	public void remove(ParkVO p) {
		list.remove(p);
	}
	
	
	// �Էµ� �ð��� �ùٸ��� true, �ùٸ��� ������ false��
	// �����ϴ� validateTime �޼ҵ�
	// �� �ð��� int�� �Էµȴ�.
	public boolean validateTime(int time) {
		// �Էµ� �ð���
		// ��� �ؾ� 
		// �տ� 2�ڸ� ���� 2�ڸ� ���� �� ������?
		// �տ� 2�ڸ��� / 100 �ϸ� �ȴ�
		// �ڿ� 2�ڸ��� % 100 �ϸ� �ȴ�.
		int hour = time / 100;
		int minute = time % 100;
		
		if(hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59) {
			return true;
		}
		
		return false;
	}
	
	public void take(ParkVO p) {
		int index = list.indexOf(p);
		p.setInTime(list.get(index).getInTime());
	}



	
	
}
