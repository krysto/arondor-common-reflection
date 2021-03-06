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
package com.arondor.common.reflection.gwt.client.presenter.fields;

import java.util.List;

import com.arondor.common.reflection.bean.config.PrimitiveConfigurationBean;
import com.arondor.common.reflection.gwt.client.event.TreeNodeClearEvent;
import com.arondor.common.reflection.gwt.client.presenter.TreeNodePresenter;
import com.arondor.common.reflection.model.config.ElementConfiguration;
import com.arondor.common.reflection.model.config.ObjectConfiguration;
import com.arondor.common.reflection.model.config.ObjectConfigurationFactory;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

public class EnumTreeNodePresenter implements TreeNodePresenter
{
    private final String fieldName;

    private String fieldValue;

    public interface EnumDisplay extends ValueDisplay<String>
    {

        void initEnumList(List<String> enumList);

    }

    private final EnumDisplay enumDisplay;

    public EnumTreeNodePresenter(String fieldName, EnumDisplay enumDisplay)
    {
        this.fieldName = fieldName;
        this.enumDisplay = enumDisplay;
        bind();
    }

    private void bind()
    {
        enumDisplay.addValueChangeHandler(new ValueChangeHandler<String>()
        {
            public void onValueChange(ValueChangeEvent<String> event)
            {
                fieldValue = event.getValue();
            }
        });
        enumDisplay.addTreeNodeClearHandler(new TreeNodeClearEvent.Handler()
        {
            public void onTreeNodeClearEvent(TreeNodeClearEvent treeNodeClearEvent)
            {
                fieldValue = null;
            }
        });

    }

    public String getFieldName()
    {
        return fieldName;
    }

    public ElementConfiguration getElementConfiguration(ObjectConfigurationFactory objectConfigurationFactory)
    {
        if (fieldValue != null && !fieldValue.isEmpty())
        {
            return objectConfigurationFactory.createPrimitiveConfiguration(fieldValue);
        }
        return null;
    }

    public void setElementConfiguration(ElementConfiguration elementConfiguration)
    {
        if (elementConfiguration instanceof ObjectConfiguration)
        {
            ObjectConfiguration objectConfiguration = (ObjectConfiguration) elementConfiguration;
            if (objectConfiguration.getConstructorArguments() != null
                    && !objectConfiguration.getConstructorArguments().isEmpty())
            {
                ElementConfiguration fieldValue = objectConfiguration.getConstructorArguments().get(0);

                if (fieldValue instanceof PrimitiveConfigurationBean)
                {
                    String value = ((PrimitiveConfigurationBean) fieldValue).getValue();
                    enumDisplay.setValue(value);
                }
            }
        }
    }

    public Display getDisplay()
    {
        return enumDisplay;
    }

    public void setEnumValues(List<String> enumValues)
    {
        enumDisplay.initEnumList(enumValues);
    }

}
