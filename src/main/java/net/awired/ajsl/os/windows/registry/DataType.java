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

public enum DataType {

    REG_SZ("string", "REG_SZ"),
    REG_MULTI_SZ("multi string", "REG_MULTI_SZ"),
    REG_DWORD_BIG_ENDIAN("", "REG_DWORD_BIG_ENDIAN"),
    REG_DWORD("dword", "REG_DWORD"),
    REG_BINARY("binary", "REG_BINARY"),
    REG_DWORD_LITTLE_ENDIAN("", "REG_DWORD_LITTLE_ENDIAN"),
    REG_NONE("", "REG_NONE"),
    REG_EXPAND_SZ("expandable string", "REG_EXPAND_SZ")
    ;
    
    private final String name;
    private final String type;
    
    public String getName() {
        return this.name;
    }
    
    public String getType() {
        return this.type;
    }
    
    private DataType(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
