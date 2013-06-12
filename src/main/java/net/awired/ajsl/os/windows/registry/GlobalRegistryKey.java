package net.awired.ajsl.os.windows.registry;

public class GlobalRegistryKey {

	private RegistryKey localMachine;
	private RegistryKey currentUser;

	public GlobalRegistryKey(String key) {
		localMachine = new RegistryKey(RegistryKey.KEY_LM + "\\" + key);
		currentUser = new RegistryKey(RegistryKey.KEY_CU + "\\" + key);
	}

	public RegistryKey getCurrentUserKey() {
		return currentUser;
	}

	public RegistryKey getLocalMachineKey() {
		return localMachine;
	}
}
