package com.myproject.services.core.use;

import com.adobe.cq.sightly.WCMUse;

public class PassingParameters extends WCMUse {

	
	String reverseText;
	@Override
	public void activate() throws Exception {
		// TODO Auto-generated method stub
		String text = get("text", String.class);
        reverseText = new StringBuffer(text).reverse().toString();
		
	}
	public String getReverseText() {
        return reverseText;
    }
 

}


/*
package apps.my_example.components.info;


    ...
 
    private String reverseText;
     
    @Override
    public void activate() throws Exception {
 
        ...
         
        String text = get("text", String.class);
        reverseText = new StringBuffer(text).reverse().toString();
 
    }
  
    public String getReverseText() {
        return reverseText;
    }
 
    ...
}
*/