FROM centos
RUN yum install -y java

ARG WORKDIR=/usr/bin/discovery-service
WORKDIR ${WORKDIR}
ADD /target/discovery-service-0.0.1-SNAPSHOT.jar ${WORKDIR}
EXPOSE 8761
ENTRYPOINT ["java"]
CMD ["-jar", "discovery-service-0.0.1-SNAPSHOT.jar"] 