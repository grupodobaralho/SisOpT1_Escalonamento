//package catandoDaNet;
//
///**********************************************************
// *  JAVA Code for preemptive sjf(Shortest job First)      *
// *  scheduling Algorithm used in O.S.                     * 
// *                                                        *
// *                                                        *
// * ********************************************************/
//
//
//import java.util.*;
//public class presjf{
//	
//	public static void main(String args[]){
//		int a,b,i,x;
//		System.out.println("Enter the no. of processes");
//		Scanner sc=new Scanner(System.in);
//		int n = sc.nextInt(); 
//		process p[]=new process[n];
//		for(i=0;i<n;i++){
//			p[i]=new process();
//		}
//		System.out.println("Enter the processes in the form CPU_Burst Arrival_time");
//		for(i=0;i<n;i++){
//			System.out.println("Enter Process "+(i+1));
//			a=sc.nextInt();
//			b=sc.nextInt();
//			p[i].setData(a,b);
//			if(i>0 && p[i-1].getAt()>p[i].getAt()){
//				System.out.println("Process is invalid re-enter");
//				i--;
//			}
//		}	
//		int t=0,r,temp,z;
//		i=0;
//		while(i!=n){
//			r=-1;
//			for(z=0;z<n;z++){
//				if(p[z].getAt()<=t&&!p[z].isComplete()){
//					if(r==-1)
//						r=z;
//					 if(p[r].getCpuBurst()>p[z].getCpuBurst())
//					{
//					r=z;
//					}		
//}		
//		
//		
//
//			}
//			System.out.println("selected: "+r);
//			if(r!=-1){		
//				p[r].setCpuBurst(p[r].getCpuBurst()-1);		
//				p[r].setTat(p[r].getTat()+1);
//				if(p[r].getCpuBurst()==0){
//				p[r].setComplete(true);
//				i++;}t++;
//			}
//			for(x=0;x<n;x++)
//			{
//				if(x!=r&&!p[x].isComplete()&&p[x].getAt()<t){
//				p[x].setWt(p[x].getWt()+1);
//				p[x].setTat(p[x].getTat()+1);
//				}
//			}
//			
//		}
//		System.out.println("WT \t TAT");
//		for(i=0;i<n;i++)
//			System.out.println(p[i].getWt()+" \t "+p[i].getTat());
//	}
//	
//}
