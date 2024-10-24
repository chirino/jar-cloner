## jar-mimic 

jar-mimic is a cli tool and maven plugin that 
allows you to mimic an existing jar by extracting the jar's 
structure such as its entry order, timestamps, compressions setttings, 
etc and then using it to create a new jar that will have the same
settings.


Build it with:

```shell
./mvnw clean install
```

## Using

You first extract the structure/metadata of the jar file into a yaml file:

```shell
java -jar cli/target/quarkus-app/quarkus-run.jar extract some.jar contents.yaml
```

The `contents.yaml` file will look something like this:

```yaml
---
comment: "PACK200"
entries:
- name: "META-INF/MANIFEST.MF"
  time: 1689775860000
  extra: !!binary |-
    /soAAA==
  method: 8
- name: "/org/example/Main.class"
  time: 1689775674000
  method: 8
``` 

You can now use this contents.yaml file to create a new jar file with the same structure.  If the files
being added to the new jar file are the same as the original jar file, the new jar file will be byte for byte identical
as the original jar file.

The following command will create the `new.jar` using the data in the directory `directoryWithFiles` and the metadata in the `contents.yaml` file.
```shell
java -jar cli.target/quarkus-app/quarkus-run.jar create -C directoryWithFiles contents.yaml new.jar 
```

## Maven Plugin

To use with maven, first run the following to extract the metadata from a `some.jar` file:

```shell
mvn io.github.chirino:jar-mimic-maven-plugin:1.0.0-SNAPSHOT:extract -D jar-mimic.jar=some.jar
```

It will generate a `src/main/jar-mimic.yaml` file.

After that, assuming your `target/classes` directory contains the files you want to package into a new jar file, you can run:

```shell
mvn io.github.chirino:jar-mimic-maven-plugin:1.0.0-SNAPSHOT:repackage
```