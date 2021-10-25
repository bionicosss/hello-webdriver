
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class log4jExample{
    /* Get actual class name to be printed on */
    static Logger log = Logger.getLogger(log4jExample.class);

    public static void main(String[] args)throws IOException,SQLException{
        log.debug("Hello this is a debug message");
        log.info("Hello this is an info message");
        log.trace("Open bookdepository.com website.");
        log.error("Did it again! Step01");
    }
}