package com.myproject.services.core.use;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;
import com.adobe.xmp.schema.rng.model.Context;
import com.myproject.services.core.impl.CommonReferenceServiceImpl;
import com.sun.javafx.scene.control.skin.ContextMenuSkin;

public class PassingParameters extends WCMUse {

	BundleContext context;
	String reverseText;
	@Override
	public void activate() throws Exception {
		// TODO Auto-generated method stub
		Logger log=LoggerFactory.getLogger(PassingParameters.class);
		
		String text = get("text", String.class);
        reverseText = new StringBuffer(text).reverse().toString();
		
        acti(context);
	}
	public String getReverseText() {
        //return reverseText;
		return reverseText;
        
    }
 

	public Bundle[] acti(BundleContext context)
	{
		Logger log1=LoggerFactory.getLogger(PassingParameters.class);
		Bundle bundle=context.getBundle();
		log1.debug("The Bundle id is :" + bundle);
		Bundle[] bundle1=context.getBundles();
		log1.debug("Returns an array : "+bundle1);
		return bundle1;
		
	}
	
	
	
	
	
	
	
}
