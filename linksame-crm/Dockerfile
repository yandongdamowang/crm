FROM harbor.ls.com/library/maven:latest
COPY ./target/crm-release/crm  /crm
WORKDIR /
EXPOSE 80
CMD java -Xverify:none -cp /crm/config:/crm/lib/* com.linksame.crm.Application
