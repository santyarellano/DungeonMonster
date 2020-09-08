build:
	javac -d bin -sourcepath res:src src/Classes/Main.java

run:
	java -cp bin:res Classes.Main