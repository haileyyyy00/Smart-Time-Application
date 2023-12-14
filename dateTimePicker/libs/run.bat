set CLASSPATH=%CLASSPATH%;JPlanner.jar
md classes
javac -d classes src\*.java
cd classes
java -cp ./;..\JPlanner.jar MainWindow
cd..
pause..