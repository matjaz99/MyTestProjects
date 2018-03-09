# XML Generator of IMS measurements

#### Configuration

Configuration is in config.xml file:

- period - interval in seconds when new measurement is taken (and written to xml)
- outputDirectory - path to directory where xml files are generated
- fakeMeas - if true some dummy measurements are added to S-CSCF node just to get a desired number of measurements (about 70). These 'fake' measurements are BGCF sessions and Presence service which have nothing to do with real S-CSCF node.

Configuration of nodes consists of elementType, nodeName and nodeId. Many <nodes> elements can be added. Each node will simulate measurements according to type of element: BGCF, MGCF, I-CSCF, S-CSCF, P-CSCF, TAS and VM.

Configuration of InfluxDB client consists of:
- enabled - client is enabled if true
- url - url to access database


#### v1.4
- includes all IMS standard measurements on BGCF, MGCF, I/S/P-CSCF, TAS and VM

#### v2.0
- added InfluxDB support (using http requests)
- measurements are stored in InfluxDB (and displayed by Grafana)


