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

/**
 *
 * @author CodeCamp4
 */
public class MainPage extends BasePage{
   private Form<?> form;
    @Override
    protected void onInitialize() {
        super.onInitialize(); 
        add(initializeForm());
        form.add(initializeCheckInCylinderCtrl());
        form.add(initializeBrowseInventoryCtrl());
        // to implement the rest do 
        // eg. form.add(intializeCheckOutCylinderCtrl());
        // implment the method initializeCheckinCylinderCtrl();
        
    }

    private Button initializeAddCustomerCtrl(){
        return null;
    }
    
    private Button initlaizeAddCustomerCtrl(){
        return null;
    }
    
   
    
    
    private Button initializeCheckInCylinderCtrl() {
        AjaxFallbackButton button = new AjaxFallbackButton("checkInCylinder", form){
            @Override
            protected void onSubmit(Optional<AjaxRequestTarget> target) {
                super.onSubmit(target); //To change body of generated methods, choose Tools | Templates.
                setResponsePage(CylinderCheckInPage.class);
            }
        };
        return button;
    }

    private Form initializeForm() {
       return form = new Form<>("actionForm");
    }

    private Button initializeBrowseInventoryCtrl(){
        AjaxFallbackButton button = new AjaxFallbackButton("browseInventory", form) {
            @Override
            protected void onSubmit(Optional<AjaxRequestTarget> target) {
                super.onSubmit(target); 
                setResponsePage(InventoryBrowser.class);
            }
            
           
        };
       return button;

    }
    
    
    
}
