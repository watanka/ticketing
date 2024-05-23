from locust import HttpUser, Locust, TaskSet, task, between
import time

class QueueTaskSet(TaskSet):
    def on_start(self):
        # Initialize user ID
        self.user_id = 1

    @task
    def queue_user(self):
        # Send POST request to queue user
        response = self.client.post("/queue", json={"userId": self.user_id})
        print(f"Queued user ID: {self.user_id}")

        self.user_id += 1

        # If we've queued all users, stop the user
        if self.user_id > 60:
            self.interrupt()

class TokenStatusCheckTaskSet(TaskSet):
    def on_start(self):
        # Initialize user ID
        self.user_id = 1
        wait_time = between(60, 60)  # Wait for 1 minute before starting the checks

    @task
    def check_token_status(self):
        for user_id in range(1, 61):
            response = self.client.get(f"/tokens", params={'userId' : user_id})
            json_data = response.json()

            if user_id <= 50:
                assert json_data['status'] == "ACTIVE", f"User {user_id} expected ACTIVE but got {json_data['status']}"
            else:
                assert json_data['status'] == "WAIT", f"User {user_id} expected WAIT but got {json_data['status']}"
                assert 1 <= json_data['waitingNum'] <= 10, f"User {user_id} has waitingNum out of range: {json_data['waitingNum']}"

            print(f"Checked user ID: {user_id}, Status: {json_data['status']}, WaitingNum: {json_data.get('waitingNum', 'N/A')}")

class MyUser(HttpUser):
    tasks = [QueueTaskSet, TokenStatusCheckTaskSet]
    wait_time = between(1, 1)  # Wait time between tasks
    host = "http://localhost:8080"  # Specify the base URL here