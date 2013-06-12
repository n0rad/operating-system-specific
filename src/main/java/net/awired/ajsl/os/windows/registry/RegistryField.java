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
package net.awired.ajsl.os.windows.registry;

public class RegistryField {

    private String      name;
    private String      value;
    private DataType    type = DataType.REG_SZ;
    private RegistryKey key;

    // //////////////////////////////////////////////////////////////////////

    public RegistryField(RegistryKey key, String name, String value) {
        this.key = key;
        this.name = name;
        this.value = value;
    }

    public RegistryField(RegistryKey key, String name, DataType type,
            String value) {
        this(key, name, value);
        this.type = type;
    }

    // //////////////////////////////////////////////////////////////////////

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DataType getType() {
        return this.type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public RegistryKey getKey() {
        return this.key;
    }

    public void setKey(RegistryKey key) {
        this.key = key;
    }

}
