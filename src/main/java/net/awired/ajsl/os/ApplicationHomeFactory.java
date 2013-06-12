package net.awired.ajsl.os;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.awired.ajsl.core.lang.StringUtils;
import net.awired.ajsl.core.lang.reflect.ReflectTool;
import net.awired.ajsl.os.linux.XdgDirectory;
import net.awired.ajsl.os.system.Os;
import net.awired.ajsl.os.system.OsLinux;
import net.awired.ajsl.os.system.OsMac;
import net.awired.ajsl.os.system.OsSolaris;
import net.awired.ajsl.os.system.OsWindows;

public class ApplicationHomeFactory {

    private static final List<Class<? extends Os>> APPLICATION_HOMES = new ArrayList<Class<? extends Os>>();

    public class ApplicationHomeLinux implements ApplicationHome, OsLinux, OsSolaris {
        @Override
        public File getFolder(String applicationName) {
            return new File(XdgDirectory.XDG_CONFIG_HOME.getDirectory(), applicationName.toLowerCase());
        }
    }

    public class ApplicationHomeMac implements ApplicationHome, OsMac {
        @Override
        public File getFolder(String applicationName) {
            return new File(System.getProperty("user.home") + "/Library/" + StringUtils.ucFirst(applicationName));
        }
    }

    public class ApplicationHomeWindows implements ApplicationHome, OsWindows {
        @Override
        public File getFolder(String applicationName) {
            return new File(System.getProperty("user.home") + "/AppData/Local/"
                    + StringUtils.ucFirst(applicationName));
        }
    }

    static {
        APPLICATION_HOMES.add(ApplicationHomeLinux.class);
        APPLICATION_HOMES.add(ApplicationHomeMac.class);
        APPLICATION_HOMES.add(ApplicationHomeWindows.class);
    }

    public static ApplicationHome getApplicationHome() {
        OsManager<?> osManager = OsManager.getInstance();
        Class<?> applicationHome = osManager.getOsClassInArray(APPLICATION_HOMES);
        try {
            return (ApplicationHome) ReflectTool.createInstance(applicationHome);
        } catch (Exception e) {
            throw new IllegalStateException("Cannot instanciate ApplicationHome implementation", e);
        }
    }
}
