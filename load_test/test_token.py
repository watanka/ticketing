from locust import Locust, LoadTestShape, HttpUser, task
import random

class TicketingApiUser(HttpUser):
    host = "http://localhost:8080"
    def on_start(self):
        self.userid = 1
    @task(10)
    def issueToken(self):

        self.client.post("/queue", json = {'userId' : self.userid}) # userId 횟수마다 증가


    @task(4) # 40%의 비율 부여
    def checkToken(self):
        self.client.get('/tokens', params = {'userId': self.userid})



