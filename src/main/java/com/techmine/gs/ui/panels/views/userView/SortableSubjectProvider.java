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
import com.techmine.gs.repository.SubjectRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilterStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

/**
 *
 * @author Cedric-Pemberton
 */
class SortableSubjectProvider extends SortableDataProvider<Subject, String> {

    public SortableSubjectProvider() {
        setSort("userName", SortOrder.ASCENDING);
    }

    @Inject
    private SubjectRepository subjectRepository;

    List<Subject> database = getCreateDatabase();

    @Override
    public Iterator<? extends Subject> iterator(long first, long count) {
        // more efficent to ask the db to return the sub list.
        // List<Subject> subjects = subjectRepository.findAll();
        return database.subList((int) first, (int) (first + count)).iterator();

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
        List<Subject> subjects = new ArrayList<Subject>();
        subjects.add(new Subject().userName("user1").password("password1"));
        subjects.add(new Subject().userName("user2").password("password2"));
        subjects.add(new Subject().userName("user3").password("password3"));
        return subjects;
    }
}
