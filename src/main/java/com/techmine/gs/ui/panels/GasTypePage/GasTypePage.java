/*
 * Copyright 2022 User.
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
package com.techmine.gs.ui.panels.GasTypePage;
 
//import com.techmine.gs.domain.institution;

import org.apache.wicket.markup.html.form.TextField;
import org. apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.model.IModel;
import org.apache.wicket.markup.html.form.Form;
 

/**
 *
 * @author User
 */
public class GasTypePage extends Panel {
    private AjaxFallbackButton save;
    private AjaxFallbackButton cancel;
    private AjaxFallbackButton edit;
    
    
    //private Form form; 
    // To be added 
    
    public GasTypePage(String id, IModel<?> model) {
        super(id, model);
    }
    
    private TextField gasName;
    private TextField symbol;
    
    
    private void initializegasName() {
        this.gasName = new TextField("gasName","));
    } 
    
    private void initializesymbol() {
        this.gasName = new TextField("symbol","));
    }
    
    
    
    private void initializeSave() {
        save = new AjaxFallbackButton("save", form) {
        };
    }

    private void initializeCancel() {
        cancel = new AjaxFallbackButton("cancel", form) {
        };
    } 
    
    private void initializeEdit() {
        edit = new AjaxFallbackButton("edit", form) {
        };
    } 
}
