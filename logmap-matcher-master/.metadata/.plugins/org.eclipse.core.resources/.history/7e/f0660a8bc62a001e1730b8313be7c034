    To facilitate creation of a Web Application Archive file in the required format, it is convenient to arrange the "executable" files of your web application (that is, the files that Tomcat actually uses when executing your app) in the same organization as required by the WAR format itself. To do this, you will end up with the following contents in your application's "document root" directory:

        * *.html, *.jsp, etc. - The HTML and JSP pages, along with other files that must be visible to the client browser (such as JavaScript, stylesheet files, and images) for your application. In larger applications you may choose to divide these files into a subdirectory hierarchy, but for smaller apps, it is generally much simpler to maintain only a single directory for these files.

        * /WEB-INF/web.xml - The Web Application Deployment Descriptor for your application. This is an XML file describing the servlets and other components that make up your application, along with any initialization parameters and container-managed security constraints that you want the server to enforce for you. This file is discussed in more detail in the following subsection.

        * /WEB-INF/classes/ - This directory contains any Java class files (and associated resources) required for your application, including both servlet and non-servlet classes, that are not combined into JAR files. If your classes are organized into Java packages, you must reflect this in the directory hierarchy under /WEB-INF/classes/. For example, a Java class named com.mycompany.mypackage.MyServlet would need to be stored in a file named /WEB-INF/classes/com/mycompany/mypackage/MyServlet.class.

        * /WEB-INF/lib/ - This directory contains JAR files that contain Java class files (and associated resources) required for your application, such as third party class libraries or JDBC drivers.

    When you install an application into Tomcat (or any other 2.2/2.3-compatible server), the classes in the WEB-INF/classes/ directory, as well as all classes in JAR files found in the WEB-INF/lib/ directory, are made visible to other classes within your particular web application. Thus, if you include all of the required library classes in one of these places (be sure to check licenses for redistribution rights for any third party libraries you utilize), you will simplify the installation of your web application -- no adjustment to the system class path (or installation of global library files in your server) will be necessary.

    Much of this information was extracted from Chapter 9 of the Servlet API Specification, version 2.3, which you should consult for more details.

