<<<<<<< HEAD
FROM openjdk:8-jre
COPY ./target/crm-release/crm /crm
=======
FROM harbor.ls.com/library/maven:latest
COPY ./target/crm-release/crm  /crm
>>>>>>> 2f2b253153c22870096aa02996a5fd1dc9fe31da
WORKDIR /
EXPOSE 80
CMD java -Xverify:none -cp /crm/config:/crm/lib/* com.linksame.crm.Application
