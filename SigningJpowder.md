# Reason #

Jpowder is deployed as a Java web start application. In order for this application to work as a 'normal' application, which e.g. can access files from local disk, it must be signed.

# Requirements #

To sign jars for use in web start you need JDK installed, which includes jarsigner.exe located in the jdk bin directory.

# Step by step #

  * Package the application into a jar (from Netbeans e.g. right-click Standalone and select clean and build which will create the jar file Standalone\dist\Jpowder.jar)
  * The default keystore file used for signing Jpowder is located at trunk\code\Signing\_Jpowder\Jpowder.jks. This certificate was created using the following command from the JDK bin directory: keytool -genkey -alias Jpowder -keystore Jpowder.jks -keypass Jpowder -dname "cn=Jpowder" -storepass Jpowder .
  * To sign Jpowder.jar from the JDK bin run the command:

jarsigner.exe –keystore Jpowder.jks Jpowder.jar Jpowder

where the keypass is Jpowder.