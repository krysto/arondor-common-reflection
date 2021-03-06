/*
 *  Copyright 2013, Arondor
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.arondor.common.reflection.gwt.client.presenter;

import com.arondor.common.reflection.gwt.client.service.GWTReflectionServiceAsync;
import com.arondor.common.reflection.model.config.ObjectConfigurationMap;
import com.google.gwt.user.client.ui.HasVisibility;
import com.google.gwt.user.client.ui.IsWidget;

public class ClassTreePresenter
{
    public interface Display extends IsWidget
    {
        ClassTreeNodePresenter.ClassDisplay createRootView(String baseClassName);
    }

    private TreeNodePresenter rootNodePresenter;

    private final Display display;

    public ClassTreePresenter(GWTReflectionServiceAsync rpcService, ObjectConfigurationMap objectConfigurationMap,
            String baseClassName, Display view)
    {
        this.display = view;

        setRootNodePresenter(new ClassTreeNodePresenter(rpcService, objectConfigurationMap, baseClassName,
                display.createRootView(baseClassName)));

        if (getRootNodePresenter().getDisplay() instanceof HasVisibility)
        {
            ((HasVisibility) getRootNodePresenter().getDisplay()).setVisible(true);
        }
        bind();
    }

    private void bind()
    {

    }

    public TreeNodePresenter getRootNodePresenter()
    {
        return rootNodePresenter;
    }

    public void setRootNodePresenter(ClassTreeNodePresenter rootNodePresenter)
    {
        this.rootNodePresenter = rootNodePresenter;
    }

    public Display getDisplay()
    {
        return display;
    }

    public IsWidget getDisplayWidget()
    {
        return getDisplay();
    }
}
