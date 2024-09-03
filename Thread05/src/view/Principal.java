package view;

import controller.ThreadPing;

public class Principal 
{
	public static void main(String[] args) 
	{
		for(int i=0;i<4;i++)
		{
		ThreadPing ping = new ThreadPing(i);
		ping.start();
		}
	}

}
