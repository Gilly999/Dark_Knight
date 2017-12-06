package com.myproject.service.json;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageInfoProvider;

public class Json_Data implements PageInfoProvider {

	@Override
	public void updatePageInfo(SlingHttpServletRequest request, JSONObject jsonObject, Resource resource) throws JSONException {
		// TODO Auto-generated method stub
		
		Page page = resource.adaptTo(Page.class);
        JSONObject urlinfo = new JSONObject();
        urlinfo.put("publishURL", fetchExternalUrl(null,page.getPath()));
        jsonObject.put("URLs",urlinfo);
	}

}
