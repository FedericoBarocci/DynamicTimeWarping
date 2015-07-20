#!/bin/bash

params="data/filter_eventlog.csv 2 24"

apache_commons_path="libs/commons-lang3-3.4.jar"
guice_path="libs/guice-4.0.jar:libs/aopalliance-1.0.jar:libs/javax.inject.jar"

java -cp ${guice_path}:${apache_commons_path}:bin logAnalizer.main.Main ${params}
