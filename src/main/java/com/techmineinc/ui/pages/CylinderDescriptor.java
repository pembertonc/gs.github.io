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
package com.techmineinc.ui.pages;

import java.util.Optional;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;

/**
 *
 * @author CodeCamp4
 */
public class CylinderDescriptor extends BasePage{

    Form<?> form;
    
    @Override
    protected void onInitialize() {
        super.onInitialize();
        form = initializeForm();
        add(form);
        form.add(initializeSerialNo());
        form.add(initializeGasType());
        form.add(initializeOwner());
        form.add(initializeDateReturned());
        form.add(initializeCylinderCapacity());
        form.add(initializeVerificationPin());
        form.add(initializeTelephone());
        form.add(initializeSubmit());
    }

    private Form<?> initializeForm() {
        return form = new Form("checkInCylinderForm");
    }

    private TextField initializeSerialNo() {
        return new TextField("serialNo");
    }

    private TextField initializeGasType() {
        return new TextField("gasType");
    }

    private TextField initializeOwner() {
        return new TextField("owner");
    }

    private TextField initializeDateReturned() {
        return new TextField("dateReturned");
    }

    private TextField initializeCylinderCapacity() {
        return new TextField("cylinderCapacity");
    }

    private TextField initializeVerificationPin() {
return new TextField("verificationPin");
    }

    private TextField initializeTelephone() {
       return new TextField("telephoneNo");
    }

    private Button initializeSubmit() {
        return new AjaxFallbackButton("submit", form){
            @Override
            protected void onSubmit(Optional<AjaxRequestTarget> target){
                
            }

            @Override
            protected void onError(Optional<AjaxRequestTarget> target) {
                super.onError(target); //To change body of generated methods, choose Tools | Templates.
            }
            
        };
    }

    
    
    
    
}
