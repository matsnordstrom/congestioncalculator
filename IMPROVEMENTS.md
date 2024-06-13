# Congestion Tax Calculator

## Possible future improvements

### Put to Cloud

This application would be a good fit for putting up to the cloud. This could be a way to improve availability and
flexibility.

One way could be to utilize AWS and create a simple CloudFomration stack.

1. Clean the application from REST handling and put in an AWS Lambda.
2. Create an API Gateway and link to application
3. Update application to handle tax information from external documents or similar
4. Place tax information in an e.g. AWS S3 and make application fetch information from here
5. Create a Frontend using AWS Amplify to let users easily upload their own tax information 




