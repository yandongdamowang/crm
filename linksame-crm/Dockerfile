FROM harbor.ls.com/library/maven:latest
ADD ./target/*.zip  /
RUN ls ./
WORKDIR /
EXPOSE 80
CMD java -Xverify:none -cp /crm/config:/crm/lib/* com.linksame.crm.Application
