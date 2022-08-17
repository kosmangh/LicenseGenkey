/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sabonay.common.license;

import java.text.DecimalFormat;
import java.util.Random;

/**
 *
 * @author Kwadwo
 */
public class GenKey {

    public GenKey() {
    }
    
    private static String generateRadom2() {
        StringBuffer sNumber = new StringBuffer();
        Random randomGenerator = new Random();
        for (int idx = 0; idx < 2; ++idx) {
            sNumber.append(randomGenerator.nextInt(9));
        }
        return sNumber.toString();
    }
    
    public static String generateLicenseFrom(String expirationDate) {
        System.out.println("GenKey::generateLicenseFrom() expirationDate: " + expirationDate);
        String licenseKey = "";
        String dateDigits = expirationDate.replaceAll("/", "");
        if ((null != dateDigits) && (dateDigits.length() == 8)) {
            String strMonth = dateDigits.substring(0, 2);
            String strDay = dateDigits.substring(2, 4);
            String strYear = dateDigits.substring(4, 8);

            String dateNumber = strYear + strMonth + strDay;
            double dnumber = Double.parseDouble(dateNumber);
            dnumber = ((dnumber * 256.0) - 121.0);
            String randomNumber = generateRadom2();
            DecimalFormat df = new DecimalFormat("#");
            String key = randomNumber + df.format(dnumber);
            while (key.length() >= 3) {
                licenseKey += key.substring(0, 3);
                key = key.substring(3);
                if (key.length() > 0) {
                    licenseKey += "-";
                }
            }
        } else {
            System.out.println("GenKey::generateLicenseFrom() format: mm/dd/yyyy");
        }
        
        return licenseKey;
    }

    public static void main(String[] args) {
        System.out.println("Genkey::main() ");
        //String expdate = "03/22/2016"; // 705-161-042-311
        //String expdate = "04/21/2018"; // 775-161-042-055
        //String expdate = "04/23/2020"; // 045-171-308-167
        //String expdate = "12/31/2019"; // 385-168-955-015 Kofi Adjei
        //String expdate = "08/31/2020"; // 3 months ending 31/08/2020
        //String expdate = "06/30/2021"; // 3 months ending 31/08/2020
        String expdate = "31/12/2022"; // 305-177-116-551 ending 31/12/2022        
        System.out.println("Genkey::main() expdate: " + expdate);
        String licenseKey = GenKey.generateLicenseFrom( expdate );
        System.out.println(licenseKey);
        
        // String expdate = "03/07/2014"; // 115-155-918-471
        // String expdate = "10/23/2014"; // 745-156-101-767
        // expdate Mon Sep 30 00:00:00 EDT 2013
        // expdate = "9/30/2013";  // 185-153-517-959   
    }
}
