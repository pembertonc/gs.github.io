/*
 * Copyright 2021 elesh.
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
package com.techmine.gs.ui.pages;

import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 *
 * @author elesh
 */
public class AddCylinder extends BasePage {

    private Form<Void> form;
    private TextField serialNumber;
    private DropDownChoice cylinderGasType;
    private DropDownChoice cylinderCapacity;
    private DropDownChoice cylinderOwner;

    private AjaxFallbackButton add;
    private AjaxFallbackButton cancel;

    public AddCylinder() {
    }

    public AddCylinder(IModel<?> model) {
        super(model);
    }

    public AddCylinder(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize(); //To change body of generated methods, choose Tools | Templates.

        initializeForm();
        add(form);
        
        //initializeCylinderGasType();
        // initializeCylinderCapacity();
        //initializeOwner();
        // initializeSerialNo();
        initializeAdd();
        form.add(add);
        //initializeCancel();
    }

    private void initializeForm() {
        // create an instance of form and assign it to form.
      form =  new Form<>("form");
    }

    private void initializeCylinderGasType() {
        // create an instance of DropDownChoice and assign it to cylinderGasType.
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initializeCylinderCapacity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initializeOwner() {
        // create an instance of DropDownChoice and assign it to cylinderOwner.
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initializeSerialNo() {
        // create an instance of TextField and assign it to serialNo.
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initializeAdd() {
        add = new AjaxFallbackButton("add", form){};
        // create an instance of AjaxFallBackButton and assign it to add.
    }

    private void initializeCancel() {
        // create an instance of AjaxFallBackButton and assign it to cancel.
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
