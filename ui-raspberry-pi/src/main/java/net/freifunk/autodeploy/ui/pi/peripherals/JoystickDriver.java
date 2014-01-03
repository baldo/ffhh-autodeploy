/*
 * Freifunk Auto Deployer
 * Copyright (C) 2013, 2014 by Andreas Baldeau <andreas@baldeau.net>
 *
 *
 * For contributers see file CONTRIB.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 *
 * Uses Logback (http://logback.qos.ch/) which is dual licensed under EPL v1.0 and LGPL v2.1.
 * See http://logback.qos.ch/license.html for details.
 */
package net.freifunk.autodeploy.ui.pi.peripherals;

/**
 * Driver for listening for joystick events.
 *
 * @author Andreas Baldeau <andreas@baldeau.net>
 */
public interface JoystickDriver {

    public static enum JoystickEvent {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        BUTTON,
        ;
    }

    /**
     * Initializes the driver.
     */
    void init();

    /**
     * Shuts down the driver. Always call before terminating.
     */
    void shutdown();

    /**
     * Reads the next {@link JoystickEvent}. Will block until an event occurs.
     */
    JoystickEvent read();

    /**
     * Flushes the buffer. Old events will be discarded.
     */
    void flush();
}
