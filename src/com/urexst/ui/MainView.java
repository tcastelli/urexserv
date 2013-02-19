package com.urexst.ui;

import com.urexst.URexst;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class MainView extends Panel implements View {

	private static final long serialVersionUID = 4330479695770603501L;

	public static final String NAME = "";
    
    private Button logOut;
    private AddUsersView addform;
   

    public MainView(final Navigator navigator) {
    	
    	super.setSizeFull();
        HorizontalLayout holder = new HorizontalLayout();
        holder.setSizeFull();
    	VerticalLayout layout = new VerticalLayout();
    	layout.setSpacing(true);
    	
    	

        logOut = new Button("Logout", new Button.ClickListener() {
          
			private static final long serialVersionUID = 7004614451726612742L;

		@Override
        	public void buttonClick(ClickEvent event) {
            	((URexst)UI.getCurrent()).setLoggedInUser(null);
            	navigator.navigateTo(AdminLoginView.NAME);
                
            }
        });
        layout.addComponent(logOut);
        layout.setComponentAlignment(logOut, Alignment.MIDDLE_CENTER);
        
        
        addform = new AddUsersView();
        layout.addComponent(addform);
        layout.setComponentAlignment(addform, Alignment.MIDDLE_CENTER);
        
        
        
        holder.addComponent(layout);
        holder.setComponentAlignment(layout, Alignment.MIDDLE_CENTER);
        
        
        setContent(holder);
    }

    @Override
    public void enter(ViewChangeEvent event) {
               
    }
}