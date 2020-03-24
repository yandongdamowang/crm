FROM harbor.ls.com/library/maven:latest
ADD ./target/crm9-release.zip /
WORKDIR /
EXPOSE 80
CMD java -Xverify:none -cp /crm9/config:/crm9/lib/* Application
