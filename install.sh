#!/bin/bash
echo $1 # 第一个参数
#echo $2 # 第二个参数



appkey='2379E9161CBB94C8C5A4:pc8ZPWHinAJmtYjm4ohRnyT25c4nCYRPCsxCh2a2'
#images='"docker:hub.03in.com:5002/ranmufei/fitmgr-admin:latest"'
images='"docker:'$1'"'   # 第一个参数 docker镜像地址  sh ./install.sh "hub.03in.com:5002/ranmufei/fitmgr-eureka:v1"
appname='"crm"'	 # 第二个参数 应用名称
stackId='"1st65"'		 # 分组名称
serviceid='1s545'		 #	微服务服务ID
description='"crm"'



echo $images


# 安装新的app
install(){
	shell='{"name":'${appname}', "stackId":'${stackId}', "scale":0, "scalePolicy":"", "launchConfig":{"instanceTriggeredStop":"stop", "kind":"container", "networkMode":"managed", "privileged":false, "publishAllPorts":false, "readOnly":false, "runInit":false, "startOnCreate":true, "stdinOpen":true, "tty":true, "vcpu":1, "drainTimeoutMs":0, "type":"launchConfig", "labels":{"io.rancher.container.pull_image":"always"}, "restartPolicy":{"name":"always"}, "secrets":[], "dataVolumes":[], "dataVolumesFrom":[], "dns":[], "dnsSearch":[], "capAdd":[], "capDrop":[], "devices":[], "logConfig":{"driver":"", "config":{}}, "dataVolumesFromLaunchConfigs":[], "imageUuid":'${images}', "ports":[], "environment":{"werwer":"1111", "rerer":"22222"}, "blkioWeight":null, "cgroupParent":null, "count":null, "cpuCount":null, "cpuPercent":null, "cpuPeriod":null, "cpuQuota":null, "cpuRealtimePeriod":null, "cpuRealtimeRuntime":null, "cpuSet":null, "cpuSetMems":null, "cpuShares":null, "createIndex":null, "created":null, "deploymentUnitUuid":null, "description":null, "diskQuota":null, "domainName":null, "externalId":null, "firstRunning":null, "healthInterval":null, "healthRetries":null, "healthState":null, "healthTimeout":null, "hostname":null, "ioMaximumBandwidth":null, "ioMaximumIOps":null, "ip":null, "ip6":null, "ipcMode":null, "isolation":null, "kernelMemory":null, "memory":null, "memoryMb":null, "memoryReservation":null, "memorySwap":null, "memorySwappiness":null, "milliCpuReservation":null, "oomScoreAdj":null, "pidMode":null, "pidsLimit":null, "removed":null, "requestedIpAddress":null, "shmSize":null, "startCount":null, "stopSignal":null, "stopTimeout":null, "user":null, "userdata":null, "usernsMode":null, "uts":null, "uuid":null, "volumeDriver":null, "workingDir":null, "networkLaunchConfig":null,"requestedHostId":"1h1"}, "secondaryLaunchConfigs":[], "publicEndpoints":[], "assignServiceIpAddress":false, "lbConfig":""}' 
	curl -u ${appkey} \
	-X POST \
	-H 'Accept: application/json' \
	-H 'Content-Type: application/json' \
	-d "${shell}" \
	  'http://192.168.1.174:8080/v2-beta/projects/1a5/services'
}
# 安装新程序
#install

# 应用更新执行
upapp(){
curl -u ${appkey} \
-X POST \
-H 'Accept: application/json' \
-H 'Content-Type: application/json' \
'http://192.168.1.174:8080/v2-beta/projects/1a5/services/'${serviceid}'/?action=finishupgrade'

sleep 10

curl -u ${appkey} \
-X POST \
-H 'Accept: application/json' \
-H 'Content-Type: application/json' \
-d '{"inServiceStrategy":{"batchSize":1, "intervalMillis":2000, "startFirst":false, "type":"inServiceUpgradeStrategy", "launchConfig":{"instanceTriggeredStop":"stop", "kind":"container", "networkMode":"managed", "privileged":false, "publishAllPorts":false, "readOnly":false, "runInit":false, "startOnCreate":true, "stdinOpen":true, "tty":true, "vcpu":1, "drainTimeoutMs":0, "type":"launchConfig", "blkioWeight":null, "capAdd":[], "capDrop":[], "cgroupParent":null, "count":null, "cpuCount":null, "cpuPercent":null, "cpuPeriod":null, "cpuQuota":null, "cpuRealtimePeriod":null, "cpuRealtimeRuntime":null, "cpuSet":null, "cpuSetMems":null, "cpuShares":null, "dataVolumes":[], "dataVolumesFrom":[], "description":null, "devices":[], "diskQuota":null, "dns":[], "dnsSearch":[], "domainName":null, "environment":{"werwertttt":"111123333", "rererddd":"22222"}, "healthInterval":null, "healthRetries":null, "healthTimeout":null, "hostname":null, "imageUuid":'${images}', "ioMaximumBandwidth":null, "ioMaximumIOps":null, "ip":null, "ip6":null, "ipcMode":null, "isolation":null, "kernelMemory":null, "labels":{"io.rancher.container.pull_image":"always"}, "logConfig":{"type":"logConfig", "config":{}, "driver":null}, "memory":null, "memoryMb":null, "memoryReservation":null, "memorySwap":null, "memorySwappiness":null, "milliCpuReservation":null, "oomScoreAdj":null, "pidMode":null, "pidsLimit":null, "ports":[], "requestedIpAddress":null, "secrets":[], "shmSize":null, "stopSignal":null, "stopTimeout":null, "system":false, "user":null, "userdata":null, "usernsMode":null, "uts":null, "version":"52ec5be3-abcf-4305-b611-edced72ad092", "volumeDriver":null, "workingDir":null, "dataVolumesFromLaunchConfigs":[], "networkLaunchConfig":null, "createIndex":null, "created":null, "deploymentUnitUuid":null, "externalId":null, "firstRunning":null, "healthState":null, "removed":null, "startCount":null, "uuid":null}, "previousLaunchConfig":{"instanceTriggeredStop":"stop", "kind":"container", "networkMode":"managed", "privileged":false, "publishAllPorts":false, "readOnly":false, "runInit":false, "startOnCreate":true, "stdinOpen":true, "tty":true, "vcpu":1, "drainTimeoutMs":0, "type":"launchConfig", "blkioWeight":null, "capAdd":[], "capDrop":[], "cgroupParent":null, "count":null, "cpuCount":null, "cpuPercent":null, "cpuPeriod":null, "cpuQuota":null, "cpuRealtimePeriod":null, "cpuRealtimeRuntime":null, "cpuSet":null, "cpuSetMems":null, "cpuShares":null, "dataVolumes":[], "dataVolumesFrom":[], "description":null, "devices":[], "diskQuota":null, "dns":[], "dnsSearch":[], "domainName":null, "environment":{"werwer":"111123333", "rerer":"22222"}, "healthInterval":null, "healthRetries":null, "healthTimeout":null, "hostname":null, "imageUuid":'${images}', "ioMaximumBandwidth":null, "ioMaximumIOps":null, "ip":null, "ip6":null, "ipcMode":null, "isolation":null, "kernelMemory":null, "labels":{"io.rancher.container.pull_image":"always"}, "logConfig":{"type":"logConfig", "config":{}, "driver":null}, "memory":null, "memoryMb":null, "memoryReservation":null, "memorySwap":null, "memorySwappiness":null, "milliCpuReservation":null, "oomScoreAdj":null, "pidMode":null, "pidsLimit":null, "ports":[], "requestedIpAddress":null, "secrets":[], "shmSize":null, "stopSignal":null, "stopTimeout":null, "system":false, "user":null, "userdata":null, "usernsMode":null, "uts":null, "version":"f9c1f642-576e-4350-b29d-c6866909da57", "volumeDriver":null, "workingDir":null, "dataVolumesFromLaunchConfigs":[], "networkLaunchConfig":null, "createIndex":null, "created":null, "deploymentUnitUuid":null, "externalId":null, "firstRunning":null, "healthState":null, "removed":null, "startCount":null, "uuid":null}, "previousSecondaryLaunchConfigs":[], "secondaryLaunchConfigs":[]}, "toServiceStrategy":null}' \
'http://192.168.1.174:8080/v2-beta/projects/1a5/services/'${serviceid}'/?action=upgrade'

sleep 5

curl -u ${appkey} \
-X POST \
-H 'Accept: application/json' \
-H 'Content-Type: application/json' \
'http://192.168.1.174:8080/v2-beta/projects/1a5/services/'${serviceid}'/?action=finishupgrade'
}

#执行更新
upapp
