# utils4j

My personal Java utility library of commonly used methods. Here is a list:

```
arrayToString(String[] command)
```

Returns array as single string with whitespace delimiter.

```
parseInt(String val, int defaultValue)
```

Convert String value into integer. If it fails, return default value.

```
parseBool(String val, boolean defaultValue)
```

Convert String value into boolean. If it fails, return default value.


### File handling

```
fileExists(String file)
```

Return true if file (absolute path) exists.


### Encryption

```
getMd5Checksum(String s)
```

Return MD5 Checksum of a string.

```
getMd5Checksum(File f)
```

Return MD5 Checksum of a whole file.

```
getSha1(String file)
```

Get SHA1 checksum of a file.


### Networking

```
getLocalIp()
```

Return IP address of the local host.







