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
package net.freifunk.autodeploy.ui.commandline;

import static com.google.inject.Scopes.SINGLETON;
import net.freifunk.autodeploy.AutoDeployModule;

import com.google.inject.AbstractModule;

/**
 * Module for configuring the command line UI.
 *
 * @author Andreas Baldeau <andreas@baldeau.net>
 */
public class CommandLineUIModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new AutoDeployModule());
        bind(CommandLineParser.class).to(CommandLineParserImpl.class).in(SINGLETON);
    }
}
