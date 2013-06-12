package net.awired.operating.system.specific;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.awired.ajsl.core.lang.reflect.ReflectTools;
import net.awired.operating.system.specific.linux.XdgDirectory;
import net.awired.operating.system.specific.system.Os;
import net.awired.operating.system.specific.system.OsLinux;
import net.awired.operating.system.specific.system.OsMac;
import net.awired.operating.system.specific.system.OsSolaris;
import net.awired.operating.system.specific.system.OsWindows;
import org.apache.commons.lang3.text.WordUtils;

public class ApplicationHomeFactory {

    private static final List<Class<? extends Os>> APPLICATION_HOMES = new ArrayList<>();

    public class ApplicationHomeLinux implements ApplicationHome, OsLinux, OsSolaris {
        @Override
        public File getFolder(String applicationName) {
            return new File(XdgDirectory.XDG_CONFIG_HOME.getDirectory(), applicationName.toLowerCase());
        }
    }

    public class ApplicationHomeMac implements ApplicationHome, OsMac {
        @Override
        public File getFolder(String applicationName) {
            return new File(System.getProperty("user.home") + "/Library/" + WordUtils.capitalize(applicationName));
        }
    }

    public class ApplicationHomeWindows implements ApplicationHome, OsWindows {
        @Override
        public File getFolder(String applicationName) {
            return new File(System.getProperty("user.home") + "/AppData/Local/"
                    + WordUtils.capitalize(applicationName));
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
            return (ApplicationHome) ReflectTools.createInstance(applicationHome);
        } catch (Exception e) {
            throw new IllegalStateException("Cannot instanciate ApplicationHome implementation", e);
        }
    }
}
