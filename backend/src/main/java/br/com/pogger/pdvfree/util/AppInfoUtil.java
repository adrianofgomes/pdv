package br.com.pogger.pdvfree.util;

import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Component;

@Component
public class AppInfoUtil {
    private final BuildProperties buildProperties;

    public AppInfoUtil(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    public String getVersao() {
        return buildProperties.getVersion();
    }

    public String getNomeAplicacaoVersao() {
        return buildProperties.getName() + " - " + buildProperties.getVersion() + " (" + buildProperties.getTime()
                + ")";
    }

}
