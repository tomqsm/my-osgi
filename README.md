# my-osgi
This project is a collection of code example supporting my learning of OSGi.
## How to refresh a deployed and runing bundle.
see: http://stackoverflow.com/a/34313556/1398360
## Service null after an update?
e.g bundle test-api exports Export-Package:biz.letsweb.osgi.test.api and has private package with the implementation Private-Package:biz.letsweb.osgi.test.impl When the bundle is exporting a package and also imports it Import-Package:org.osgi.framework;version="1.8",biz.letsweb.osgi.test.api, the problem is solved. ref: http://stackoverflow.com/a/19223538/1398360
