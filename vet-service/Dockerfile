FROM centos
RUN yum install -y java

ARG WORKDIR=/usr/bin/vet-service
WORKDIR ${WORKDIR}
ADD /target/vet-service-0.0.1-SNAPSHOT.jar ${WORKDIR}
EXPOSE 8090
ENTRYPOINT ["java"]
CMD ["-jar", "vet-service-0.0.1-SNAPSHOT.jar"]