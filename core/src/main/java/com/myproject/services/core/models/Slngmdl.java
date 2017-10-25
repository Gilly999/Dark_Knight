package com.myproject.services.core.models;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.settings.SlingSettingsService;

@Model(adaptables = Resource.class)
public class Slngmdl {
	private String message;
	@Inject 
	public String s1;
	@Inject 
	public String s2;
	@Inject 
	public String s3;
    @Inject
    private SlingSettingsService settings;
    @PostConstruct
    protected void init() {
        message = settings.getSlingId();
    }
    public String getMessage() {
        return message;
    }
}
