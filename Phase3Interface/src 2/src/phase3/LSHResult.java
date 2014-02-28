package phase3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

public class LSHResult {
	LinkedHashMap<Long,ArrayList<Integer>> gHashTable;
	ArrayList<double []> randomVectors;

	LSHResult(LinkedHashMap<Long,ArrayList<Integer>> gHashTable, ArrayList<double []> randomVectors)
	{
		this.gHashTable=gHashTable;
		this.randomVectors=randomVectors;
	}
	@SuppressWarnings("unchecked")
	public long getQueryIndexLocation()
	{
		boolean status=false;
		long indexPoint=0;
		Collection<ArrayList<Integer>> c=gHashTable.values();
		Iterator<ArrayList<Integer>> itr=c.iterator();
		ArrayList<Integer> list=null;
		while(itr.hasNext())
		{
			list=(ArrayList<Integer>) itr.next();
			if(list.contains(99999))
			{
				status=true;
			//	System.out.println(status);
				break;
			}
		}
		Set<Entry<Long, ArrayList<Integer>>> set=gHashTable.entrySet();
		Iterator<Entry<Long, ArrayList<Integer>>> itr2=set.iterator();
		while(itr2.hasNext())
		{
			Entry<Long, ArrayList<Integer>> e=(Entry<Long, ArrayList<Integer>>)itr2.next();
			if(e.getValue().equals(list))
			{
				indexPoint=(long) e.getKey();			
			}
		}
		//System.out.println(indexPoint);
		return indexPoint;
	}
	public ArrayList<Integer> getQueryNeighbors()
	{
		ArrayList<Integer> allElements=new ArrayList<Integer>();
		int target=-1;
		long indextPoint=getQueryIndexLocation();
	   Set<Long> s=	gHashTable.keySet();
	   Iterator itr=s.iterator();
	   ArrayList<Long> temp=new ArrayList<Long>();
	   while(itr.hasNext())
	   {
		
		   temp.add((long)itr.next()); 
	   }
	  target= temp.indexOf(indextPoint);
	  allElements.addAll(gHashTable.get(temp.get(target)));
	  if(target!=temp.size()-1)
	  allElements.addAll(gHashTable.get(temp.get(target+1)));
	  if(target>0)
	  allElements.addAll(gHashTable.get(temp.get(target-1)));
	  System.out.println(allElements);
	  return allElements;
	}
	public ArrayList<Integer> getQueryNeighbors1(int i)
	{
		ArrayList<Integer> allElements=new ArrayList<Integer>();
		int target=-1;
		long indextPoint=getQueryIndexLocation();
	   Set<Long> s=	gHashTable.keySet();
	   Iterator<Long> itr=s.iterator();
	   ArrayList<Long> temp=new ArrayList<Long>();
	   while(itr.hasNext())
	   {
		
		   temp.add((long)itr.next()); 
	   }
	  target= temp.indexOf(indextPoint);
	  allElements.addAll(gHashTable.get(temp.get(target)));
	  
	  
	  int count =1;
	  while(count<=i)
	  {
		  if(target+count<temp.size())
			  allElements.addAll(gHashTable.get(temp.get(target+count)));
			  if(target-count>=0)
			  allElements.addAll(gHashTable.get(temp.get(target-count)));
			  count++;
		  
	  }
	  
	  
	  
	  System.out.println(allElements);
	  return allElements;
	  
	  
	}

}
