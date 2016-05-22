package com.setmore.minions.dao;

import java.util.logging.Logger;

import javax.jdo.PersistenceManager;

import com.setmore.minions.jdo.PMF;

public class SampleDao
{
	private static Logger log=Logger.getLogger(SampleDao.class.getName());
	
	public void getGCSDetailByGSKey(String strGSKey) throws Exception 
	{

		PersistenceManager pm=null;
		
		try
		{
			pm=PMF.get().getPersistenceManager();
		}
		catch(Exception e)
		{
			log.warning("Exception in getGCSDetailByGSKey : "+e);
			e.printStackTrace();
		}
		finally
		{
			pm.close();
		}
	
	}
}
