package indi.ice;


public class Test {
	public static void main(String args[]){  
		ColFlt aptest=new ColFlt();
		//输入用户和待选择的item，即可能推荐的item，返回最优
		int b=aptest.getItems(2, new int[] {1,2,3,4});
		//for(int t:b)
		System.out.println(b);
	}  
}
