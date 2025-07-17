# Usamos imagen oficial de Maven con JDK 17
FROM maven:3.9.6-eclipse-temurin-17

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos todo el proyecto al contenedor
COPY . .

# Declaramos la variable de entorno para el token (puede ser sobreescrita al ejecutar)
ENV SONAR_TOKEN=sqp_92122df037c19e56a8ba20ca309a14c0bfbeabee

# Comando para compilar, probar y enviar análisis a SonarQube
CMD mvn clean verify sonar:sonar \
  -Dsonar.projectKey=ProyectoJunitMockito \
  -Dsonar.projectName='ProyectoJunitMockito' \
  -Dsonar.host.url=http://sonarqube:9000 \
  -Dsonar.token=$SONAR_TOKEN
