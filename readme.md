# ezcloud-archetype

[![HnF0M9.png](https://s4.ax1x.com/2022/02/05/HnF0M9.png)](https://imgtu.com/i/HnF0M9)

![mit](https://img.shields.io/github/license/Project-H3phaestus/ezcloud-archetype)
![contributors](https://img.shields.io:/github/contributors/Project-H3phaestus/ezcloud-archetype)
![jdk](https://img.shields.io:/badge/JDK-11-brightgreen)
![automation](https://img.shields.io:/badge/AUTOMATION-SUPPORTED-brightgreen)


| English Doc | [中文文档](./readme_chinese.md) |
|:-----------:|:---------------------------:|

A maven archetype for [EZCloud](https://github.com/Project-H3phaestus/EZCloud) scaffolding.


# :bulb: Usage

## create one project

Customize the values of the following parameters according to the situation:
```shell
mvn -U -B archetype:generate \
-DarchetypeGroupId='org.hephaestus.archetype' \
-DarchetypeArtifactId='ezcloud-archetype' \
-DarchetypeVersion='1.0.0-SNAPSHOT' \
-Dgitignore='.gitignore' \
-DprojectDescription='' \
-DgroupId='' \
-DartifactId='' \
-Dpackage='' \
-DjdkVersion='' \
-DserverPort='${random.int(1000,2000)}' \
-DnacosDevHost='' \
-DnacosTestHost='' \
-DnacosPrdHost='' \
-Dauthor='' \
-DezCloudVersion=''
```

## batch create project

We can also generate multiple projects at one time through python scripts:
> python version: 3.8


```python
import json
import os
import sys
import subprocess

# JSON configuration:
configJson = """[
    {
        "projectDescription": "my test project",
        "groupId": "com.yangyunsen.test",
        "artifactId": "ezcloud-test",
        "package": "com.yangyunsen.test.ezcloudtest",
        "jdkVersion": "11",
        "author": "clouds3n",
        "ezCloudVersion": "1.0.0-SNAPSHOT",
        "serverPort": "${random.int(1000,2000)}",
        "nacosDevHost": "10.236.101.16:30848",
        "nacosTestHost": "192.168.2.157:8848",
        "nacosPrdHost": "nacos-headless.default.svc.cluster.local:8848",
        "driverType": "0"
    },
    {
        "projectDescription": "my test project2",
        "groupId": "com.yangyunsen.test",
        "artifactId": "ezcloud-test2",
        "package": "com.yangyunsen.test.ezcloudtest2",
        "jdkVersion": "11",
        "author": "clouds3n",
        "ezCloudVersion": "1.0.0-SNAPSHOT",
        "serverPort": "${random.int(1000,2000)}",
        "nacosDevHost": "10.236.101.16:30848",
        "nacosTestHost": "192.168.2.157:8848",
        "nacosPrdHost": "nacos-headless.default.svc.cluster.local:8848",
        "driverType": "0"
    },
    {
        "projectDescription": "my test project3",
        "groupId": "com.yangyunsen.test",
        "artifactId": "ezcloud-test3",
        "package": "com.yangyunsen.test.ezcloudtest3",
        "jdkVersion": "11",
        "author": "clouds3n",
        "ezCloudVersion": "1.0.0-SNAPSHOT",
        "serverPort": "${random.int(1000,2000)}",
        "nacosDevHost": "10.236.101.16:30848",
        "nacosTestHost": "192.168.2.157:8848",
        "nacosPrdHost": "nacos-headless.default.svc.cluster.local:8848",
        "driverType": "0"
    },
    {
        "projectDescription": "my test project4",
        "groupId": "com.yangyunsen.test",
        "artifactId": "ezcloud-test4",
        "package": "com.yangyunsen.test.ezcloudtest4",
        "jdkVersion": "11",
        "author": "clouds3n",
        "ezCloudVersion": "1.0.0-SNAPSHOT",
        "serverPort": "${random.int(1000,2000)}",
        "nacosDevHost": "10.236.101.16:30848",
        "nacosTestHost": "192.168.2.157:8848",
        "nacosPrdHost": "nacos-headless.default.svc.cluster.local:8848",
        "driverType": "0"
    },
    {
        "projectDescription": "my test project5",
        "groupId": "com.yangyunsen.test",
        "artifactId": "ezcloud-test5",
        "package": "com.yangyunsen.test.ezcloudtest5",
        "jdkVersion": "11",
        "author": "clouds3n",
        "ezCloudVersion": "1.0.0-SNAPSHOT",
        "serverPort": "${random.int(1000,2000)}",
        "nacosDevHost": "10.236.101.16:30848",
        "nacosTestHost": "192.168.2.157:8848",
        "nacosPrdHost": "nacos-headless.default.svc.cluster.local:8848",
        "driverType": "0"
    }
]"""

print('> current running OS: ' + ('Windows' if os.name == 'nt' else 'Unix'))

# parse JSON configuration:
print('[ INFO] Start parsing json configuraiton...')
configArray = json.loads(configJson)

# exec dynamic commands
print('> Fetch archetype from remote mvn repository？[y/n]:')
fromRemoteFlag = input()
print(f"""[ INFO ] Start generating {len(configArray)} projects...""")
processes = []
try:
    for config in configArray:
        command = f"""
        mvn -U -B archetype:generate \
        {f'-DarchetypeCatalog=internal {chr(92)}' if fromRemoteFlag != 'y' else ''}
        -DarchetypeGroupId='org.hephaestus.archetype' \
        -DarchetypeArtifactId='ezcloud-archetype' \
        -DarchetypeVersion='1.0.0-SNAPSHOT' \
        -Dgitignore='.gitignore' \
        -DprojectDescription={config['projectDescription']} \
        -DgroupId={config['groupId']} \
        -DartifactId={config['artifactId']} \
        -Dpackage={config['package']} \
        -DjdkVersion={config['jdkVersion']} \
        -DserverPort='{config['serverPort']}' \
        -DnacosDevHost='{config['nacosDevHost']}' \
        -DnacosTestHost='{config['nacosTestHost']}' \
        -DnacosPrdHost='{config['nacosPrdHost']}' \
        -Dauthor='{config['author']}' \
        -DezCloudVersion='{config['ezCloudVersion']}'
        """
        print(command)
        processes.append(subprocess.Popen(command, shell=True))
except Exception as e:
    print(f"""[ ERROR] Failed to generate projects: {str(e)}""")
    sys.exit(1)
for process in processes:
    process.wait()
print('[ OK ] Success!')

```

# :hammer: Contributing
PRs accepted.

Small note: If editing the Readme, please conform to the [standard-readme](https://github.com/RichardLitt/standard-readme) specification.

# :sunglasses: Contributors
<a href="https://github.com/Project-H3phaestus/ezcloud/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=Project-H3phaestus/ezcloud-archetype" />
</a>

# :blue_book: License
[MIT © CloudS3n](./LICENSE)
