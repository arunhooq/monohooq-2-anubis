FROM  python:3-slim
COPY ./requirements.txt /python-app/requirements.txt
WORKDIR /python-app
RUN pip install -r requirements.txt

COPY ./helloworld.py /python-app/helloworld.py

EXPOSE 80

ENTRYPOINT ["python", "helloworld.py"]