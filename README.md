Practice Project to learn AWS - 

1. Configuring property file from a file uploaded on S3

Local setup - 
- Step 1 - Adding dependency to connect to AWS
- Step 2 - Configure user's AWS Credentials using aws cli
- Step 3 - Create a Configuration class to load the spring Environment from the property uploaded at a path on S3
- Step 4 - Set up env variables and the run the application and run the application

branch - s3EnvFile

2. Spring Boot’s Application Events
During the startup of an a spring application there are several stages and at the start of each an event is triggered. By listening to these events a finegrained control over teh application can
be achieved.

 ApplicationEnvironmentPreparedEvent - For modifying property values, adding or removing configuration sources, activating specific profiles,
 or applying custom logic based on the environment’s state.

Case - Loading the spring Environment from a file uploaded on S3 and picking the file based on the profile the application is ran with.

-  Step 1 - Add listener at application startup, that listens for ApplicationEnvironmentPreparedEvent.
-  Step 2 - Override the onApplicationEvent method of ApplicationEnvironmentPreparedEvent to load the env using the property file from s3, use the active profile set with which the application
  is ran in the path of the S3 file location.
-  Step 3 - Create a config class that connects to S3 and gets the file.
-  Step 4 - verify by setting different things in the file for different envs.

branch - applicationStartupEvents
