package net.freifunk.autodeploy.firmware;

import java.io.File;
import java.util.Map;
import java.util.Set;

import net.freifunk.autodeploy.device.Device;
import net.freifunk.autodeploy.device.DeviceService;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;
import com.google.inject.Inject;

public class FirmwareServiceImpl implements FirmwareService {

    private final Map<Firmware, FirmwareConfigurator> _configurators;
    private final DeviceService _deviceService;

    @Inject
    public FirmwareServiceImpl(
        final Map<Firmware, FirmwareConfigurator> configurators,
        final DeviceService deviceService
    ) {
        _configurators = configurators;
        _deviceService = deviceService;
    }

    @Override
    public Firmware findSupportedFirmware(final String firmwareString) {
        final Iterable<Firmware> matches = Iterables.filter(_configurators.keySet(), new Predicate<Firmware>() {

            @Override
            public boolean apply(final Firmware firmware) {
                return firmware != null && firmware.getName().equals(firmwareString);
            }
        });

        if (Iterables.size(matches) > 1) {
            throw new IllegalStateException("More than one firmware found: " + firmwareString);
        }
        return Iterables.getFirst(matches, null);
    }

    @Override
    public FirmwareConfigurator getConfigurator(final Firmware firmware) {
        final FirmwareConfigurator configurator = _configurators.get(firmware);
        if (configurator == null) {
            throw new IllegalArgumentException("No configurator found for firmware: " + firmware);
        }
        return configurator;
    }

    @Override
    public Set<Firmware> getSupportedFirmwares() {
        return _configurators.keySet();
    }

    @Override
    public Multimap<Device, Firmware> getAvailableDeviceFirmwareMappings(final File firmwareImageDirectory) {
        Preconditions.checkState(firmwareImageDirectory.exists(), "Directory not found: " + firmwareImageDirectory);
        Preconditions.checkState(firmwareImageDirectory.isDirectory(), "Not a directory: " + firmwareImageDirectory);

        final Builder<Device, Firmware> builder = ImmutableMultimap.builder();

        for (final Firmware firmware: getSupportedFirmwares()) {
            for (final Device device: _deviceService.getSupportedDevices()) {
                final File image = toFirmwareImageFile(firmwareImageDirectory, firmware, device);
                if (image.exists() && image.isFile()) {
                    builder.put(device, firmware);
                }
            }
        }

        return builder.build();
    }

    @Override
    public File findFirmwareImage(final File firmwareImageDirectory, final Device device, final Firmware firmware) {
        final File firmwareImageFile = toFirmwareImageFile(firmwareImageDirectory, firmware, device);
        return firmwareImageFile.exists() ? firmwareImageFile : null;
    }

    private File toFirmwareImageFile(
        final File firmwareImageDirectory,
        final Firmware firmware,
        final Device device
    ) {
        return new File(firmwareImageDirectory, (firmware.getName() + "_" + device.getModel() + "_" + device.getVersion()).toLowerCase());
    }
}
