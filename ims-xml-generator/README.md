# XML Generator of IMS measurements

#### Configuration

Configuration is in config.xml file:

- period - interval in seconds when new measurement is taken (and written to xml)
- outputDirectory - path to directory where xml files are generated
- fakeMeas - if true some dummy measurements are added to S-CSCF node just to get a desired number of measurements (about 70). These 'fake' measurements are BGCF sessions and Presence service which have nothing to do with real S-CSCF node.

Configuration of nodes consists of elementType, nodeName and nodeId. Many <nodes> elements can be added. Each node will simulate measurements according to type of element: BGCF, MGCF, I-CSCF, S-CSCF, P-CSCF, TAS and VM.



#### v1.4
- includes all IMS standard measurements on BGCF, MGCF, I/S/P-CSCF, TAS and VM


Monitoring HSS nodes

PMON retrieves all kind of nodes from MNS with method getNeNodes.
PMON should monitor only nodes with product category: IA, IE and IC.

HSS nodes cannot be identified by product category, since their product category is AP, the same as many other Iskratel products.
To overcome this problem, PMON should implement a possibility to manually add additional nodes under performance monitoring (by nodeId in MNS).
