FROM centos
RUN yum install -y java

ARG WORKDIR=/usr/bin/visit-service
WORKDIR ${WORKDIR}
ADD /target/visit-service-0.0.1-SNAPSHOT.jar ${WORKDIR}
EXPOSE 9000
ENTRYPOINT ["java"]
CMD ["-jar", "-Dspring.profiles.active=docker", "visit-service-0.0.1-SNAPSHOT.jar"]