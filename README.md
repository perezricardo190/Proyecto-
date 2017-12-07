# Proyecto-
Inlocate es una plataforma de posicionamiento interior para teléfonos inteligentes Android. Esta plataforma cuenta con dos aplicaciones de Android. Uno para la fase Offline o de aprendizaje (Logger) y la otra aplicación para la fase Online (Tracker).


###Componentes


•	InlocateLogger: Código Fuente más librerías
•	InlocateTracker: Código Fuente más librerías 
•	RM Server: código fuente 
•	Documentation: https://drive.google.com/file/d/1bg47bZ7-B0e-Zdoz2OF3kkZuI3-bwlLb/view?usp=sharing

##Instalación de las aplicaciones Inlocate Logger y Tracker
Una vez creados  los archivos .apk  copie en la tarjeta SD del Android. Inicie una aplicación de administrador de archivos en el teléfono inteligente. O en caso de que use Android Studio, simplemente ejecútelos.
El servidor de RadioMap se puede ejecutar en cualquier PC con Java.

###Preparación del mapa del plano para Inlocate

Para preparar el mapa que será insertado en la apps Logger y Trackerse se necesita  un mapa del plano en formato jpeg (por ejemplo, mymap.jpg) y un archivo de configuración asociado (por ejemplo, mymap.config) que mantenga el ancho y la altura reales del plano en metros (Nota: el nombre del archivo antes de la extensión de archivo debe ser la misma para ambos archivos. Se incluye un ejemplo en la carpeta \ Ejemplo \ Mapa que se puede modificar apropiadamente con sus propios valores). Ambos archivos deben guardarse juntos.
Nota: los valores reales para las dimensiones del plano no son tan importantes; los valores aproximados (o incluso ficticios) podrían usarse en su lugar y al hacerlo no afectarán el rendimiento de los algoritmos de posicionamiento. Los valores reales solo son necesarios para ver ubicaciones significativas (X, Y) en las aplicaciones Logger y Tracker.

##InlocateLogger
Este proyecto se maneja con la clase WifiManager. Esta clase proporciona la API principal para administrar todos los aspectos de la conectividad Wi-Fi.
Dentro del paquete Inlocaterssloger se manejan las clases que proporcionan los parámetros de los Ap’s detectado, para almacenar las huellas en un archivo (fingerprint) asimismo desde la clase RSSLogger  se obtiene la lista que almacena los resultado de los [AP’s es  List<ScanResult> wifiList = wifi.getScanResults()]. A partir de esta lista se maneja el filtrado de AP’s, ya sea por por el SSID o BSSID.
Dentro del paquete Uploading está loa clase que maneja el envío de los datos al servidor.
##InlocateTracker
Dentro del paquete Downlading se codifica la conexión con el server para la descarga de un radiomap basado en las huellas tomado por la app Inlocate logger.
El paquete FileBrowser maneja el almacenado local del archivo (radiomap) de manera que pueda encontrar la ruta en la que el server enviará el archivo.
Dentro del paquete Inlocate están las clases de los algoritmos de posicionamiento y configuraciones.
RM server
Esta carpeta contiene todas las clases que conforman el servidor, para la recepción (desde Inlocate logger)  y envío (Inlocate Tracker) de la información para la ubicación. Este proyecto debe importarse en netbeans



Nota: las funciones y métodos más relevantes están comentadas de manera más detalladas dentro del código.



