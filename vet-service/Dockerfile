FROM centos
RUN yum install -y java

ARG WORKDIR=/usr/bin/vet-service
WORKDIR ${WORKDIR}
ADD /target/vet-service-0.0.1-SNAPSHOT.jar ${WORKDIR}
EXPOSE 8090
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.5.0/wait /wait
RUN chmod +x /wait

#ENTRYPOINT ["java"]
#CMD ["-jar","-Dspring.profiles.active=docker", "vet-service-0.0.1-SNAPSHOT.jar"]

CMD ["sh", "-c", "/wait && java -jar -Dspring.profiles.active=docker vet-service-0.0.1-SNAPSHOT.jar"]