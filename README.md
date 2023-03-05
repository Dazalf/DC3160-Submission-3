**The Healthy Lifecycle Software Production (HLSP) application**

HLSP is a physical wellbeing application with a target audience of those interested in their physical 
wellbeing. It has a focus on the members of the IT industry, e.g., technical and management 
professionals, who live busy lives in the modern world, but aims to support all with an interest.

The latest code in this repository is currently being hosted via AWS Elastic Beanstalk here: http://hlsp.eu-west-2.elasticbeanstalk.com/, with the database hosted in AWS RDS, if you wish to play around with the application. 

However, if you wish to run this application locally, you need to run to download the Eclipse IDE for Web Developers, create a new project and clone this repository code into the project. As this is Spring Boot, you'll run the application by running the 'Application.java' file as a 'Java Applicaton'. This file is located as followed: src/main/java/eat3160/HLSP22/Application.java.

**What's changed? (as of 05/03)**

There have been multiple key changes to HLSP.

Users can now use their favourited or most recent exercises as input when adding an exercise.

It has transitioned from using JSP to Thymeleaf in the view layer, leveraging the natural templating and attributes. 

TSpring Data JPA has been implemented to connect and query the backend database, replacing the previous approach of using POJO's and Bean's with Entities, Services, and Reposistories, a cleaner and (in my opinion) better approach that leverages Spring Boot. 
It has now been configured to connect to a remotely hosted database rather than a local MySQL database, enabling people to contribute to the project alongside other benefits i.e., automatic backups. 

Google Analytics has been implemented to enable efficient and effective analysis of the websites activities to provide insights that align with the marketing goals of HLSP. 

**What's next? (as of 05/03)**

The implementation of recommendations based on user data, rather than a static set of articles taken from the database, and reporting based on user data.
