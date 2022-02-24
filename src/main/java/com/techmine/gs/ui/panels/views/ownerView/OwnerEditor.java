/*
 * Copyright 2022 bethy.
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
package com.techmine.gs.ui.panels.views.ownerView;

import com.techmine.gs.domain.Institution;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author bethy
 */
public class OwnerEditor extends Panel {

    public OwnerEditor(String id) {
        this(id, Model.of(new Institution()));
    }

    public OwnerEditor(String id, IModel<?> model) {
        super(id, model);
    }
    
    
    
    
    private Institution owner;
    private TextField institutionName;
    private TextField street1;
    private TextField street2;
    private TextField country;
    private TextField city;
    private TextField email;
    private TextField telephone1;
    private TextField telephone2;
    private TextField firstName;
    private TextField familyName;
    private TextField otherName;

    private Form<Institution> form;

    private AjaxFallbackButton save;
    private AjaxFallbackButton cancel;

    /**
     *
     */
    @Override
    protected void onInitialize() {
        super.onInitialize();
       

        // owner = Objects.requireNonNullElseGet(owner, Institution::new);
       
       
        add(form = new Form<>("form", (IModel<Institution>) getDefaultModel()));
        
        Institution institution = (Institution) getDefaultModelObject();
        form.add(initializeInstitutionName("institutionName", LambdaModel.of(institution::getName, institution::setName), true));
        

        initializeStreet1();
        form.add(new TextField("street1").setRequired(true));

        initializeStreet2();
        form.add(street2);

        initializeCity();
        form.add(new TextField("city").setRequired(true));

        initializeCountry();
        form.add(new TextField("country").setRequired(true));

        initializeTelephone1();
        form.add(new TextField("telephone1").setRequired(true));

        initializeTelephone2();
        form.add(telephone2);

        initializeEmail();
        form.add(email);

        initializeFirstName();
        form.add(new TextField("firstName").setRequired(true));

        initializeFamilyName();
        form.add(new TextField("familyName").setRequired(true));

        initializeOtherName();
        form.add(otherName);

        initializeSave();
        form.add(save);

        initializeCancel();
        form.add(cancel);

    }

    private void initializeStreet1() {
        this.street1 = new TextField("street1", PropertyModel.of(owner, "contact:address:street1"));
    }

    private void initializeStreet2() {
        this.street2 = new TextField("street2", PropertyModel.of(owner, "contact:address:street2"));
    }

    private void initializeCity() {
        this.city = new TextField("city", PropertyModel.of(owner, "contact:address:city"));
    }

    private void initializeTelephone1() {
        this.telephone1 = new TextField("telephone1", PropertyModel.of(owner, "contact:person:telephone1"));
    }

    private void initializeTelephone2() {
        this.telephone2 = new TextField("telephone2", PropertyModel.of(owner, "contact:person:telephone2"));
    }

    private void initializeEmail() {
        this.email = new TextField("email", PropertyModel.of(owner, "contact:person:email"));
    }

    private void initializeFirstName() {
        this.firstName = new TextField("firstName", PropertyModel.of(owner, "contact:person:firstName"));
    }

    private void initializeFamilyName() {
        this.familyName = new TextField("familyName", PropertyModel.of(owner, "contact:person:familyName"));
    }

    private void initializeOtherName() {
        this.otherName = new TextField("otherName", PropertyModel.of(owner, "contact:person:otherName"));
    }

    private TextField initializeInstitutionName(String id, IModel<String> model, boolean isRequired) {
        return (TextField) new TextField(id, model).setRequired(true);
        
    }

    private void initializeCountry() {
        this.country = new TextField("country", PropertyModel.of(owner, "contact:address:country"));
    }

    private Form<Institution> initializeForm(String id, IModel<Institution> model) {
        return new Form<Institution>(id, model);
    }

    private void initializeSave() {
        save = new AjaxFallbackButton("save", form) {
        };
    }

    private void initializeCancel() {
        cancel = new AjaxFallbackButton("cancel", form) {
        };
    }

}
