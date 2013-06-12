package net.awired.ajsl.os;

import java.util.List;
import net.awired.ajsl.core.lang.EnumInterface;
import net.awired.ajsl.os.system.Os;

public interface OsType<E extends Enum<E>> extends EnumInterface<E> {

    Class<? extends Os> getOsInterface();

    List<String> getOsNamePattern();

}
