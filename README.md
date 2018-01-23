# MyTestProjects


## camel
Apache Camel for copying files and FTP client

## derby
Storing data into Apache Derby DB

## influxdb
Storing time-series data into InfluxDB

## javase
Basics of Java SE

## kafka
Kafka...

## mariadb
Storing data into Maria DB

## mqtt
MQTT publish and subscribe example

## mysql
Storing data into MySQL

## mytrayicon
Tray Icon (Swing)

## restws
REST web service and client


## Import jar into local repository:

$ mvn install:install-file -Dfile=/Users/matjaz/Downloads/simple-logger-1.6.1.jar -DgroupId=si.matjazcerkvenik.simplelogger -DartifactId=simple-logger -Dversion=1.6.1 -Dpackaging=jar




# Markdown syntax (.md)


**This is bold text**

*This text is italic*

~~This text should be strike-through~~

**This text is _extremely_ important**

If you really need \*stars\*

> This is quote

List of items:
- A
- B
- C


Code formating

```
function test() {
  console.log("notice the blank line before this function?");
}
```

```ruby
require 'redcarpet'
markdown = Redcarpet.new("Hello World!")
puts markdown.to_html
```


Links:

This site was built using [GitHub Pages](https://pages.github.com/).


Relative links:

[JAX-WS examples](restws/README.md)


Task lists:

- [x] Finish my changes
- [ ] Push my commits to GitHub
- [ ] Open a pull request


Referencing other people and emojis:

@matjaz99 :+1: This project is great! :bowtie:

Find more Emojis [Emoji Cheat Sheet](http://emoji-cheat-sheet.com/).
