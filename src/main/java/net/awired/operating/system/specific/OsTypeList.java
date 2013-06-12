package net.awired.operating.system.specific;

import java.util.Arrays;
import java.util.List;
import net.awired.operating.system.specific.system.Os;
import net.awired.operating.system.specific.system.OsLinux;
import net.awired.operating.system.specific.system.OsMac;
import net.awired.operating.system.specific.system.OsSolaris;
import net.awired.operating.system.specific.system.OsWindows;

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
