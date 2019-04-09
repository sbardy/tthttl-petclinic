FROM centos
RUN yum install -y java

ARG WORKDIR=/usr/bin/customer-service
WORKDIR ${WORKDIR}
ADD /target/customer-service-0.0.1-SNAPSHOT.jar ${WORKDIR}
EXPOSE 8081
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.5.0/wait /wait
RUN chmod +x /wait

#ENTRYPOINT ["java"]
#CMD ["-jar", "-Dspring.profiles.active=docker", "customer-service-0.0.1-SNAPSHOT.jar"]

CMD ["sh", "-c", "/wait && java -jar -Dspring.profiles.active=docker customer-service-0.0.1-SNAPSHOT.jar"]