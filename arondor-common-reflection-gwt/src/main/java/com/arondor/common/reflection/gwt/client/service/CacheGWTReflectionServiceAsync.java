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
package com.arondor.common.reflection.gwt.client.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.arondor.common.reflection.model.java.AccessibleClass;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CacheGWTReflectionServiceAsync implements GWTReflectionServiceAsync
{
    private final GWTReflectionServiceAsync rpcService;

    public CacheGWTReflectionServiceAsync(GWTReflectionServiceAsync rpcService)
    {
        this.rpcService = rpcService;
    }

    private final Map<String, AccessibleClass> accessibleClassMap = new HashMap<String, AccessibleClass>();

    public void getAccessibleClass(final String className, final AsyncCallback<AccessibleClass> callback)
    {
        if (accessibleClassMap.containsKey(className))
        {
            callback.onSuccess(accessibleClassMap.get(className));
        }
        else
        {
            rpcService.getAccessibleClass(className, new AsyncCallback<AccessibleClass>()
            {
                public void onFailure(Throwable caught)
                {
                    callback.onFailure(caught);
                }

                public void onSuccess(AccessibleClass result)
                {
                    accessibleClassMap.put(className, result);
                    callback.onSuccess(result);
                }
            });
        }
    }

    private final Map<String, Collection<AccessibleClass>> implementingAccessibleClassesMap = new HashMap<String, Collection<AccessibleClass>>();

    public void getImplementingAccessibleClasses(final String name,
            final AsyncCallback<Collection<AccessibleClass>> callback)
    {
        if (implementingAccessibleClassesMap.containsKey(name))
        {
            callback.onSuccess(implementingAccessibleClassesMap.get(name));
        }
        else
        {
            rpcService.getImplementingAccessibleClasses(name, new AsyncCallback<Collection<AccessibleClass>>()
            {
                public void onFailure(Throwable caught)
                {
                    callback.onFailure(caught);
                }

                public void onSuccess(Collection<AccessibleClass> result)
                {
                    implementingAccessibleClassesMap.put(name, result);
                    callback.onSuccess(result);
                }
            });
        }
    }

}
