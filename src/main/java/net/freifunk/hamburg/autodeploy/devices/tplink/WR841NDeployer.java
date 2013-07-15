package net.freifunk.hamburg.autodeploy.devices.tplink;

import java.util.Set;

import net.freifunk.hamburg.autodeploy.devices.Device;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Inject;

/**
 * Deploys the Freifunk firmware to the WR841N.
 *
 * @author Andreas Baldeau <andreas@baldeau.net>
 */
public class WR841NDeployer extends AbstractTPLinkDeployer {

    public static final String MODEL_NAME = "WR841N";
    public static final Set<Device> SUPPORTED_DEVICES = ImmutableSet.of(
        new Device(MODEL_NAME, "v8")
    );

    @Inject
    public WR841NDeployer(final WebDriver webDriver, final WebDriverWait wait) {
        super(
            SUPPORTED_DEVICES,
            webDriver,
            wait
        );
    }
}
