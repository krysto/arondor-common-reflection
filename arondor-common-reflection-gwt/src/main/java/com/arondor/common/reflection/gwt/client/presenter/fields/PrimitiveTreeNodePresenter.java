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

import com.arondor.common.reflection.gwt.client.event.TreeNodeClearEvent;
import com.arondor.common.reflection.gwt.client.presenter.TreeNodePresenter;
import com.arondor.common.reflection.model.config.ElementConfiguration;
import com.arondor.common.reflection.model.config.ObjectConfigurationFactory;
import com.arondor.common.reflection.model.config.PrimitiveConfiguration;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

public class PrimitiveTreeNodePresenter implements TreeNodePresenter
{
    private final String fieldName;

    private String fieldValue;

    private String defaultValue;

    public interface PrimitiveDisplay extends ValueDisplay<String>
    {
    }

    private final PrimitiveDisplay primitiveDisplay;

    public PrimitiveTreeNodePresenter(String fieldName, PrimitiveDisplay primitiveDisplay)
    {
        this.fieldName = fieldName;
        this.primitiveDisplay = primitiveDisplay;
        bind();
    }

    private void bind()
    {
        primitiveDisplay.addValueChangeHandler(new ValueChangeHandler<String>()
        {
            public void onValueChange(ValueChangeEvent<String> event)
            {
                fieldValue = event.getValue();
            }
        });
        primitiveDisplay.addTreeNodeClearHandler(new TreeNodeClearEvent.Handler()
        {
            public void onTreeNodeClearEvent(TreeNodeClearEvent treeNodeClearEvent)
            {
                fieldValue = null;
                primitiveDisplay.setDefaultValue(defaultValue);
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
        if (elementConfiguration instanceof PrimitiveConfiguration)
        {
            PrimitiveConfiguration primitiveConfiguration = (PrimitiveConfiguration) elementConfiguration;
            fieldValue = primitiveConfiguration.getValue();
            if (defaultValue != null)
            {
                primitiveDisplay.setDefaultValue(defaultValue);
            }
            if (fieldValue != null)
            {
                primitiveDisplay.setValue(fieldValue);
            }
        }
    }

    public String getDefaultValue()
    {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue)
    {
        this.defaultValue = defaultValue;
    }

    public Display getDisplay()
    {
        return primitiveDisplay;
    }

}
