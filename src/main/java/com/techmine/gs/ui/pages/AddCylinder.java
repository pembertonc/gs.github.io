/*
 * Copyright 2021 elesha.
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

import com.techmine.gs.dto.CylinderDTO;
import java.util.Arrays;
import java.util.List;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
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

    public List<String> getGasTypes() {
        return gasTypes;
    }

    public void setGasTypes(List<String> gasTypes) {
        this.gasTypes = gasTypes;
    }

    private AjaxFallbackButton add;
    private AjaxFallbackButton cancel;

    private CylinderDTO cylinderDTO;
    private List<String> gasTypes;

    public AddCylinder() {
        cylinderDTO = new CylinderDTO();
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

        initializeCylinderGasType();
        form.add(cylinderGasType);
        initializeCylinderCapacity();
        form.add(cylinderCapacity);
        //initializeOwner();


        initializeAdd();
        form.add(add);

        initializeSerialNo();
        form.add(serialNumber);
        initializeCancel();
        form.add(cancel);


    }

    private void initializeForm() {
        // create an instance of form and assign it to form.
        form = new Form("form", Model.of(cylinderDTO));
    }

    private void initializeCylinderGasType() {
        // create an instance of DropDownChoice and assign it to cylinderGasType.
        gasTypes = Arrays.asList("Carbon Dioxide","Oxygen","Argon","Dry Air","Helium","Nitrous Oxide","Nitrogen");
        cylinderGasType = new DropDownChoice("cylinderGasType",LambdaModel.of(cylinderDTO::getGasType, cylinderDTO::setGasType),gasTypes);
    }

    private void initializeCylinderCapacity() {
        cylinderCapacity = new DropDownChoice("cylinderCapacity", PropertyModel.of(cylinderDTO, "cylinderCapacity"),
                Arrays.asList("40", "60", "80"));
    }

    private void initializeOwner() {
        // create an instance of DropDownChoice and assign it to cylinderOwner.
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initializeSerialNo() {
        // create an instance of TextField and assign it to serialNo.
        serialNumber = new TextField("serialNumber", PropertyModel.of(cylinderDTO, "serialNumber")) {
        };
        //To change body of generated methods, choose Tools | Templates.
    }

    private void initializeAdd() {

        add = new AjaxFallbackButton("add", form){};

       

        // create an instance of AjaxFallBackButton and assign it to add.
    }

    private void initializeCancel() {
        cancel = new AjaxFallbackButton("cancel", form) {
        };
    }

}
