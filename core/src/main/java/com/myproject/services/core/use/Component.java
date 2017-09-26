package com.myproject.services.core.use;

import com.adobe.cq.sightly.WCMUse;

public class Component extends WCMUse {
	 private String foo;
	 @Override
	 public void activate() throws Exception {
	 foo = "Hello World";
	 }
	 public String getFoo() {
	 return foo;
	 }
	}