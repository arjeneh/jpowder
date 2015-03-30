# Introduction #
For a demonstration of how to use JpowderApplet to create interactive web content see http://www.jpowder.org/JpowderApplet.html.

The demonstration below require that you access to server where you can upload files. If you don't have access to such a server in your department then on [AppletInstallationTutorial](AppletInstallationTutorial.md) it is demonstrated how to use an Internet fileshare.

This page explain how to create such content.

  1. Load data into Jpowder. Optionally adjust how the data are displayed until the region or features of interest of the data are highlighted.
  1. Right click over the chart displaying the data and select Save As... then Jpowder-Applet Format as illustrated below. Note you must keep the extension of the saved file to .ser. http://jpowder.googlecode.com/svn/wiki/pictures/demo_save_applet_format.PNG
  1. Assume the saved file in 2) is called "data.ser". Store this file initially together with (right-click over links to download) [JpowderApplet.jar](http://jpowder.googlecode.com/files/JpowderApplet.jar) and [this demo HTML page](http://jpowder.googlecode.com/svn/wiki/html/simple_demo_JpowderApplet.html).
  1. Open the demo HTML page in a text editor and the line of interest is: ```xml
<applet code="JpowderApplet.class" codebase = "." name="Jpowder" alt="JpowderAppletTest" width="510" height="311" align="middle" id="Jpowder" archive="JpowderApplet.jar"><PARAM name="PATH" value="data.ser">

Unknown end tag for &lt;/applet&gt;

```. Note codebase="." is the path of JpowderApplet.jar here specified with the relative path "." which means current directory.
  1. Open HTML demo page. If this opens Internet Explorer you will get a warning message along the line of "To help protect your security...", right click on message and allow blocked content.
  1. If you have access to the server displaying your web pages (or know has), then to make the content in 5) public, simply move the folder in 5) onto the server. When a user opens the public demo HTML page the page will be the same as in 5) but without any warnings as long as this user has Java installed.

Optional. In 5) to get rid of the warning when viewing the demo page locally sign JpowderApplet first, see [here](SigningJpowder.md) for signing Jpowder and use same procedure to sign JpowderApplet.jar.