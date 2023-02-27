FROM joengenduvel/jre17 as dev

ENV ENV LANG C.UTF-8

RUN ln -snf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo 'Asia/Shanghai' > /etc/timezone

COPY *.jar /home/luckydraw.jar

WORKDIR /home

EXPOSE 18080

CMD ["sh", "-c", "rm /dev/random && ln -s /dev/urandom /dev/random && java -jar luckydraw.jar -Xms256m -Xmx512m --spring.profiles.active=dev"]


FROM dev as prod

CMD ["sh", "-c", "rm /dev/random && ln -s /dev/urandom /dev/random && java -jar luckydraw.jar -Xms512m -Xmx1g --spring.profiles.active=prod"]