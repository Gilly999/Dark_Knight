package com.myproject.services.core.use;

import java.util.HashMap;
import com.adobe.cq.sightly.WCMUse;

public class MapIte extends WCMUse {

	HashMap<Integer,String> hm=new HashMap<Integer, String>();
	@Override
	public void activate() throws Exception {
		// TODO Auto-generated method stub
		//HashMap<Integer,String> hm=new HashMap<Integer, String>();
		hm.put(1, "Manikanta");
		hm.put(2,"Revanth");
		hm.put(3,"kolusu");
		hm.put(4,"MAnikanta");
		
	}
	
		public  HashMap<Integer, String> sighIte()
		{
		return hm;	
		}


}