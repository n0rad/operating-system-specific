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
package net.awired.operating.system.specific.windows.registry;

public class RegistryKey {

    //TODO: manage short version of keys
    public static final String KEY_CU = "HKEY_CURRENT_USER";
    public static final String KEY_LM = "HKEY_LOCAL_MACHINE";

    private String key;

    public RegistryKey(String key) {
        this.key = key;
    }

    // ////////////////////////////////////////////////////////////////////////////

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }

}
