package com.gxatek.firewall.manager;

import com.gxatek.firewall.bean.FirewallConfig;
import com.gxatek.firewall.utils.CarGson;

import java.io.File;


/**
 * @author Jack_Ou  created on 2021/3/12.
 */
public class ConfigManager {
    private static final String FIREWALL_CONFIG_DIR = "/vendor/etc/data/staticconfig.json";
    private static final String GXA_FIREWALL_CONFIG_DIR = "/vendor/etc/data/gxa_staticconfig.json";
    private static ConfigManager mInstance;

    public static ConfigManager getInstance() {
        if (mInstance == null) {
            synchronized (ConfigManager.class) {
                if (mInstance == null) {
                    mInstance = new ConfigManager();
                }
            }
        }
        return mInstance;
    }

    public FirewallConfig readInstallConfig() {
        CarGson carGson = new CarGson();
        String configFilePath = getConfigFilePath();
        return carGson.getObjectFromJsonFile(configFilePath, FirewallConfig.class);
    }

    private String getConfigFilePath() {
        File file = new File(FIREWALL_CONFIG_DIR);
        if ((file.exists()) && (file.isFile())) {
            return FIREWALL_CONFIG_DIR;
        }
        return GXA_FIREWALL_CONFIG_DIR;
    }
}
