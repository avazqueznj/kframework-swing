#!/bin/sh

#           This source code is part of the KFRAMEWORK  (http://k-framework.sourceforge.net/)
#           Copyright (C) 2001  Alejandro Vazquez, Ke Li
#           Feedback / Bug Reports vmaxxed@users.sourceforge.net
#           
#           This library is free software; you can redistribute it and/or
#           modify it under the terms of the GNU Lesser General Public
#           License as published by the Free Software Foundation; either
#           version 2.1 of the License, or (at your option) any later version.
#           
#           This library is distributed in the hope that it will be useful,
#           but WITHOUT ANY WARRANTY; without even the implied warranty of
#           MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
#           Lesser General Public License for more details.
#           
#           You should have received a cp of the GNU Lesser General Public
#           License along with this library; if not, write to the Free Software
#           Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
#           

# ---------------------------------------------------------------------------------------------------------------
#     Make a keystore and a key ( 1 time, or when new key required )
#
# 	keytool  -genkey -keyalg rsa -keysize 1024 -alias KFrameworkKey01 -validity 3650 -keystore KFrameworkStore00
#


# If you get a "invalid magic" error, make sure the default jarsigner 
# is the one corresponding to the JAVA SDK




die () {
    echo >&2 "$@"
    exit 1
}

[ "$#" -eq 1 ] || die "Sintax Error: 1 JDK bin path argument required (ex InstallClient.sh /usr/java/jdk1.6.0_22/bin ), $# provided"

echo "KFramework client installer v1.0"
echo "2011 (c) Alejandro Vazquez"
echo "--------------------------------"

echo "* Erase all previous stuff..."



rm KFrameworkBase.jar
rm KFrameworkClient.jar
rm KFrameworkServer.jar 
rm Sample2ProblemDomainComponent.jar
rm Sample2Client1.jar

rm activation-1.1.jar
rm jcalendar-1.3.2.jar
rm jcommon-1.0.0.jar
rm jfreechart-1.0.0.jar
rm swing-layout-1.0.3.jar
rm openjpa-2.0.0.jar

rm xmlpull-1.1.3.1.jar
rm xpp3_min-1.1.4c.jar
rm xstream-1.4.3.jar


echo "* Update all libraries... "
cp ..\..\..\target\Sample2Server1\WEB-INF\lib\KFramework3Base-3.1.jar                       KFrameworkBase.jar
cp ..\..\..\target\Sample2Server1\WEB-INF\lib\KFramework3Client-3.1.jar                     KFrameworkClient.jar
cp ..\..\..\target\Sample2Server1\WEB-INF\lib\KFramework3Server-3.1.jar                     KFrameworkServer.jar
cp ..\..\..\target\Sample2Server1\WEB-INF\lib\Sample2ProblemDomainComponent-3.1.jar         Sample2ProblemDomainComponent.jar
cp ..\..\..\..\Sample2Client1\target\Sample2Client1-3.1.jar                                 Sample2Client1.jar

cp ..\..\..\target\Sample2Server1\WEB-INF\lib\activation-1.1.jar                            activation-1.1.jar
cp ..\..\..\target\Sample2Server1\WEB-INF\lib\jcalendar-1.3.2.jar                           jcalendar-1.3.2.jar
cp ..\..\..\target\Sample2Server1\WEB-INF\lib\jcommon-1.0.0.jar                             jcommon-1.0.0.jar
cp ..\..\..\target\Sample2Server1\WEB-INF\lib\jfreechart-1.0.0.jar                          jfreechart-1.0.0.jar
cp ..\..\..\target\Sample2Server1\WEB-INF\lib\swing-layout-1.0.3.jar                        swing-layout-1.0.3.jar
cp ..\..\..\target\Sample2Server1\WEB-INF\lib\openjpa-2.0.0.jar                             javax.persistence.jar

cp ..\..\..\target\Sample2Server1\WEB-INF\lib\xmlpull-1.1.3.1.jar                           xmlpull-1.1.3.1.jar
cp ..\..\..\target\Sample2Server1\WEB-INF\lib\xpp3_min-1.1.4c.jar                           xpp3_min-1.1.4c.jar
cp ..\..\..\target\Sample2Server1\WEB-INF\lib\xstream-1.4.3.jar                             xstream-1.4.3.jar

# sign all 
echo "* Signing libraries..."


$1/jarsigner  -keystore KFrameworkStore00  -storepass password  -keypass password    KFrameworkBase.jar                    KFrameworkKey01  
$1/jarsigner  -keystore KFrameworkStore00  -storepass password  -keypass password    KFrameworkClient.jar             	    KFrameworkKey01  
$1/jarsigner  -keystore KFrameworkStore00  -storepass password  -keypass password    KFrameworkServer.jar      			KFrameworkKey01  
$1/jarsigner  -keystore KFrameworkStore00  -storepass password  -keypass password    Sample2ProblemDomainComponent.jar     KFrameworkKey01  
$1/jarsigner  -keystore KFrameworkStore00  -storepass password  -keypass password    Sample2Client1.jar                    KFrameworkKey01  

$1/jarsigner  -keystore KFrameworkStore00  -storepass password  -keypass password    activation-1.1.jar  	                    KFrameworkKey01  
$1/jarsigner  -keystore KFrameworkStore00  -storepass password  -keypass password    jcalendar-1.3.2.jar  	                    KFrameworkKey01 
$1/jarsigner  -keystore KFrameworkStore00  -storepass password  -keypass password    jcommon-1.0.0.jar	                        KFrameworkKey01 
$1/jarsigner  -keystore KFrameworkStore00  -storepass password  -keypass password    jfreechart-1.0.0.jar	                    KFrameworkKey01 
$1/jarsigner  -keystore KFrameworkStore00  -storepass password  -keypass password    swing-layout-1.0.3.jar  	                KFrameworkKey01 
$1/jarsigner  -keystore KFrameworkStore00  -storepass password  -keypass password    javax.persistence.jar  	                KFrameworkKey01 

$1/jarsigner  -keystore KFrameworkStore00  -storepass password  -keypass password    xmlpull-1.1.3.1.jar	                    KFrameworkKey01 
$1/jarsigner  -keystore KFrameworkStore00  -storepass password  -keypass password    xpp3_min-1.1.4c.jar  	                    KFrameworkKey01 
$1/jarsigner  -keystore KFrameworkStore00  -storepass password  -keypass password    xstream-1.4.3.jar  	                    KFrameworkKey01 

 


echo "... all done."

 