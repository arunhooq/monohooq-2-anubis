#!/bin/bash

 ./functionbeat  -v -e -d "*" $1 $2 &> $3

if grep -q 'AlreadyExistsException\|No updates are to be performed\|deploy successful\|update successful' $3
then

    echo "::set-output name=functionbeat_status::success"

fi