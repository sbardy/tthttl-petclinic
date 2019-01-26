FROM centos
RUN yum install -y java

ARG WORKDIR=/usr/bin/config-service
WORKDIR ${WORKDIR}
ADD /target/config-service-0.0.1-SNAPSHOT.jar ${WORKDIR}
EXPOSE 8888
ENTRYPOINT ["java"]
CMD ["-jar", "config-service-0.0.1-SNAPSHOT.jar"] 
