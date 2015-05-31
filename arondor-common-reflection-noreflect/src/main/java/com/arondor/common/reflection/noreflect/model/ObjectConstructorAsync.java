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
package com.arondor.common.reflection.noreflect.model;

import com.arondor.common.reflection.api.instantiator.InstantiationCallback;

/**
 * Object constructor prototype
 * 
 * @author Francois Barre
 * 
 */
public interface ObjectConstructorAsync
{
    /**
     * Create a new instance of an object
     * 
     * @param arguments
     *            the provided arguments
     * @return the new instance of this object
     */
    void getObjectConstructor(InstantiationCallback<ObjectConstructor> callback);
}
