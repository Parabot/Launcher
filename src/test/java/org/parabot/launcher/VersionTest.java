package org.parabot.launcher;

import org.junit.Assert;
import org.junit.Test;
import org.parabot.launcher.helpers.VersionHelper;

/**
 * @author EmmaStone
 */
public class VersionTest {

    @Test
    public void testAmount() {
        Assert.assertNotNull(VersionHelper.getLatestClient());
    }
}
