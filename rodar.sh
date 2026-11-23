#!/bin/bash
# Roda o sistema no Git Bash / MSYS2 (Windows), usando o Java da Eclipse Adoptium.
cd "$(dirname "$0")"
JDK="$(ls -d /c/Program\ Files/Eclipse\ Adoptium/jdk-*/bin 2>/dev/null | head -1)"
if [ -z "$JDK" ]; then
  echo "Java (JDK) nao encontrado em C:\\Program Files\\Eclipse Adoptium"
  exit 1
fi
mkdir -p out dados
"$JDK/javac.exe" -encoding UTF-8 -d out -cp lib/derby-10.14.2.0.jar src/empresa/*.java src/empresa/*/*.java || exit 1
"$JDK/java.exe" -cp "out;lib/derby-10.14.2.0.jar" empresa.Main
