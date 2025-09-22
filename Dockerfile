FROM ubuntu:latest
LABEL authors="kimosop"

ENTRYPOINT ["top", "-b"]