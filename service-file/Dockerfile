FROM livingobjects/jre8
MAINTAINER smile_jt@qq.com
RUN ["mkdir","-p","/workplace"]
COPY service-file.jar /workplace/app.jar
WORKDIR /workplace/
CMD ["java","-jar","-Xms128m","-Xmx512m","-XX:MaxPermSize=128m","app.jar"]
EXPOSE 8080