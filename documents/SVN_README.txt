IN CASE YOU ARE PLANNING TO IMPORT/COMMIT FILES FROM BOTH LINUX AND WINDOWS
(AND MAC) MACHINES PLEASE READ THIS FILE.

SVN can be set-up to tackle the well-known problem caused by the different
end-of-line styles used by Windows/Linux/Mac (see http://en.wikipedia.org/wiki/Newline 
for background info on this). For instance, say that you commit a text file
from a Windows machine and later update this file to a Linux machine and
tries to open this text file you may (depending on the Linux editor you
are using) see an unexpected result. 

To avoid this please do the following:


  On a Windows machine:  Copy the file named 'config' (in the same directory
                         as this file) to the directory:

                           C:\Documents and Settings\'local user name'\Application Data\Subversion

                         where you need to substitute 'local user name' with 
                         the login name of the user who installed SVN in the first place.


  On a Linux machine:    Copy the file named 'config' (in the same directory
                         as this file) to the directory:

                           /home/'username'/.subversion/config

                         where 'username' is your login name on the machine.                        


  On a Mac machine:      Don't know how to do this yet, but the SVN 
                         documentation (referenced below) will tell you this.


For more information about how to configure the SVN 'config' file see
the SVN documentation: http://svnbook.red-bean.com. 
