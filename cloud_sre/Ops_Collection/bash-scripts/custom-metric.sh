#!/bin/sh

# Create cloudwatch directory and go to directory
mkdir ~/cloudwatch-logs && cd ~/cloudwatch-logs

# Selecting package manager based on distribution
case `uname` in
  Linux )
     LINUX=1
     which yum && { sudo yum install -y perl-Switch perl-DateTime perl-Sys-Syslog perl-LWP-Protocol-https perl-Digest-SHA.x86_64 unzip; }
     ;;
  Darwin )
     DARWIN=1
     ;;
  * )
     # Handle AmgiaOS, CPM, and modified cable modems here.
     ;;
esac


# Get the cloudwatch monitoring script
curl https://aws-cloudwatch.s3.amazonaws.com/downloads/CloudWatchMonitoringScripts-1.2.2.zip -O

# Unzip cloudwatch monitoring script
unzip CloudWatchMonitoringScripts-1.2.2.zip && rm CloudWatchMonitoringScripts-1.2.2.zip

# Set the cloudwatch monitoring crontab to 5 minute
crontab -l | { cat; echo "*/5 * * * * ~/cloudwatch-logs/aws-scripts-mon/mon-put-instance-data.pl --mem-used-incl-cache-buff --mem-util --mem-used --mem-avail --disk-space-util --disk-space-avail --disk-path=/ --from-cron"; } | crontab -


