/*
 * Copyright 2021 CodeCamp4.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.techmine.ui.pages;

import java.util.Optional;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author CodeCamp4
 */
public class LandingPage extends BasePage{
    Form<?> form;
    String userName;
    String password;
    @Override
    protected void onInitialize() {
        super.onInitialize(); 
        form = initializeForm();
        add(form);
      
        form.add(initializeUserName());
        form.add(initializePassword());
       form.add(initializeSignInButton());


    }

    private Button initializeSignInButton(){
        return new AjaxFallbackButton("signIn", form){
            @Override
            protected void onSubmit(Optional<AjaxRequestTarget> target) {
                super.onSubmit(target); 
                if(signIn()){
                    setResponsePage(MainPage.class);
                }

            }
            
        };
    }
    
    private boolean signIn(){
        return userName.equals(password);
    }
    private TextField initializePassword(){
        return new PasswordTextField("password", PropertyModel.of(this, "password"));
    }
    
    private Form<?> initializeForm() {
        return new Form<>("signInForm", CompoundPropertyModel.of(this));
    }

    private TextField initializeUserName() {
        return new TextField("userName", PropertyModel.of(this, "userName"));
    }
    
    
    
}
