FROM livingobjects/jre8
MAINTAINER smile_jt@qq.com
RUN ["mkdir","-p","/workplace"]
COPY service-other.jar /workplace/app.jar
WORKDIR /workplace/
CMD ["java","-jar","-Xms128m","-Xmx512m","-XX:MaxPermSize=128m","-Duser.timezone=GMT+08","app.jar"]
EXPOSE 8080