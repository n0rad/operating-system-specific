package net.awired.operating.system.specific.windows.registry;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import net.awired.ajsl.core.lang.Exec;

public class RegistryManager {

    private static final String REGISTRY_ADD       = "reg add \"{0}\" /v \"{1}\" /t \"{2}\" /d \"{3}\" /f";
    private static final String REGISTRY_QUERY     = "reg query \"{0}\" /v \"{1}\"";
    private static final String REGISTRY_QUERY_ALL = "reg query \"{0}\" /s";
    private static final String REGISTRY_DELETE    = "";
    private static final String REG_VERSION        = "! REG.EXE VERSION 3.0";

    public static List<RegistryField> getAll(RegistryKey key) {

        MessageFormat messageFormat = new MessageFormat(REGISTRY_QUERY_ALL);
        String[] ss2 = { key.toString() };
        List<String> l = Exec.runExec(messageFormat.format(ss2));
        return parseRegResult(l, key);
    }

    public static RegistryField getValue(RegistryKey key, String name) {
        MessageFormat messageFormat = new MessageFormat(REGISTRY_QUERY);
        String[] ss2 = { key.toString(), name };
        List<String> l = Exec.runExec(messageFormat.format(ss2));

        List<RegistryField> fields = parseRegResult(l, key);
        if (fields.size() > 1) {
            throw new RuntimeException("bad number of result element from reg.exe");
        }
        return fields.get(0);
    }

    public static RegistryField getGlobalValue(GlobalRegistryKey key, String name) {
        try {
            RegistryField f = getValue(key.getCurrentUserKey(), name);
            return f;
        } catch (Exception e) {
            RegistryField f = getValue(key.getLocalMachineKey(), name);
            return f;
        }
    }

    public static void setValue(RegistryKey key, String name, DataType type, Object value) {
        MessageFormat messageFormat = new MessageFormat(REGISTRY_ADD);
        String[] ss2 = { key.toString(), name, type.getType(), value.toString() };
        List<String> l = Exec.runExec(messageFormat.format(ss2));
    }

    public static List<RegistryField> parseRegResult(List<String> res, RegistryKey key) {
        try {
            List<RegistryField> fields = new ArrayList<RegistryField>();
            int pos = 0;
            for (; pos < res.size(); pos++) {
                if (res.get(pos).equals(REG_VERSION) || res.get(pos).trim().isEmpty()) {
                    continue;
                }
                if (res.get(pos).equals(key.getKey())) {
                    break;
                }
            }

            for (int i = pos + 1; i < res.size(); i++) {
                if (res.get(i).trim().equals("")) {
                    continue;
                }

                //				String[] s = res.get(i).trim().split("\t");
                String[] s = res.get(i).trim().split("\\s+", 3);

                DataType type = DataType.valueOf(s[1]);

                // TODO: manage other DWORD TYPES
                if (type.equals(DataType.REG_DWORD)) {
                    fields.add(new RegistryField(key, s[0], DataType.valueOf(s[1]), Integer.decode(s[2]).toString()));
                } else {
                    fields.add(new RegistryField(key, s[0], DataType.valueOf(s[1]), s[2]));
                }

            }
            return fields;
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException("can not parse reg.exe result", e);
        }

    }
}
