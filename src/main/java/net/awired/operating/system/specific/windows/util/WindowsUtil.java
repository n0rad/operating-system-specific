package net.awired.operating.system.specific.windows.util;

import java.util.ArrayList;
import java.util.List;
import net.awired.ajsl.core.lang.Exec;

public class WindowsUtil {
    public static void closeSession() {
        Exec.runExec("shutdown.exe -l -t 0");
    }

    public static List<Process> listRunningProcesses() {
        //TODO: transform to platform independent

        // get output from exec
        List<String> result = Exec.runExec("tasklist.exe /fo csv /nh");
        // create process list
        List<Process> processes = new ArrayList<>(result.size());

        for (String line : result) {
            if (!line.trim().equals("")) {
                Process p = new Process();
                String[] pData = line.split(",");

                p.setName(pData[0]);
                p.setPid(Integer.parseInt(pData[1]));
                p.setSessionName(pData[2]);
                p.setSessionId(Integer.parseInt(pData[3]));
                p.setMemory(pData[4]);

                processes.add(p);
            }
        }
        return processes;
    }
}
