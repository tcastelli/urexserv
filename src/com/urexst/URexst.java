package com.urexst;

import com.urexst.ui.AdminLoginView;
import com.urexst.ui.MainView;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * Main UI class
 */
@SuppressWarnings("serial")
@PreserveOnRefresh
public class URexst extends UI {

	
		private Navigator navigator;

	    private String loggedInUser = null;

	    @Override
	    public void init(VaadinRequest request) {
	        // Create Navigator, make it control the ViewDisplay
	        navigator = new Navigator(this, this);

	        // Add some Views
	        // no fragment for main view
	        navigator.addView(MainView.NAME, new MainView(navigator));
	      
	        
	        // #login will navigate to the main view if invoked via this mechanism
	        navigator.addView(AdminLoginView.NAME, new AdminLoginView(navigator,
	                MainView.NAME));
	        

	        // we'll handle permissions with a listener here, you could also do
	        // that in the View itself.
	        navigator.addViewChangeListener(new ViewChangeListener() {

	            @Override
	            public boolean beforeViewChange(ViewChangeEvent event) {
	            	if (((URexst)UI.getCurrent()).getLoggedInUser() == null) {
	            		// Show to LoginView instead, pass intended view
//	            		String fragmentAndParameters = event.getViewName();
//	            		if (event.getParameters() != null) {
//	            			fragmentAndParameters += "/";
//	            			fragmentAndParameters += event.getParameters();
//	            		}
	            		navigator.getDisplay().showView(new AdminLoginView(navigator,
	            				""));
	            		return false;

	            	} else {
	            		         		
	            		
	            		return true;
	            	}
	              }
	                  
	              @Override
	              public void afterViewChange(ViewChangeEvent event) {
	            	  
	            	  
	            	  
	            	  

	              }          
	        });
	    }

	    public String getLoggedInUser(){ return loggedInUser; } 
	    public void setLoggedInUser(String user){ loggedInUser = user; }
		
	

}