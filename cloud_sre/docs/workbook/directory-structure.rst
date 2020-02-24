==============================
Repository directory structure
==============================

Applications deployed by the pipeline need to include the following directory structure in the monohooq source control repository::

    /
    ├── <other repository files>
    ├── services
    |   ├── miyazaki
    |   |   |
    |   |   |-- infra-deploy
    |   |   |-- code-deploy
    |   |   |-- config-deploy
    |   |   |-- metrics-deploy
    |   |   |-- functionbeat-deploy


* infra-deploy - infrastructure
* code-deploy  - code
* config-deploy - configuration deploy
* metrics-deploy - metrics
* functionbeat-deploy - logging

infra-deploy
============

code-deploy
===========

config-deploy
=============

metrics-deploy
==============

functionbeat-deploy
===================
