package com.myproject.services.core;

import org.apache.sling.api.resource.ResourceResolver;


import com.day.cq.search.result.SearchResult;
import com.myproject.services.core.SearchPOJO;



/**
 * 
 *
 *
 */
public interface SearchService {
	public SearchResult getSearchResult(SearchPOJO pojo, ResourceResolver resolver);
}