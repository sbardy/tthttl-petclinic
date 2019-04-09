FROM centos
RUN yum install -y java

ARG WORKDIR=/usr/bin/visit-service
WORKDIR ${WORKDIR}
ADD /target/visit-service-0.0.1-SNAPSHOT.jar ${WORKDIR}
EXPOSE 9000
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.5.0/wait /wait
RUN chmod +x /wait

#ENTRYPOINT ["java"]
#CMD ["-jar", "-Dspring.profiles.active=docker", "visit-service-0.0.1-SNAPSHOT.jar"]

CMD [ "sh", "-c", "/wait && java -jar -Dspring.profiles.active=docker visit-service-0.0.1-SNAPSHOT.jar" ]