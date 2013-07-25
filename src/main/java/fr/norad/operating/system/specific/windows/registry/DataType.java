/**
 *
 *     Copyright (C) norad.fr
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *             http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */
/**
 * $Id:$
 *
 * This file is part of Business Intranet Collaboratif (BIC).
 *
 * Labo NTIC Production 2008
 * 
 * @author renaudinx
 * @creation 22 oct. 2008
 * @version 1.0
 */
package fr.norad.operating.system.specific.windows.registry;

public enum DataType {

    REG_SZ("string", "REG_SZ"),
    REG_MULTI_SZ("multi string", "REG_MULTI_SZ"),
    REG_DWORD_BIG_ENDIAN("", "REG_DWORD_BIG_ENDIAN"),
    REG_DWORD("dword", "REG_DWORD"),
    REG_BINARY("binary", "REG_BINARY"),
    REG_DWORD_LITTLE_ENDIAN("", "REG_DWORD_LITTLE_ENDIAN"),
    REG_NONE("", "REG_NONE"),
    REG_EXPAND_SZ("expandable string", "REG_EXPAND_SZ")
    ;
    
    private final String name;
    private final String type;
    
    public String getName() {
        return this.name;
    }
    
    public String getType() {
        return this.type;
    }
    
    private DataType(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
