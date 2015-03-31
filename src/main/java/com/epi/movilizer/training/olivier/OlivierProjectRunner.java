package com.epi.movilizer.training.olivier;

import com.movilizer.cli.CliProjectRunner;
import com.movilizer.projectmanagement.MobileProjectException;
import org.apache.commons.cli.ParseException;

/**
 * @author Peter.Grigoriev@movilizer.com
 */
public class OlivierProjectRunner {

    public static void main(String[] args) throws ParseException, MobileProjectException {
        adjustProxy();
        new CliProjectRunner(new OlivierProject()).run(args);
    }

    private static void adjustProxy() {
        System.setProperty("http.proxyUser", "formation");
        System.setProperty("http.proxyPassword", "lotus");
    }
}
