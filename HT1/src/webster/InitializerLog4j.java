package webster;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Order;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.apache.logging.log4j.core.config.plugins.Plugin;

import java.io.File;
import java.net.URI;

/*
* Initialize Log4j Using ConfigurationBuilder with a Custom ConfigurationFactory. (without xml or properties)
* https://logging.apache.org/log4j/2.x/manual/customconfig.html
* */

@Plugin(name = "InitializerLog4j", category = ConfigurationFactory.CATEGORY)
@Order(50)
public class InitializerLog4j extends ConfigurationFactory {
    private static String pathLogFile = "src/webster/resources/log.txt"; //default path

    public static void setPathLogFile(String pathLogFile) {

        if (new File(pathLogFile).isFile()) {
            InitializerLog4j.pathLogFile = pathLogFile;
        }
        if (new File(pathLogFile).isDirectory()) {
            InitializerLog4j.pathLogFile = pathLogFile+"/log.txt";
        }
    }

    public static String getPathLogFile() {
        return pathLogFile;
    }


    static Configuration createConfiguration(final String name, ConfigurationBuilder<BuiltConfiguration> builder, String path) {
        builder.setConfigurationName(name);

        AppenderComponentBuilder appenderBuilder = builder.newAppender("Console", "CONSOLE").addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT);
        appenderBuilder.add(builder.newLayout("PatternLayout").addAttribute("pattern", "%5p | %d{yyy-MM-dd HH:mm:ss.SSS} | %m%n"));
        builder.add(appenderBuilder);

        appenderBuilder = builder.newAppender("File", "FILE").addAttribute("fileName", path).addAttribute("append", false);
        appenderBuilder.add(builder.newLayout("PatternLayout").addAttribute("pattern", "%m%n"));
        builder.add(appenderBuilder);

        builder.add(builder.newLogger("webster", Level.ALL).add(builder.newAppenderRef("File").addAttribute("level", Level.INFO)));
        builder.add(builder.newRootLogger(Level.ALL).add(builder.newAppenderRef("Console").addAttribute("level", Level.ALL)));

        return builder.build();
    }

    @Override
    public Configuration getConfiguration(final LoggerContext loggerContext, final ConfigurationSource source) {
        return getConfiguration(loggerContext, source.toString(), null);
    }

    @Override
    public Configuration getConfiguration(final LoggerContext loggerContext, final String name, final URI configLocation) {
        ConfigurationBuilder<BuiltConfiguration> builder = newConfigurationBuilder();
        return createConfiguration(name, builder, pathLogFile);
    }

    @Override
    protected String[] getSupportedTypes() {
        return new String[]{"*"};
    }
}