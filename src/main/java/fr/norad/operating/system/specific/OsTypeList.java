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

import java.util.Arrays;
import java.util.List;
import fr.norad.operating.system.specific.system.Os;
import fr.norad.operating.system.specific.system.OsLinux;
import fr.norad.operating.system.specific.system.OsMac;
import fr.norad.operating.system.specific.system.OsSolaris;
import fr.norad.operating.system.specific.system.OsWindows;

public enum OsTypeList implements OsType<OsTypeList> {

    WINDOWS(OsWindows.class, "win"), //
    LINUX(OsLinux.class, new String[] { "nix", "nux", "aix" }), //
    MAC(OsMac.class, "mac"), //
    SOLARIS(OsSolaris.class, "sunos"), // 
    ;

    private final Class<? extends Os> osInterface;
    private final List<String> osNamePattern;

    private OsTypeList(Class<? extends Os> osInterface, String pattern) {
        this.osInterface = osInterface;
        this.osNamePattern = Arrays.asList(pattern);
    }

    private OsTypeList(Class<? extends Os> osInterface, String[] osNames) {
        this.osInterface = osInterface;
        this.osNamePattern = Arrays.asList(osNames);
    }

    @Override
    public Class<? extends Os> getOsInterface() {
        return osInterface;
    }

    @Override
    public List<String> getOsNamePattern() {
        return osNamePattern;
    }

}
