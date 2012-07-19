/*Copyright 2012 Citrix Systems, Inc. Licensed under the
Apache License, Version 2.0 (the "License"); you may not use this
file except in compliance with the License.  Citrix Systems, Inc.
reserves all rights not expressly granted by the License.
You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/


package com.cloud.upgrade.dao;

import java.io.File;
import java.sql.Connection;

import org.apache.log4j.Logger;

import com.cloud.utils.exception.CloudRuntimeException;
import com.cloud.utils.script.Script;

public class Upgrade304to305 extends Upgrade30xBase implements DbUpgrade {
    final static Logger s_logger = Logger.getLogger(Upgrade304to305.class);

    @Override
    public String[] getUpgradableVersionRange() {
        return new String[] { "3.0.4", "3.0.5" };
    }

    @Override
    public String getUpgradedVersion() {
        return "3.0.5";
    }

    @Override
    public boolean supportsRollingUpgrade() {
        return true;
    }

    @Override
    public File[] getPrepareScripts() {
        String script = Script.findScript("", "db/schema-304to305.sql");
        if (script == null) {
            throw new CloudRuntimeException("Unable to find db/schema-304to305.sql");
        }

        return new File[] { new File(script) };
    }

    @Override
    public void performDataMigration(Connection conn) {
       
    }

    @Override
    public File[] getCleanupScripts() {
        String script = Script.findScript("", "db/schema-304to305-cleanup.sql");
        if (script == null) {
            throw new CloudRuntimeException("Unable to find db/schema-304to305-cleanup.sql");
        }

        return new File[] { new File(script) };
    }
}
