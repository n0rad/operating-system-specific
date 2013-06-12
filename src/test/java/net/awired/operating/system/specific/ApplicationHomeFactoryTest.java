package net.awired.operating.system.specific;

import java.io.File;
import junit.framework.Assert;
import net.awired.operating.system.specific.ApplicationHome;
import net.awired.operating.system.specific.ApplicationHomeFactory;
import org.junit.Test;

public class ApplicationHomeFactoryTest {

    @Test
    public void should_find_directory() throws Exception {
        ApplicationHome applicationHome = ApplicationHomeFactory.getApplicationHome();

        File folder = applicationHome.getFolder("housecream");
        Assert.assertTrue(folder.getAbsolutePath().endsWith("/housecream"));
    }
}
