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
package fr.norad.operating.system.specific;

import java.util.List;
import fr.norad.operating.system.specific.system.Os;

public class OsManager<T extends OsType<?>> {

    private static final String OS_NAME_KEY = "os.name";
    //    private static final String OS_VERSION_KEY = "os.version";
    //    private static final String OS_ARCH_KEY = "os.arch";

    private static final Class<? extends OsType<?>> OS_TYPE_ENUM = OsTypeList.class;

    private T osType;

    public OsManager(T osType) {
        this.osType = osType;
    }

    public Class<? extends Os> getOsClassInArray(List<Class<? extends Os>> classes) {
        for (Class<? extends Os> clazz : classes) {
            Class<?> o = osType.getOsInterface();
            if (o.isAssignableFrom(clazz)) {
                return clazz;
            }
        }
        return null;
    }

    public static OsManager<?> getInstance() {
        return getInstance(OS_TYPE_ENUM);
    }

    public static OsManager<?> getInstance(Class<? extends OsType<?>> osTypeEnum) {
        String name = System.getProperty(OS_NAME_KEY).toLowerCase();
        OsType<?>[] constants = osTypeEnum.getEnumConstants();

        for (OsType<?> osType : constants) {
            for (String pattern : osType.getOsNamePattern()) {
                if (name.indexOf(pattern.toLowerCase()) >= 0) {
                    return new OsManager<OsType<?>>(osType);
                }
            }
        }
        throw new IllegalStateException("Your os is not supported by this OsType : " + osTypeEnum);
    }

    // ////////////////////////////////////////////////////////////////////////

    public T getOsType() {
        return osType;
    }
}
