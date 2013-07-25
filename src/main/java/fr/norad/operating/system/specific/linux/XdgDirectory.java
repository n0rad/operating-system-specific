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
package fr.norad.operating.system.specific.linux;

import java.io.File;

public enum XdgDirectory {
    XDG_DATA_HOME("~/.local/share"), //
    XDG_CONFIG_HOME("~/.config"), //
    XDG_CACHE_HOME("~/.cache"), //
    ;

    private final String defaultDirectory;

    private XdgDirectory(String defaultDirectory) {
        this.defaultDirectory = defaultDirectory;
    }

    public File getDirectory() {
        return new File(findDirPath());
    }

    private String findDirPath() {
        String xdgEnvValue = System.getenv(this.name());
        if (xdgEnvValue == null) {
            xdgEnvValue = defaultDirectory;
        }
        return xdgEnvValue.replaceFirst("~", System.getProperty("user.home"));
    }
}
