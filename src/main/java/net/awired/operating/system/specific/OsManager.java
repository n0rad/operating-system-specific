package net.awired.operating.system.specific;

import java.util.List;
import net.awired.operating.system.specific.system.Os;

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
