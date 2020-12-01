# Using JMeter

## Installation

Download JMeter on this [website](https://jmeter.apache.org/download_jmeter.cgi). Run `bin/jmeterw.cmd` on Windows to start JMeter.

## Used configuration

To see the configuration used for the tests, load the file in the `jmeter` folder. You can run it, but you will have loads of errors as you won't have the same UUID's used.

## Creating a configuration

- Use [BlazeMeter](https://chrome.google.com/webstore/detail/blazemeter-the-continuous/mbopgmdnpcbohhpnfglgohlbhfongabi)

- On the website, start a recording. Perform actions you want to stress, then stop the recording

- Click on edit, then save the test as a JSON

- Use this [website](https://converter.blazemeter.com/) to convert the file to a JMX one

- Open this file with JMeter

## Mesuring

You are welcomed to copy mesures from the provided files. Here are some useful ones:

- Response Assertation (select code, then contains, and use "20*")

- Time Assertation (~ 500ms should be a good start)

- Agregate Graph

- Summary Table

- Response Time Table