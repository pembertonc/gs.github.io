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

import com.techmine.gs.ui.pages.BasePage;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;

/**
 *
 * @author bethy
 */
public class OwnerPage extends BasePage {

    private Object owner;
    private TextField name;
    private TextField street1;
    private TextField street2;
    private TextField city;
    private TextField email;
    private TextField telephone1;
    private TextField telephone2;
    private TextField firstName;
    private TextField familyName;
    private TextField otherName;

    private Form<Void> form;

    private AjaxFallbackButton save;
    private AjaxFallbackButton cancel;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        initializeForm();
        add(form);

        initializeOwner();
        form.add(owner);

        initializeName();
        form.add(name);
        initializeStreet1();
        form.add(street1);
        initializeStreet2();
        form.add(street2);
        initializeCity();
        form.add(city);
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
    }

    private void initializeForm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initializeOwner() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initializeName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initializeStreet1() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initializeStreet2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initializeCity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initializeTelephone1() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initializeTelephone2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initializeEmail() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initializeFirstName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initializeFamilyName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initializeOtherName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
