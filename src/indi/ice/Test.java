package indi.ice;


public class Test {
	public static void main(String args[]){  
		ColFlt aptest=new ColFlt();
		//�����û��ʹ�ѡ���item���������Ƽ���item����������
		int b=aptest.getItems(2, new int[] {1,2,3,4});
		//for(int t:b)
		System.out.println(b);
	}  
}
