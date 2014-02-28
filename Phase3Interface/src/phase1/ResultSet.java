package phase1;

import java.util.ArrayList;

public class ResultSet
{
	private ArrayList<Result> resultSet;
	public ResultSet()
	{
		resultSet=new ArrayList<Result>();
	}
	public void addResult(Result result)
	{
		resultSet.add(result);
	}
	public void sort()
	{
		for(int i=0;i<resultSet.size();i++)
		{
			for(int j=(i+1);j<resultSet.size();j++)
			{
				if(resultSet.get(i).getDistance()<resultSet.get(j).getDistance())
				{
					Result temp1=resultSet.get(i);
					Result temp2=resultSet.get(j);
					resultSet.set(i, temp2);
					resultSet.set(j, temp1);
				}
			}
		}
	}
	public void rsort()
	{
		for(int i=0;i<resultSet.size();i++)
		{
			for(int j=(i+1);j<resultSet.size();j++)
			{
				if(resultSet.get(i).getDistance()>resultSet.get(j).getDistance())
				{
					Result temp1=resultSet.get(i);
					Result temp2=resultSet.get(j);
					resultSet.set(i, temp2);
					resultSet.set(j, temp1);
				}
			}
		}
	}
	public void printTop10()
	{
		sort();
		for(int i=0;i<resultSet.size()&&i<5;i++)
		{
			System.out.println(resultSet.get(i));
		}
	}
	public Result getResult(int index)
	{
		return resultSet.get(index);
	}
	public void printTop101()
	{
		rsort();
		for(int i=0;i<resultSet.size()&&i<5;i++)
		{
			System.out.println(resultSet.get(i));
		}
	}
}