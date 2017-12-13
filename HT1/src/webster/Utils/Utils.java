package webster.Utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;

/*
* Programmatically Modifying log4j2 the Current Configuration after Initialization
*
* */
public class Utils {


        public static void initLogFile(String path, Level level){
            final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
            final Configuration config = ctx.getConfiguration();

            Layout layout = PatternLayout.createLayout("%m%n", null, config, null, null, false, false, null, null);
            Appender appender = FileAppender.createAppender(path, "false", "true", "File", "true",
                    "false", "false", null, layout, null, "false", null, config);
            appender.start();
            config.addAppender(appender);
            AppenderRef ref = AppenderRef.createAppenderRef("File", null, null);
            AppenderRef[] refs = new AppenderRef[] {ref};
            LoggerConfig loggerConfig = config.getLoggerConfig("webster");
            loggerConfig.addAppender(appender, level, null);
            ctx.updateLoggers();
    }
}
