#! /bin/bash

aws s3 cp s3://hooq-cloudsre-workbook-docs/files/cloudsre/docs/workbookdocs.tar.gz /tmp
sudo tar -xzf /tmp/workbookdocs.tar.gz -C /var/www/html/workbook