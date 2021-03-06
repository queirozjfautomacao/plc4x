/*
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
*/
package org.apache.plc4x.java.mock.field;

import org.apache.plc4x.java.api.exceptions.PlcInvalidFieldException;
import org.apache.plc4x.java.api.model.PlcField;

public class MockField implements PlcField {

    private final String address;
    private final MockPlcValue plcValue;


    public static MockField of(String addressString) throws PlcInvalidFieldException {
        return new MockField(addressString);
    }

    public MockField(String address) {
        this.address = address;
        this.plcValue = null;
    }

    public MockField(String address, MockPlcValue plcValue) {
        this.address = address;
        this.plcValue = plcValue;
    }

    public String getAddress() {
        return address;
    }

    public MockPlcValue getPlcValue() {
        return plcValue;
    }

    @Override
    public String toString() {
        return "mock field: " + address;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof MockField
            && ((MockField) o).address.equals(this.address);
    }

    @Override
    public int hashCode() {
        return address.hashCode();
    }

}
