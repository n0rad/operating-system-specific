package net.awired.operating.system.specific.linux;

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
