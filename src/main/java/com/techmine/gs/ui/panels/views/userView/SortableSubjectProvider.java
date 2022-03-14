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
package com.techmine.gs.ui.panels.views.userView;

import com.techmine.gs.domain.Subject;
import com.techmine.gs.service.UserService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 *
 * @author Cedric-Pemberton
 */
class SortableSubjectProvider extends SortableDataProvider<Subject, String> {

    public SortableSubjectProvider() {
        setSort("userName", SortOrder.ASCENDING);
    }

    private IModel<List<Subject>> searchData;

    public SortableSubjectProvider(IModel<List<Subject>> searchData) {
        this.searchData = searchData;
    }
    @Inject
    private UserService userService;

    List<Subject> database = getCreateDatabase();

    /*
    SortableSubjectProvider(IModel<Collection<Subject>> of) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/
    @Override
    public Iterator<? extends Subject> iterator(long first, long count) {
        // more efficent to ask the db to return the sub list.
        // List<Subject> subjects = subjectRepository.findAll();
        // return database.subList((int) first, (int) (first + count)).iterator();
        List<Subject> subjects = searchData.getObject();
        int size = subjects.size();
        if (size < first) {
            first = size;
            count = 0;
        } else if (size - first < count) {
            count = size - first;
        }

        return searchData.getObject().stream().collect(Collectors.toList()).subList((int) first, (int) (first + count)).iterator();

    }

    @Override
    public long size() {
        return database.size();
    }

    @Override
    public IModel<Subject> model(Subject t) {
        return new Model(t);

    }

    private List<Subject> getCreateDatabase() {
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject().userName("user1").password("password1"));
        subjects.add(new Subject().userName("user2").password("password2"));
        subjects.add(new Subject().userName("user3").password("password3"));

        return subjects;
    }
}
