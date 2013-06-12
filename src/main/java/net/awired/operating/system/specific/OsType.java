package net.awired.operating.system.specific;

import java.util.List;
import net.awired.ajsl.core.lang.EnumInterface;
import net.awired.operating.system.specific.system.Os;

public interface OsType<E extends Enum<E>> extends EnumInterface<E> {

    Class<? extends Os> getOsInterface();

    List<String> getOsNamePattern();

}
