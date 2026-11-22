#!/bin/sh
cd "$(dirname "$0")"
mkdir -p out dados
javac -encoding UTF-8 -d out -cp lib/derby-10.14.2.0.jar src/empresa/Main.java src/empresa/modelo/*.java src/empresa/dao/*.java || exit 1
java -cp "out:lib/derby-10.14.2.0.jar" empresa.Main
