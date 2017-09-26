package com.myproject.services.core.use;
  
import com.adobe.cq.sightly.WCMUse;
  
public class Info extends WCMUse {
    private String lowerCaseTitle;
    private String lowerCaseDescription;
    private String Path;
    @Override
    public void activate() throws Exception {
        
    	//Path
    	Path=getCurrentPage().getPath();
    	//lowerCaseTitle = getProperties().get("title", "").toLowerCase();
        //lowerCaseDescription = getProperties().get("description", "").toLowerCase();
    }
  
    public String getLowerCaseTitle() {
        return lowerCaseTitle;
    }
  
    public String getLowerCaseDescription() {
        return lowerCaseDescription;
    }
    
    public String getPath(){
    	return Path;
    }
}