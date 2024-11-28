package com.github.kanataidarov.iview;

import org.cryptacular.util.CertUtil;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.X509Certificate;

public class Main {
    public static void main(String[] args) throws Exception {
        var keystore = KeyStore.getInstance("JKS");
        var keystoreLocation = new FileInputStream(System.getenv("JKS_PATH"));
        keystore.load(keystoreLocation, System.getenv("JKS_PWD").toCharArray());
        var aliases = keystore.aliases();

        while (aliases.hasMoreElements()) {
            var alias = aliases.nextElement();
            System.out.println("Alias name: " + alias);
            var certificate = (X509Certificate) keystore.getCertificate(alias);
            var cn = CertUtil.subjectCN(certificate);
            System.out.println("Common Name: " + cn);
        }
    }
}