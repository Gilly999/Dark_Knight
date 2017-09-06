package com.myproject.services.core.use;

import org.apache.sling.api.resource.Resource;

import com.adobe.cq.sightly.WCMUse;
//import com.adobe.cq.sightly.WCMUsePojo;

public class MyComponent extends WCMUse {

    private String value;

    @Override
    public void activate() {
       // Convenience methods allow to access the default bindings
       Resource resource = getResource();

       // Parameters are can be accessed via a get() method
       String param1 = get("param1", String.class);
       String param2 = get("param2", String.class);

       value = resource.getPath() + param1 + param2;
    }

    public String getCalculatedValue() {
        return value;
    }
}