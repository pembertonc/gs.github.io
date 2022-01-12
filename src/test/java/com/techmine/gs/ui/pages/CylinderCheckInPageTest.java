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
package com.techmine.gs.ui.pages;

import com.techmine.gs.ui.pages.CylinderCheckInPage;
import com.techmine.gs.WicketApplication;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 *
 * @author CodeCamp4
 */
public class CylinderCheckInPageTest {

    private WicketTester tester;

    public CylinderCheckInPageTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        tester = new WicketTester(new WicketApplication());
    }

    @AfterEach
    public void tearDown() {

    }

    /**
     * Test of onInitialize method, of class CylinderCheckInPage.
     */
    /* @Test
    public void testOnInitialize() {
    System.out.println("onInitialize");
    CylinderCheckInPage instance = new CylinderCheckInPage();
    instance.onInitialize();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
    }
     */
    @Test
    public void testCylinderCheckInFormRendered() {
        tester.startPage(CylinderCheckInPage.class);
        tester.assertComponent("cylinderCheckInForm", Form.class);
    }

    @Test
    public void testSerialNumberInputRendered() {
        tester.startPage(CylinderCheckInPage.class);
        tester.assertComponent("cylinderCheckInForm:serialNo", TextField.class);
    }

    @Test
    public void testCustomerDropDownChoiceRendered() {
        tester.startPage(CylinderCheckInPage.class);
        tester.assertComponent("cylinderCheckInForm:selectCustomer", DropDownChoice.class);
    }

    @Test
    public void testReturnedDateRendered() {
        tester.startPage(CylinderCheckInPage.class);
        tester.assertComponent("cylinderCheckInForm:turnedInDate", DateTextField.class);
    }

    @Test
    public void testCheckedInByRendered() {
        tester.startPage(CylinderCheckInPage.class);
        tester.assertComponent("cylinderCheckInForm:checkedInBy", TextField.class);
    }

    @Test
    public void testBroughtInByRendered() {
        tester.startPage(CylinderCheckInPage.class);
        tester.assertComponent("cylinderCheckInForm:broughtInBy", TextField.class);
    }

    @Test
    public void testCheckInButtonRendered() {
        tester.startPage(CylinderCheckInPage.class);
        tester.assertComponent("cylinderCheckInForm:checkIn", AjaxFallbackButton.class);
    }

    @Test
    public void testCancelButtonRendered() {
        tester.startPage(CylinderCheckInPage.class);
        tester.assertComponent("cylinderCheckInForm:cancel", AjaxFallbackButton.class);
    }
}
