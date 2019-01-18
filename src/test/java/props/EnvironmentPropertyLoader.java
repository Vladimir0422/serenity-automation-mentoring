package props;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by : Volodymyr_Silitskyi
 * Created at : 1/11/2019
 */


public class EnvironmentPropertyLoader {

    private static Logger log = LoggerFactory.getLogger(EnvironmentPropertyLoader.class);

    public static Properties loadByEnvironment() {
        String propertyName = StringUtils.isEmpty(System.getProperty("propertyName")) ? "env2" : System.getProperty("propertyName");

        return getProperty(propertyName);
    }

    public static Properties getProperty(String propertyName) {
        String propertiesFile;
        Properties properties = new Properties();

        propertiesFile = propertyName + ".properties";
        log.info("The property is " + propertiesFile);
        InputStream loader = EnvironmentPropertyLoader.class.getClassLoader().getResourceAsStream(propertiesFile);

        if (loader != null) {
            try {
                properties.load(loader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return properties;
    }
}
