# constrictor
Robust and useful library when working with netty and similar frameworks. Contains networking utility like reachability checks, Health Checker and specific utils for example netty pipelines.

# Build Status

|             | Build Status                                                                                                            |
|-------------|-------------------------------------------------------------------------------------------------------------------------|
| Master      | [![Build Status](https://travis-ci.org/d3adspace/constrictor.svg?branch=master)](https://travis-ci.org/d3adspace/constrictor) |
| Development | [![Build Status](https://travis-ci.org/d3adspace/constrictor.svg?branch=dev)](https://travis-ci.org/d3adspace/constrictor)    |

# Installation / Usage

**Maven repositories**
```xml
<repositories>
    <!-- Klauke Enterprises Releases -->
    <repository>
        <id>klauke-enterprises-maven-releases</id>
        <name>Klauke Enterprises Maven Releases</name>
        <url>https://repository.klauke-enterprises.com/repository/maven-releases/</url>
    </repository>
	
    <!-- Klauke Enterprises Snapshots -->
    <repository>
        <id>klauke-enterprises-maven-snapshots</id>
        <name>Klauke Enterprises Maven Snapshots</name>
        <url>https://repository.klauke-enterprises.com/repository/maven-snapshots/</url>
    </repository>
</repositories>
```

**Maven dependencies**

_Combined all module:_
```xml
<!-- All -->
<dependency>
    <groupId>de.d3adspace.constrictor</groupId>
    <artifactId>constrictor-netty</artifactId>
    <version>2.2</version>
    <scope>compile</scope>
</dependency>
```

_Netty utils:_
```xml
<!-- Netty -->
<dependency>
    <groupId>de.d3adspace.constrictor</groupId>
    <artifactId>constrictor-netty</artifactId>
    <version>2.2</version>
    <scope>compile</scope>
</dependency>
```

_TCP utils:_
```xml
<!-- TCP -->
<dependency>
    <groupId>de.d3adspace.constrictor</groupId>
    <artifactId>constrictor-tcp</artifactId>
    <version>2.2</version>
    <scope>compile</scope>
</dependency>
```

_UDP utils:_
```xml
<!-- UDP -->
<dependency>
    <groupId>de.d3adspace.constrictor</groupId>
    <artifactId>constrictor-udp</artifactId>
    <version>2.2</version>
    <scope>compile</scope>
</dependency>
```
