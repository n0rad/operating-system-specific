/**
 *
 *     Copyright (C) norad.fr
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *             http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */
package fr.norad.operating.system.specific.windows.util;

import java.util.ArrayList;
import java.util.List;
import fr.norad.core.lang.Exec;

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
