# This is script for created service for delete all resources at playground

How to create this service (deleted for all resources aat playground):

1.    Setup AWS configure first

2.    Build the layer "make build-deleteplayground"

3.    Publish the layer and after finish take the arn "make publish-deleteplayground"

4.    Create the lambda with custom runtime and provide bootstrap

5.    add a layer arn we create before

6.    create new file and copy all.yml to the lambda code.

7.    add handler with command "awsweeper all.yml --force"

8.    run lambda

9.    create cloudwatch event for reccuring invoke
