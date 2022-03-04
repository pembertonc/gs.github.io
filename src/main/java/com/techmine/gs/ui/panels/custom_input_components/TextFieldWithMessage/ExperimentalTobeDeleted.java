/*
 * Copyright 2022 Cedric-Pemberton.
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
package com.techmine.gs.ui.panels.custom_input_components.TextFieldWithMessage;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.IMarkupResourceStreamProvider;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.StringResourceStream;

/**
 *
 * @author Cedric-Pemberton
 */
@Deprecated(forRemoval = true, since = "2022-03-04")
public class ExperimentalTobeDeleted extends Panel implements IMarkupResourceStreamProvider {

    public ExperimentalTobeDeleted(String id) {
        super(id);
    }

    @Override
    public IResourceStream getMarkupResourceStream(MarkupContainer mc, Class<?> type) {
        /* <wicket:panel>

            <div class="control-group">
                <input class="form-control" placeholder="User Name" type="text" id="inputComponent" />
                <label class="control-label" for="inputComponent" id="inputLabel">User Name</label>
                <div  id="feedback"></div>
            </div>


        </wicket:panel>  */

        String markup = new StringBuilder("<wicket:panel>")
                .append("<div class=\"control-group\">")
                .append("<input class=\"form-control\" placeholder=\"User Name\" ")
                .append(" id=\"inputComponent\" />")
                .append("<label class=\"control-label\" for=\"inputComponent\" id=\"inputLabel\">User Name</label>")
                .append("<div  id=\"feedback\"></div>")
                .append("</div>")
                .append("</wicket:panel>").toString();

        StringResourceStream resourceStream = new StringResourceStream(markup);
        return resourceStream;
    }

}
