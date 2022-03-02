/*
 * Copyright 2021 bethy.
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
package com.techmine.gs.ui.pages.ownerpage;
import com.techmine.gs.domain.Institution;

import com.techmine.gs.ui.pages.authenticatedbasepage.AuthenticatedBasePage;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author bethy
 */
public class OwnerPage extends AuthenticatedBasePage {

    private Institution owner;
    private FormComponent institutionName;
    private FormComponent street1;
    private FormComponent street2;
    private FormComponent country;
    private FormComponent city;
    private FormComponent email;
    private FormComponent telephone1;
    private FormComponent telephone2;
    private FormComponent firstName;
    private FormComponent familyName;
    private FormComponent otherName;

    private Form<Institution> form;

    private AjaxFallbackButton save;
    private AjaxFallbackButton cancel;

    /**
     *
     */
    @Override
    protected void onInitialize() {
        super.onInitialize();
        if (owner == null) {
            owner = new Institution();
        }

        // owner = Objects.requireNonNullElseGet(owner, Institution::new);
        initializeForm();
        add(form);

        initializeInstitutionName();
        form.add(institutionName);

        initializeStreet1();
        form.add(street1);

        initializeStreet2();
        form.add(street2);

        initializeCity();
        form.add(city);

        initializeCountry();
        form.add(country);

        initializeTelephone1();
        form.add(telephone1);

        initializeTelephone2();
        form.add(telephone2);

        initializeEmail();
        form.add(email);

        initializeFirstName();
        form.add(firstName);

        initializeFamilyName();
        form.add(familyName);

        initializeOtherName();
        form.add(otherName);

        initializeSave();
        form.add(save);

        initializeCancel();
        form.add(cancel);

    }

    private void initializeStreet1() {
        this.street1 = new TextField("street1", LambdaModel.of(owner.getAddress()::getStreet1))
                .setRequired(true);
        /*
        this.street1 = new TextField("street1", PropertyModel
        .of(owner, "address.street1"))
        .setRequired(true);*/
    }

    private void initializeStreet2() {
        this.street2 = new TextField("street2", LambdaModel.of(owner.getAddress()::getStreet2));
        /*
        this.street2 = new TextField("street2", PropertyModel.of(owner, "address.street2"));
         */
    }

    private void initializeCity() {
        this.city = new TextField("city", LambdaModel.of(owner.getAddress()::getCity))
                .setRequired(true);

        /*
//        this.city = new TextField("city", PropertyModel
        .of(owner, "address.city"))
        .setRequired(true);
         */
    }

    private void initializeTelephone1() {
        this.telephone1 = new TextField("telephone1", PropertyModel
                .of(owner, "contact.telephone1"))
                .setRequired(true);
    }

    private void initializeTelephone2() {
        this.telephone2 = new TextField("telephone2", PropertyModel.of(owner, "contact.telephone2"));
    }

    private void initializeEmail() {
        this.email = new TextField("email", PropertyModel.of(owner, "contact.email"));
    }

    private void initializeFirstName() {
        this.firstName = new TextField("firstName", PropertyModel
                .of(owner, "contact.person.firstName"))
                .setRequired(true);
    }

    private void initializeFamilyName() {
        this.familyName = new TextField("familyName", PropertyModel
                .of(owner, "contact.person.familyName"))
                .setRequired(true);
    }

    private void initializeOtherName() {
        this.otherName = new TextField("otherName", PropertyModel.of(owner, "contact.person.otherName"));
    }

    private void initializeInstitutionName() {
        this.institutionName = new TextField("institutionName", LambdaModel.of(owner::getName, owner::setName)).setRequired(true);
    }

    private void initializeCountry() {
        this.country = new TextField("country", PropertyModel
                .of(owner, "address.country"))
                .setRequired(true);
    }

    private void initializeForm() {
        this.form = new Form<>("form", CompoundPropertyModel.of(owner));
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
