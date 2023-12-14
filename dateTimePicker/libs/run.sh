cd `dirname $0`

export CLASSPATH=$CLASSPATH:./JPlanner.jar
mkdir ./classes
javac -d ./classes ./src/*.java
cd classes
java -cp ./:./../JPlanner.jar MainWindow
cd ..
echo “Done”