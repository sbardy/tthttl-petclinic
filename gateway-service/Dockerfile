FROM centos
RUN yum install -y java

ARG WORKDIR=/usr/bin/gateway-service
WORKDIR ${WORKDIR}
ADD /target/gateway-service-0.0.1-SNAPSHOT.jar ${WORKDIR}
EXPOSE 8080
ENTRYPOINT ["java"]
CMD ["-jar", "gateway-service-0.0.1-SNAPSHOT.jar"]