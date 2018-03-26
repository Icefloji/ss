package indi.ice;

import java.util.HashMap;
import java.util.Map.Entry;

public class ColFlt {
   private int [][] a; //user and item matrix
   private final static int userNumber=3 ; 
   private HashMap<Integer,Double> userCon=new HashMap<Integer,Double>();  //store the confirmed user's sim
   //�������
   public ColFlt() {
	   a=new int[][] {
		   new int[]{1,2,3,4,5},
		   new int[]{1,2,3,4,3},
		   new int[]{3,2,2,1,2},
		   new int[]{3,5,0,1,5},
		   new int[]{3,4,1,4,5} 
	   };
	   for(int []ui:a)
		   for(int uic:ui)
			   if(uic==0) uic=3;
   }
   private  double aver(int u)
    {
    	double sum=0;
    	for(int ui:a[u])
    		sum+=ui;
    	return sum/a[u].length;
    }
   //return the similarity between two user
   private  double sim(int i,int j)
    {
    	double iAver,jAver,similarity=0;
    	double si,sj,sij;
    	si=sj=sij=0;
    	iAver=aver(i);
    	jAver=aver(j);
    	for(int k=0;k<a[0].length;k++)
    	{
    		sij+=(a[i][k]-iAver)*(a[j][k]-jAver);
    		si+=(a[i][k]-iAver)*(a[i][k]-iAver);
    		sj+=(a[j][k]-jAver)*(a[j][k]-jAver);
    	}
    	similarity=sij/Math.sqrt(si)/Math.sqrt(sj);
    	return similarity;
    }
   public  int[] getSimUsers(int u)
    {
    	int[]users=new int[userNumber]; //ע��Ҫ��֤���з���userΪ��ֵ
    	double items=-100;
    	HashMap<Integer,Double> userSim=new HashMap<Integer,Double>();
    	for(int i=0;i<a.length;i++) {
    		if(u!=i) userSim.put(i,sim(u,i));
    	}	
    	for(int k=0;k<userNumber;k++) {
    		double uScore=-100;int uKey=-1;
    		 for(Entry<Integer, Double> entry: userSim.entrySet()) {
    			 if(entry.getValue()>uScore) {uScore=entry.getValue();uKey=entry.getKey();}
    		 }
    		 userCon.put(uKey, userSim.remove(uKey)) ;
    		 users[k]=uKey;
    	}
    	return users;
    }
    public int getItems(int u,int []missItems)
    {
    	int users[]=getSimUsers(u);
    	HashMap<Integer,Double> uitem=new HashMap<Integer,Double>();
    	for(int i=0;i<missItems.length;i++) {
    		double ta=0,tb=0,uAver=aver(u);
    		for(int j=0;j<userNumber;j++)
    		{
    			ta+=userCon.get(users[j])*(a[users[j]][missItems[i]]-aver(users[j]));    			
    			tb+=Math.abs(userCon.get(users[j]));			
    		}
    		uitem.put(missItems[i], uAver+ta/tb); // means the score missing item  represent
    	}
    	int getItem=-1;double maxScore=-100;
    	//���Ҫ�ı��Ƽ����������޸�����
    	//for����
    	for(Entry<Integer, Double> entry: uitem.entrySet() )
    	{
    		if(entry.getValue()>maxScore) {maxScore=entry.getValue();getItem=entry.getKey();}
    	}
    	return getItem;	
    }
}
