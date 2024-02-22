[Instalación]
curl -Ls "https://repo1.maven.org/maven2/com/madgag/bfg/1.14.0/bfg-1.14.0.jar" -o /tmp/bfg-1.14.0.jar
sudo install -d /opt/bfg && sudo mv /tmp/bfg-1.14.0.jar /opt/bfg/bfg-1.14.0.jar
sudo sh -c 'cat > /usr/local/bin/bfg << "EOT"
#!/bin/bash
java -jar /opt/bfg/bfg-1.14.0.jar "$@"
EOT'
sudo chmod +x /usr/local/bin/bfg
bfg --version

[Ejecución]

- Caso de reemplazar un bug:

1. Se crea un archivo passwords.txt con los textos a reemplar del código.
-----BEGIN RSA PRIVATE KEY-----
-----END RSA 
PRIVATE KEY-----

2. bfg --replace-text passwords.txt <nombre de la carpeta>

3. cd <nombre de la carpeta>

4. git reflog expire --expire=now --all && git gc --prune=now --aggressive

5. git push --force

[Nota]
Hacer en todas las ramas con el bug ``` git push --force  ```
