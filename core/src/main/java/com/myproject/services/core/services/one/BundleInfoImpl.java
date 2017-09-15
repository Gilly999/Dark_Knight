/*package com.myproject.services.core.services.one;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Modified;
import org.apache.felix.scr.annotations.Service;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.Version;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.deploymentadmin.BundleInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 

 
@Component(immediate = true)
@Service(BundleInfo.class)
public class BundleInfoImpl implements BundleInfo {
   private static final Logger LOG = LoggerFactory.getLogger(BundleInfoImpl.class);
   private static BundleContext bundleContext;
 
   public void getBundleInfo(BundleContext bundleContext) {
       //Fetching bundles
       Bundle[] bundles = bundleContext.getBundles();
 
       for (Bundle bundle : bundles) {
           //Bundle details
           LOG.info("State: " + bundle.getState());
           LOG.info("Symbolic name: " + bundle.getSymbolicName());
           LOG.info("Version:" + bundle.getHeaders().get(Constants.BUNDLE_VERSION).toString());
           LOG.info("Imported Packages:" + bundle.getHeaders().get(Constants.IMPORT_PACKAGE));
 
           extractServicesInfo(bundle);
       }
   }
 
   // Fetching ServiceReferernces exposed by the bundle
   public static void extractServicesInfo(Bundle bundle) {
       ServiceReference[] registeredServices = bundle.getRegisteredServices();
       if (registeredServices != null) {
           for (ServiceReference registeredService : bundle.getRegisteredServices()) {
               // Fetching any property of the Service
               LOG.info("service.pid: " + registeredService.getProperty("service.pid"));
 
               // Fetch Service from ServiceReference
               LOG.info("Service: " + bundleContext.getService(registeredService));
           }
       }
   }
 
   @Activate
   @Modified
   protected void activate(ComponentContext cc) {
       bundleContext = cc.getBundleContext();
       getBundleInfo(bundleContext);
   }

@Override
public String getSymbolicName() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Version getVersion() {
	// TODO Auto-generated method stub
	return null;
}
}*/