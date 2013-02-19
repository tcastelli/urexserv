package com.urexst.ui;

import com.urexst.URexst;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class AdminLoginView  extends Panel implements View {

    public static final String NAME = "login";

    public AdminLoginView(final Navigator navigator,
            final String fragmentAndParameters) {
    	super.setSizeFull();
    	HorizontalLayout holder = new HorizontalLayout();
    	holder.setSizeFull();
    	VerticalLayout layout = new VerticalLayout();
    	layout.setSpacing(true);
    	    	

        final TextField email = new TextField("Email");
        layout.addComponent(email);
        layout.setComponentAlignment(email, Alignment.MIDDLE_CENTER);

        final PasswordField password = new PasswordField("Password");
        layout.addComponent(password);
        layout.setComponentAlignment(password, Alignment.MIDDLE_CENTER);

        final Button login = new Button("Login", new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
               

                if (email.getValue().equals("admin")&& password.getValue().equals("admin")){
                
                // indicate the user is logged in
                ((URexst)UI.getCurrent()).setLoggedInUser(email.getValue());

                // navigate back to the intended place
                navigator.navigateTo(fragmentAndParameters);
                }
                else{
                	Notification.show("Incorrect user or password", Type.ERROR_MESSAGE);
                }
            }
        });
        layout.addComponent(login);
        layout.setComponentAlignment(login, Alignment.MIDDLE_CENTER);
        holder.addComponent(layout);
        holder.setComponentAlignment(layout, Alignment.MIDDLE_CENTER);
       
        setContent(holder);

    }
   
    @Override
    public void enter(ViewChangeEvent event) {
        // TODO Auto-generated method stub
        
    }
}