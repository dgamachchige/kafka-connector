# Event Hub with Kafka and Spring Boot Integration

This project demonstrates how to integrate Azure Event Hub with Kafka protocol using Terraform and a Spring Boot application.

---

## Steps to Set Up and Test

### 1. Deploy Azure Event Hub Infrastructure

Before running the Terraform script, export your Azure subscription ID as an environment variable:

```bash
export ARM_SUBSCRIPTION_ID="<YOUR_AZURE_SUBSCRIPTION_ID>"
```

Navigate to the `terraform` folder and run the following commands:

```bash
terraform init
terraform plan
terraform apply
```
This will create the necessary Azure Event Hub infrastructure.

### 2. Run the Spring Boot Application

Add eventhub connection string as env variable in your springboot run configurations.
```bash
EVENTHUB_CONNECTION_STRING=<YOUR_EVENTHUB_CONNECTION_STRING>
````

Start the Spring Boot application. Once it's running, send a POST request to the following endpoint to produce a message:
```bash
curl -X POST "http://localhost:8081/api/v1/producer/send?topic=kafka-eventhub&message=HelloKafka"
```

### 3. Verify the Output
Check the Spring Boot application logs. You should see the following message in the logs:
```bash
Received message: HelloKafka
```

### 4. Clean Up Resources
Once you are done testing, destroy the infrastructure to avoid unnecessary costs by running:
```bash
terraform destroy
```