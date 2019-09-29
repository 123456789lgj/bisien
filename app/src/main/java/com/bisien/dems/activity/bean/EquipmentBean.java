package com.bisien.dems.activity.bean;

import java.util.List;

public class EquipmentBean {

    /**
     * code : 0
     * message :
     * data : [{"id":1002538209305730,"description":"","name":"警铃","houseId":8622681966107648,"monitorUnitId":8622682948046848,"samplerUnitId":1040536132465698,"parentId":null,"category":6,"state":2,"latitude":null,"longitude":null,"buyTime":"2018-08-31 16:21:20","expireTime":"2024-12-31 16:21:30","useTime":"2018-09-29 16:21:26","templateId":null,"imagePath":null,"vendor":"必思恩","assetNo":null,"price":null,"communicationState":0,"visible":null,"displayIndex":null,"alarmGrade":null,"param":"","equipmentSignals":[{"id":1312538288707898,"description":"","name":"警铃开关状态","equipmentId":1002538209305730,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":11,"unit":"","upperLimit":1,"lowerLimit":null,"currentValue":1,"showPrecision":null,"updateTime":"2019-07-20 16:40:28","expression":"","storedPeriod":null,"communicationState":1,"visible":true,"displayIndex":2,"code":"d_status","signalValueToStatus":[]},{"id":1317538288866367,"description":"","name":"警铃开关","equipmentId":1002538209305730,"parentId":null,"samplerUnitId":null,"channelType":1,"category":2,"channelNo":3,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":null,"showPrecision":null,"updateTime":null,"expression":"=[1002538209305730,1312538288707898]","storedPeriod":null,"communicationState":null,"visible":true,"displayIndex":2,"code":"c_switch","signalValueToStatus":[]}],"equipmentAlarms":[],"equipmentControlAlarms":[],"signalParams":[{"id":1324538289052857,"equipmentId":1002538209305730,"equipmentAdress":3,"signalId":null,"channalParam":null,"collectionIntervalTime":3,"updateTime":1563612194670,"channalNums":"11,","channalNum":null,"type":0,"channalType":1,"dataParsingParameters":":"},{"id":1035538291008088,"equipmentId":1002538209305730,"equipmentAdress":3,"signalId":null,"channalParam":null,"collectionIntervalTime":3,"updateTime":null,"channalNums":null,"channalNum":3,"type":0,"channalType":2,"dataParsingParameters":":"}],"observable":{}},{"id":1002551160707184,"description":"","name":"机柜","houseId":8622681966107648,"monitorUnitId":8622682948046848,"samplerUnitId":1060551075335355,"parentId":null,"category":8,"state":2,"latitude":null,"longitude":null,"buyTime":"2019-02-26 13:58:15","expireTime":"2021-02-26 13:58:17","useTime":"2019-02-26 13:58:07","templateId":null,"imagePath":null,"vendor":"必思恩","assetNo":null,"price":null,"communicationState":0,"visible":null,"displayIndex":null,"alarmGrade":null,"param":"","equipmentSignals":[{"id":1032551160816617,"description":"","name":"机柜前门状态","equipmentId":1002551160707184,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":1,"unit":"","upperLimit":1,"lowerLimit":null,"currentValue":0,"showPrecision":null,"updateTime":"2019-07-20 16:40:26","expression":"","storedPeriod":30,"communicationState":1,"visible":true,"displayIndex":1,"code":"d_door","signalValueToStatus":[{"id":1050551161003069,"value":0,"description":"关闭","signalId":1032551160816617},{"id":1049551161001272,"value":null,"description":"未知","signalId":1032551160816617},{"id":1051551161004603,"value":1,"description":"打开","signalId":1032551160816617}]},{"id":1035551160842774,"description":"","name":"机柜后门状态","equipmentId":1002551160707184,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":2,"unit":"","upperLimit":1,"lowerLimit":null,"currentValue":1,"showPrecision":null,"updateTime":"2019-07-20 16:40:26","expression":"","storedPeriod":30,"communicationState":1,"visible":true,"displayIndex":2,"code":"d_door","signalValueToStatus":[{"id":1053551161038413,"value":0,"description":"关闭","signalId":1035551160842774},{"id":1054551161040097,"value":1,"description":"开启","signalId":1035551160842774},{"id":1052551161036883,"value":null,"description":"未知","signalId":1035551160842774}]},{"id":1041551160885135,"description":"","name":"后门开关状态","equipmentId":1002551160707184,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":10,"unit":"","upperLimit":1,"lowerLimit":null,"currentValue":1,"showPrecision":null,"updateTime":"2019-07-20 16:40:26","expression":"","storedPeriod":30,"communicationState":1,"visible":true,"displayIndex":4,"code":null,"signalValueToStatus":[{"id":1058551161079965,"value":null,"description":"未知","signalId":1041551160885135},{"id":1060551161083231,"value":1,"description":"开启","signalId":1041551160885135},{"id":1059551161081366,"value":0,"description":"关闭","signalId":1041551160885135}]},{"id":1089551161245145,"description":"","name":"机柜后门开关","equipmentId":1002551160707184,"parentId":null,"samplerUnitId":null,"channelType":1,"category":2,"channelNo":2,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":null,"showPrecision":null,"updateTime":null,"expression":"=[1002551160707184,1035551160842774]","storedPeriod":null,"communicationState":null,"visible":true,"displayIndex":6,"code":"c_switch","signalValueToStatus":[{"id":1553563506769755,"value":0,"description":"开启","signalId":1089551161245145},{"id":1552563506768801,"value":1,"description":"关闭","signalId":1089551161245145},{"id":1551563506767889,"value":null,"description":"未知","signalId":1089551161245145}]},{"id":1038551160865839,"description":"","name":"前门开关状态","equipmentId":1002551160707184,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":9,"unit":"","upperLimit":1,"lowerLimit":null,"currentValue":0,"showPrecision":null,"updateTime":"2019-07-20 16:40:26","expression":"","storedPeriod":30,"communicationState":1,"visible":true,"displayIndex":3,"code":null,"signalValueToStatus":[{"id":1057551161062083,"value":1,"description":"开启","signalId":1038551160865839},{"id":1056551161060869,"value":0,"description":"关闭","signalId":1038551160865839},{"id":1055551161059362,"value":null,"description":"未知","signalId":1038551160865839}]},{"id":1044551160946096,"description":"","name":" 机柜前门开关","equipmentId":1002551160707184,"parentId":null,"samplerUnitId":null,"channelType":1,"category":2,"channelNo":1,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":null,"showPrecision":null,"updateTime":null,"expression":"=[1002551160707184,1032551160816617]","storedPeriod":null,"communicationState":null,"visible":true,"displayIndex":5,"code":"c_switch","signalValueToStatus":[{"id":1061551161099200,"value":null,"description":"未知","signalId":1044551160946096},{"id":1062551161101059,"value":0,"description":"关闭","signalId":1044551160946096},{"id":1063551161103019,"value":1,"description":"开启","signalId":1044551160946096}]}],"equipmentAlarms":[],"equipmentControlAlarms":[],"signalParams":[{"id":1067551161142119,"equipmentId":1002551160707184,"equipmentAdress":8,"signalId":null,"channalParam":null,"collectionIntervalTime":3,"updateTime":1563612194670,"channalNums":"1,2,9,10,","channalNum":null,"type":0,"channalType":1,"dataParsingParameters":"::::"}],"observable":{}},{"id":1009538038728648,"description":"","name":"温湿度采集器2#","houseId":8622681966107648,"monitorUnitId":8622682948046848,"samplerUnitId":8751346992232448,"parentId":null,"category":4,"state":2,"latitude":null,"longitude":null,"buyTime":"2018-08-31 16:58:18","expireTime":"2024-12-31 16:58:28","useTime":"2018-09-27 16:58:25","templateId":null,"imagePath":null,"vendor":"必思恩","assetNo":null,"price":null,"communicationState":1,"visible":null,"displayIndex":null,"alarmGrade":null,"param":"","equipmentSignals":[{"id":1015538038862138,"description":"","name":"温度","equipmentId":1009538038728648,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":2,"unit":"℃","upperLimit":40,"lowerLimit":0,"currentValue":26.8,"showPrecision":null,"updateTime":"2019-07-20 16:43:25","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":2,"code":"d_temp","signalValueToStatus":[]},{"id":1012538038778157,"description":"","name":"湿度","equipmentId":1009538038728648,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":1,"unit":"%HR","upperLimit":100,"lowerLimit":0,"currentValue":61.4,"showPrecision":null,"updateTime":"2019-07-20 16:43:25","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":1,"code":"d_humi","signalValueToStatus":[]}],"equipmentAlarms":[{"id":1066560585003367,"description":null,"name":"2#温度告警","equipmentId":1009538038728648,"grade":2,"category":null,"expression":"[1009538038728648,1015538038862138]=0","visible":true,"displayIndex":1,"delay":0,"status":false,"code":"d_temp","observable":null}],"equipmentControlAlarms":[],"signalParams":[{"id":1017538038948433,"equipmentId":1009538038728648,"equipmentAdress":34,"signalId":null,"channalParam":"3,0,4,2,1","collectionIntervalTime":3,"updateTime":1563612194670,"channalNums":"1,2,","channalNum":null,"type":0,"channalType":1,"dataParsingParameters":"0,2,1,10:2,2,1,10:"}],"observable":{}},{"id":1018538100592695,"description":"","name":"UPS","houseId":8622681966107648,"monitorUnitId":8622682948046848,"samplerUnitId":1046534929969343,"parentId":null,"category":7,"state":2,"latitude":null,"longitude":null,"buyTime":"2018-08-31 10:08:56","expireTime":"2024-12-31 10:09:31","useTime":"2018-09-28 10:09:26","templateId":null,"imagePath":null,"vendor":"必思恩","assetNo":null,"price":null,"communicationState":null,"visible":null,"displayIndex":null,"alarmGrade":null,"param":"","equipmentSignals":[{"id":1063538104682649,"description":"","name":"输出电压","equipmentId":1018538100592695,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":3,"unit":"V","upperLimit":1,"lowerLimit":null,"currentValue":220,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":3,"code":null,"signalValueToStatus":[]},{"id":1069538104767330,"description":"","name":"输入频率","equipmentId":1018538100592695,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":5,"unit":"Hz","upperLimit":1,"lowerLimit":null,"currentValue":49.9,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":5,"code":null,"signalValueToStatus":[]},{"id":1092538105212146,"description":"","name":"UPS待机","equipmentId":1018538100592695,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":12,"unit":"","upperLimit":null,"lowerLimit":null,"currentValue":0,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":12,"code":null,"signalValueToStatus":[{"id":1087538187683909,"value":1,"description":"开机","signalId":1092538105212146},{"id":1086538187682258,"value":0,"description":"关机","signalId":1092538105212146},{"id":1088538187685991,"value":2,"description":"待机","signalId":1092538105212146}]},{"id":1080538105002703,"description":"","name":"市电故障","equipmentId":1018538100592695,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":8,"unit":"","upperLimit":1,"lowerLimit":null,"currentValue":0,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":8,"code":null,"signalValueToStatus":[{"id":1077538187487011,"value":0,"description":"正常","signalId":1080538105002703},{"id":1078538187489335,"value":1,"description":"故障","signalId":1080538105002703}]},{"id":1086538105156100,"description":"","name":"旁路（升压，降压）激活","equipmentId":1018538100592695,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":10,"unit":"","upperLimit":1,"lowerLimit":null,"currentValue":0,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":10,"code":null,"signalValueToStatus":[{"id":1083538187614419,"value":1,"description":"已激活","signalId":1086538105156100},{"id":1082538187612510,"value":0,"description":"未激活","signalId":1086538105156100}]},{"id":1057538104598989,"description":"","name":"输入电压","equipmentId":1018538100592695,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":1,"unit":"V","upperLimit":1,"lowerLimit":null,"currentValue":227.7,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":1,"code":null,"signalValueToStatus":[]},{"id":1089538105184884,"description":"","name":"UPS故障","equipmentId":1018538100592695,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":11,"unit":"","upperLimit":null,"lowerLimit":null,"currentValue":0,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":11,"code":null,"signalValueToStatus":[{"id":1084538187643470,"value":0,"description":"正常","signalId":1089538105184884},{"id":1085538187645363,"value":1,"description":"故障","signalId":1089538105184884}]},{"id":1102538105408121,"description":"","name":"系统测试中","equipmentId":1018538100592695,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":13,"unit":"","upperLimit":null,"lowerLimit":null,"currentValue":0,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":13,"code":null,"signalValueToStatus":[{"id":1090538187769892,"value":0,"description":"运行正常","signalId":1102538105408121},{"id":1092538187772696,"value":2,"description":"测试中","signalId":1102538105408121},{"id":1091538187771422,"value":1,"description":"运行异常","signalId":1102538105408121}]},{"id":1074538104824981,"description":"","name":"电池电压","equipmentId":1018538100592695,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":6,"unit":"V","upperLimit":1,"lowerLimit":null,"currentValue":2.3,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":6,"code":null,"signalValueToStatus":[]},{"id":1077538104879025,"description":"","name":"温度","equipmentId":1018538100592695,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":7,"unit":"℃","upperLimit":1,"lowerLimit":null,"currentValue":41.8,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":7,"code":null,"signalValueToStatus":[]},{"id":1105538105435654,"description":"","name":"系统关机","equipmentId":1018538100592695,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":14,"unit":"","upperLimit":null,"lowerLimit":null,"currentValue":0,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":14,"code":null,"signalValueToStatus":[{"id":1093538187836890,"value":0,"description":"系统关机","signalId":1105538105435654},{"id":1094538187879374,"value":1,"description":"系统开机","signalId":1105538105435654}]},{"id":1060538104648023,"description":"","name":"输入异常电压","equipmentId":1018538100592695,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":2,"unit":"V","upperLimit":1,"lowerLimit":null,"currentValue":0,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":2,"code":null,"signalValueToStatus":[]},{"id":1066538104734369,"description":"","name":"输出电流百分比","equipmentId":1018538100592695,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":4,"unit":"%","upperLimit":1,"lowerLimit":null,"currentValue":5,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":4,"code":null,"signalValueToStatus":[]},{"id":1083538105099946,"description":"","name":"电池电压低","equipmentId":1018538100592695,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":9,"unit":"V","upperLimit":1,"lowerLimit":null,"currentValue":0,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":9,"code":null,"signalValueToStatus":[{"id":1080538187574124,"value":1,"description":"电池电压正常","signalId":1083538105099946},{"id":1079538187573222,"value":0,"description":"电池电压低","signalId":1083538105099946},{"id":1081538187576273,"value":2,"description":"电池电压高","signalId":1083538105099946}]}],"equipmentAlarms":[],"equipmentControlAlarms":[],"signalParams":[{"id":1620538124993937,"equipmentId":1018538100592695,"equipmentAdress":1,"signalId":null,"channalParam":"Q1","collectionIntervalTime":3,"updateTime":1563612194669,"channalNums":"1,2,3,4,5,6,7,8,9,10,11,12,13,14,","channalNum":null,"type":1,"channalType":1,"dataParsingParameters":"2,5,1:2,5,7:2,5,13:2,3,19:2,4,23:2,4,28:2,4,33:1,1,38:1,1,39:1,1,40:1,1,41:1,1,42:1,1,43:1,1,44:"}],"observable":null},{"id":1067560565723696,"description":"","name":"智能电表","houseId":8622681966107648,"monitorUnitId":8622682948046848,"samplerUnitId":1052560565627267,"parentId":null,"category":3,"state":2,"latitude":null,"longitude":null,"buyTime":"2019-06-15 10:28:19","expireTime":"2021-06-15 10:28:27","useTime":"2019-06-15 10:28:35","templateId":1009535002121840,"imagePath":null,"vendor":"********","assetNo":null,"price":null,"communicationState":1,"visible":null,"displayIndex":null,"alarmGrade":null,"param":"","equipmentSignals":[{"id":1071560565723719,"description":"","name":"有功电度总和","equipmentId":1067560565723696,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":1,"unit":"KWh","upperLimit":10,"lowerLimit":null,"currentValue":338.5,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":1,"code":null,"signalValueToStatus":[]},{"id":1072560565723720,"description":"","name":"电流","equipmentId":1067560565723696,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":3,"unit":"A","upperLimit":1000,"lowerLimit":null,"currentValue":0.965,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":3,"code":null,"signalValueToStatus":[]},{"id":1070560565723718,"description":"","name":"电压","equipmentId":1067560565723696,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":2,"unit":"V","upperLimit":100,"lowerLimit":null,"currentValue":226.69,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":2,"code":null,"signalValueToStatus":[]},{"id":1068560565723716,"description":"","name":"频率","equipmentId":1067560565723696,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":5,"unit":"Hz","upperLimit":100,"lowerLimit":null,"currentValue":49.96,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":5,"code":null,"signalValueToStatus":[]},{"id":1069560565723717,"description":"","name":"有功功率","equipmentId":1067560565723696,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":4,"unit":"W","upperLimit":10,"lowerLimit":null,"currentValue":164.4,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":4,"code":null,"signalValueToStatus":[]}],"equipmentAlarms":[],"equipmentControlAlarms":[],"signalParams":[{"id":1073560565723740,"equipmentId":1067560565723696,"equipmentAdress":48,"signalId":null,"channalParam":"3,0,24,2,1","collectionIntervalTime":3,"updateTime":1563612194670,"channalNums":"1,2,3,4,5","channalNum":null,"type":1,"channalType":1,"dataParsingParameters":"0,2,1,10:4,2,1,100:6,2,1,1000:10,2,1,10:22,2,1,100"}],"observable":{}},{"id":1068538191433335,"description":"","name":"烟温采集器","houseId":8622681966107648,"monitorUnitId":8622682948046848,"samplerUnitId":1015534730767812,"parentId":null,"category":2,"state":2,"latitude":null,"longitude":null,"buyTime":"2018-08-31 11:23:20","expireTime":"2024-12-31 11:23:29","useTime":"2018-09-29 11:23:26","templateId":null,"imagePath":null,"vendor":"必思恩","assetNo":null,"price":null,"communicationState":0,"visible":null,"displayIndex":null,"alarmGrade":null,"param":"","equipmentSignals":[{"id":1071538191487154,"description":"","name":"烟温告警信息","equipmentId":1068538191433335,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":3,"unit":"","upperLimit":1,"lowerLimit":null,"currentValue":1,"showPrecision":null,"updateTime":"2019-07-20 16:40:27","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":1,"code":"d_smoke","signalValueToStatus":[{"id":1088538191515569,"value":0,"description":"正常","signalId":1071538191487154},{"id":1104538191519459,"value":2,"description":"未知","signalId":1071538191487154},{"id":1094538191517450,"value":1,"description":"告警","signalId":1071538191487154}]}],"equipmentAlarms":[{"id":1066560585003365,"description":"","name":"烟感告警","equipmentId":1068538191433335,"grade":2,"category":null,"expression":"[1068538191433335,1071538191487154]=0","visible":true,"displayIndex":1,"delay":0,"status":false,"code":null,"observable":null}],"equipmentControlAlarms":[],"signalParams":[{"id":1127560578843517,"equipmentId":1068538191433335,"equipmentAdress":1,"signalId":null,"channalParam":null,"collectionIntervalTime":3,"updateTime":1563612194670,"channalNums":"3,","channalNum":null,"type":0,"channalType":1,"dataParsingParameters":":"}],"observable":{}},{"id":1082538121688038,"description":"","name":"温湿度采集器1#","houseId":8622681966107648,"monitorUnitId":8622682948046848,"samplerUnitId":1192536126557055,"parentId":null,"category":4,"state":2,"latitude":null,"longitude":null,"buyTime":"2018-08-31 16:00:58","expireTime":"2024-12-31 16:01:10","useTime":"2018-09-28 16:01:07","templateId":null,"imagePath":null,"vendor":"必思恩","assetNo":null,"price":null,"communicationState":1,"visible":null,"displayIndex":null,"alarmGrade":null,"param":"","equipmentSignals":[{"id":1085538121720708,"description":"","name":"湿度","equipmentId":1082538121688038,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":1,"unit":"%HR","upperLimit":100,"lowerLimit":0,"currentValue":62.5,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":1,"code":"d_humi","signalValueToStatus":[]},{"id":1088538121799109,"description":"","name":"温度","equipmentId":1082538121688038,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":2,"unit":"℃","upperLimit":40,"lowerLimit":0,"currentValue":26.7,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":2,"code":"d_temp","signalValueToStatus":[]}],"equipmentAlarms":[{"id":1066560585003366,"description":null,"name":"1#温度告警","equipmentId":1082538121688038,"grade":2,"category":null,"expression":"[1082538121688038,1088538121799109]=0","visible":true,"displayIndex":1,"delay":0,"status":false,"code":"d_temp","observable":null}],"equipmentControlAlarms":[],"signalParams":[{"id":1090538121876063,"equipmentId":1082538121688038,"equipmentAdress":37,"signalId":null,"channalParam":"3,0,4,2,1","collectionIntervalTime":3,"updateTime":1563612194670,"channalNums":"1,2,","channalNum":null,"type":0,"channalType":1,"dataParsingParameters":"0,2,1,10:2,2,1,10:"}],"observable":{}},{"id":1097538121956700,"description":"","name":"温湿度采集器4#","houseId":8622681966107648,"monitorUnitId":8622682948046848,"samplerUnitId":1195536126580197,"parentId":null,"category":4,"state":2,"latitude":null,"longitude":null,"buyTime":"2018-08-31 16:05:28","expireTime":"2024-12-31 16:05:37","useTime":"2018-09-28 16:05:36","templateId":null,"imagePath":null,"vendor":"必思恩","assetNo":null,"price":null,"communicationState":1,"visible":null,"displayIndex":null,"alarmGrade":null,"param":"","equipmentSignals":[{"id":1100538121986953,"description":"","name":"湿度","equipmentId":1097538121956700,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":1,"unit":"%HR","upperLimit":100,"lowerLimit":0,"currentValue":63,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":1,"code":"d_humi","signalValueToStatus":[]},{"id":1103538122013221,"description":"","name":"温度","equipmentId":1097538121956700,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":2,"unit":"℃","upperLimit":40,"lowerLimit":0,"currentValue":26.3,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":2,"code":"d_temp","signalValueToStatus":[]}],"equipmentAlarms":[],"equipmentControlAlarms":[],"signalParams":[{"id":1105538122064718,"equipmentId":1097538121956700,"equipmentAdress":38,"signalId":null,"channalParam":"3,0,4,2,1","collectionIntervalTime":3,"updateTime":1563612194670,"channalNums":"1,2,","channalNum":null,"type":0,"channalType":1,"dataParsingParameters":"0,2,1,10:2,2,1,10:"}],"observable":{}},{"id":1112538122135110,"description":"","name":"温湿度采集器5#","houseId":8622681966107648,"monitorUnitId":8622682948046848,"samplerUnitId":1008536305346876,"parentId":null,"category":4,"state":2,"latitude":null,"longitude":null,"buyTime":"2018-08-31 16:08:29","expireTime":"2024-12-31 16:08:39","useTime":"2018-09-28 16:08:38","templateId":null,"imagePath":null,"vendor":"必思恩","assetNo":null,"price":null,"communicationState":1,"visible":null,"displayIndex":null,"alarmGrade":null,"param":"","equipmentSignals":[{"id":1119538122203854,"description":"","name":"温度","equipmentId":1112538122135110,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":2,"unit":"℃","upperLimit":40,"lowerLimit":0,"currentValue":25.5,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":2,"code":"d_temp","signalValueToStatus":[]},{"id":1115538122175996,"description":"","name":"湿度","equipmentId":1112538122135110,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":1,"unit":"%HR","upperLimit":100,"lowerLimit":0,"currentValue":66.3,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":1,"code":"d_humi","signalValueToStatus":[]}],"equipmentAlarms":[],"equipmentControlAlarms":[],"signalParams":[{"id":1125538122270215,"equipmentId":1112538122135110,"equipmentAdress":40,"signalId":null,"channalParam":"3,0,4,2,1","collectionIntervalTime":3,"updateTime":1563612194670,"channalNums":"1,2,","channalNum":null,"type":0,"channalType":1,"dataParsingParameters":"0,2,1,10:2,2,1,10:"}],"observable":{}},{"id":1294538117199991,"description":"","name":"温湿度采集器6#","houseId":8622681966107648,"monitorUnitId":8622682948046848,"samplerUnitId":1184536126477494,"parentId":null,"category":4,"state":2,"latitude":null,"longitude":null,"buyTime":"2018-08-31 14:46:11","expireTime":"2024-12-31 14:46:21","useTime":"2018-09-28 14:46:17","templateId":null,"imagePath":null,"vendor":"必思恩","assetNo":null,"price":null,"communicationState":1,"visible":null,"displayIndex":null,"alarmGrade":null,"param":"","equipmentSignals":[{"id":1300538117302251,"description":"","name":"温度","equipmentId":1294538117199991,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":2,"unit":"℃","upperLimit":40,"lowerLimit":0,"currentValue":25.2,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":2,"code":"d_temp","signalValueToStatus":[]},{"id":1297538117244210,"description":"","name":"湿度","equipmentId":1294538117199991,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":1,"unit":"%HR","upperLimit":100,"lowerLimit":0,"currentValue":66.4,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":1,"code":"d_humi","signalValueToStatus":[]}],"equipmentAlarms":[],"equipmentControlAlarms":[],"signalParams":[{"id":1302538117387130,"equipmentId":1294538117199991,"equipmentAdress":35,"signalId":null,"channalParam":"3,0,4,2,1","collectionIntervalTime":3,"updateTime":1563612194670,"channalNums":"1,2,","channalNum":null,"type":0,"channalType":1,"dataParsingParameters":"0,2,1,10:2,2,1,10:"}],"observable":{}},{"id":1312538117606604,"description":"","name":"温湿度采集器3#","houseId":8622681966107648,"monitorUnitId":8622682948046848,"samplerUnitId":1189536126538859,"parentId":null,"category":4,"state":2,"latitude":null,"longitude":null,"buyTime":"2018-08-31 14:53:00","expireTime":"2024-12-31 14:53:09","useTime":"2018-09-28 14:53:06","templateId":null,"imagePath":null,"vendor":"必思恩","assetNo":null,"price":null,"communicationState":1,"visible":null,"displayIndex":null,"alarmGrade":null,"param":"","equipmentSignals":[{"id":1315538117647676,"description":"","name":"湿度","equipmentId":1312538117606604,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":1,"unit":"%HR","upperLimit":100,"lowerLimit":0,"currentValue":63.1,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":1,"code":"d_humi","signalValueToStatus":[]},{"id":1318538117676086,"description":"","name":"温度","equipmentId":1312538117606604,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":2,"unit":"℃","upperLimit":40,"lowerLimit":0,"currentValue":26.3,"showPrecision":null,"updateTime":"2019-07-20 16:43:24","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":2,"code":"d_temp","signalValueToStatus":[]}],"equipmentAlarms":[],"equipmentControlAlarms":[],"signalParams":[{"id":1340538117903884,"equipmentId":1312538117606604,"equipmentAdress":36,"signalId":null,"channalParam":"3,0,4,2,1","collectionIntervalTime":3,"updateTime":1563612194670,"channalNums":"1,2,","channalNum":null,"type":0,"channalType":1,"dataParsingParameters":"0,2,1,10:2,2,1,10:"}],"observable":{}}]
     */

    private String code;
    private String message;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1002538209305730
         * description :
         * name : 警铃
         * houseId : 8622681966107648
         * monitorUnitId : 8622682948046848
         * samplerUnitId : 1040536132465698
         * parentId : null
         * category : 6
         * state : 2
         * latitude : null
         * longitude : null
         * buyTime : 2018-08-31 16:21:20
         * expireTime : 2024-12-31 16:21:30
         * useTime : 2018-09-29 16:21:26
         * templateId : null
         * imagePath : null
         * vendor : 必思恩
         * assetNo : null
         * price : null
         * communicationState : 0
         * visible : null
         * displayIndex : null
         * alarmGrade : null
         * param :
         * equipmentSignals : [{"id":1312538288707898,"description":"","name":"警铃开关状态","equipmentId":1002538209305730,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":11,"unit":"","upperLimit":1,"lowerLimit":null,"currentValue":1,"showPrecision":null,"updateTime":"2019-07-20 16:40:28","expression":"","storedPeriod":null,"communicationState":1,"visible":true,"displayIndex":2,"code":"d_status","signalValueToStatus":[]},{"id":1317538288866367,"description":"","name":"警铃开关","equipmentId":1002538209305730,"parentId":null,"samplerUnitId":null,"channelType":1,"category":2,"channelNo":3,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":null,"showPrecision":null,"updateTime":null,"expression":"=[1002538209305730,1312538288707898]","storedPeriod":null,"communicationState":null,"visible":true,"displayIndex":2,"code":"c_switch","signalValueToStatus":[]}]
         * equipmentAlarms : []
         * equipmentControlAlarms : []
         * signalParams : [{"id":1324538289052857,"equipmentId":1002538209305730,"equipmentAdress":3,"signalId":null,"channalParam":null,"collectionIntervalTime":3,"updateTime":1563612194670,"channalNums":"11,","channalNum":null,"type":0,"channalType":1,"dataParsingParameters":":"},{"id":1035538291008088,"equipmentId":1002538209305730,"equipmentAdress":3,"signalId":null,"channalParam":null,"collectionIntervalTime":3,"updateTime":null,"channalNums":null,"channalNum":3,"type":0,"channalType":2,"dataParsingParameters":":"}]
         * observable : {}
         */

        private long id;
        private String description;
        private String name;
        private long houseId;
        private long monitorUnitId;
        private long samplerUnitId;
        private Object parentId;
        private int category;
        private int state;
        private Object latitude;
        private Object longitude;
        private String buyTime;
        private String expireTime;
        private String useTime;
        private Object templateId;
        private Object imagePath;
        private String vendor;
        private Object assetNo;
        private Object price;
        private int communicationState;
        private Object visible;
        private Object displayIndex;
        private Object alarmGrade;
        private String param;
        private ObservableBean observable;
        private List<EquipmentSignalsBean> equipmentSignals;
        private List<?> equipmentAlarms;
        private List<?> equipmentControlAlarms;
        private List<SignalParamsBean> signalParams;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getHouseId() {
            return houseId;
        }

        public void setHouseId(long houseId) {
            this.houseId = houseId;
        }

        public long getMonitorUnitId() {
            return monitorUnitId;
        }

        public void setMonitorUnitId(long monitorUnitId) {
            this.monitorUnitId = monitorUnitId;
        }

        public long getSamplerUnitId() {
            return samplerUnitId;
        }

        public void setSamplerUnitId(long samplerUnitId) {
            this.samplerUnitId = samplerUnitId;
        }

        public Object getParentId() {
            return parentId;
        }

        public void setParentId(Object parentId) {
            this.parentId = parentId;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
            this.longitude = longitude;
        }

        public String getBuyTime() {
            return buyTime;
        }

        public void setBuyTime(String buyTime) {
            this.buyTime = buyTime;
        }

        public String getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(String expireTime) {
            this.expireTime = expireTime;
        }

        public String getUseTime() {
            return useTime;
        }

        public void setUseTime(String useTime) {
            this.useTime = useTime;
        }

        public Object getTemplateId() {
            return templateId;
        }

        public void setTemplateId(Object templateId) {
            this.templateId = templateId;
        }

        public Object getImagePath() {
            return imagePath;
        }

        public void setImagePath(Object imagePath) {
            this.imagePath = imagePath;
        }

        public String getVendor() {
            return vendor;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

        public Object getAssetNo() {
            return assetNo;
        }

        public void setAssetNo(Object assetNo) {
            this.assetNo = assetNo;
        }

        public Object getPrice() {
            return price;
        }

        public void setPrice(Object price) {
            this.price = price;
        }

        public int getCommunicationState() {
            return communicationState;
        }

        public void setCommunicationState(int communicationState) {
            this.communicationState = communicationState;
        }

        public Object getVisible() {
            return visible;
        }

        public void setVisible(Object visible) {
            this.visible = visible;
        }

        public Object getDisplayIndex() {
            return displayIndex;
        }

        public void setDisplayIndex(Object displayIndex) {
            this.displayIndex = displayIndex;
        }

        public Object getAlarmGrade() {
            return alarmGrade;
        }

        public void setAlarmGrade(Object alarmGrade) {
            this.alarmGrade = alarmGrade;
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public ObservableBean getObservable() {
            return observable;
        }

        public void setObservable(ObservableBean observable) {
            this.observable = observable;
        }

        public List<EquipmentSignalsBean> getEquipmentSignals() {
            return equipmentSignals;
        }

        public void setEquipmentSignals(List<EquipmentSignalsBean> equipmentSignals) {
            this.equipmentSignals = equipmentSignals;
        }

        public List<?> getEquipmentAlarms() {
            return equipmentAlarms;
        }

        public void setEquipmentAlarms(List<?> equipmentAlarms) {
            this.equipmentAlarms = equipmentAlarms;
        }

        public List<?> getEquipmentControlAlarms() {
            return equipmentControlAlarms;
        }

        public void setEquipmentControlAlarms(List<?> equipmentControlAlarms) {
            this.equipmentControlAlarms = equipmentControlAlarms;
        }

        public List<SignalParamsBean> getSignalParams() {
            return signalParams;
        }

        public void setSignalParams(List<SignalParamsBean> signalParams) {
            this.signalParams = signalParams;
        }

        public static class ObservableBean {
        }

        public static class EquipmentSignalsBean {
            /**
             * id : 1312538288707898
             * description :
             * name : 警铃开关状态
             * equipmentId : 1002538209305730
             * parentId : null
             * samplerUnitId : null
             * channelType : 1
             * category : 1
             * channelNo : 11
             * unit :
             * upperLimit : 1.0
             * lowerLimit : null
             * currentValue : 1.0
             * showPrecision : null
             * updateTime : 2019-07-20 16:40:28
             * expression :
             * storedPeriod : null
             * communicationState : 1
             * visible : true
             * displayIndex : 2
             * code : d_status
             * signalValueToStatus : []
             */

            private long id;
            private String description;
            private String name;
            private long equipmentId;
            private Object parentId;
            private Object samplerUnitId;
            private int channelType;
            private int category;
            private int channelNo;
            private String unit;
            private double upperLimit;
            private Object lowerLimit;
            private double currentValue;
            private Object showPrecision;
            private String updateTime;
            private String expression;
            private Object storedPeriod;
            private int communicationState;
            private boolean visible;
            private int displayIndex;
            private String code;
            private List<?> signalValueToStatus;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public long getEquipmentId() {
                return equipmentId;
            }

            public void setEquipmentId(long equipmentId) {
                this.equipmentId = equipmentId;
            }

            public Object getParentId() {
                return parentId;
            }

            public void setParentId(Object parentId) {
                this.parentId = parentId;
            }

            public Object getSamplerUnitId() {
                return samplerUnitId;
            }

            public void setSamplerUnitId(Object samplerUnitId) {
                this.samplerUnitId = samplerUnitId;
            }

            public int getChannelType() {
                return channelType;
            }

            public void setChannelType(int channelType) {
                this.channelType = channelType;
            }

            public int getCategory() {
                return category;
            }

            public void setCategory(int category) {
                this.category = category;
            }

            public int getChannelNo() {
                return channelNo;
            }

            public void setChannelNo(int channelNo) {
                this.channelNo = channelNo;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public double getUpperLimit() {
                return upperLimit;
            }

            public void setUpperLimit(double upperLimit) {
                this.upperLimit = upperLimit;
            }

            public Object getLowerLimit() {
                return lowerLimit;
            }

            public void setLowerLimit(Object lowerLimit) {
                this.lowerLimit = lowerLimit;
            }

            public double getCurrentValue() {
                return currentValue;
            }

            public void setCurrentValue(double currentValue) {
                this.currentValue = currentValue;
            }

            public Object getShowPrecision() {
                return showPrecision;
            }

            public void setShowPrecision(Object showPrecision) {
                this.showPrecision = showPrecision;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getExpression() {
                return expression;
            }

            public void setExpression(String expression) {
                this.expression = expression;
            }

            public Object getStoredPeriod() {
                return storedPeriod;
            }

            public void setStoredPeriod(Object storedPeriod) {
                this.storedPeriod = storedPeriod;
            }

            public int getCommunicationState() {
                return communicationState;
            }

            public void setCommunicationState(int communicationState) {
                this.communicationState = communicationState;
            }

            public boolean isVisible() {
                return visible;
            }

            public void setVisible(boolean visible) {
                this.visible = visible;
            }

            public int getDisplayIndex() {
                return displayIndex;
            }

            public void setDisplayIndex(int displayIndex) {
                this.displayIndex = displayIndex;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public List<?> getSignalValueToStatus() {
                return signalValueToStatus;
            }

            public void setSignalValueToStatus(List<?> signalValueToStatus) {
                this.signalValueToStatus = signalValueToStatus;
            }
        }

        public static class SignalParamsBean {
            /**
             * id : 1324538289052857
             * equipmentId : 1002538209305730
             * equipmentAdress : 3
             * signalId : null
             * channalParam : null
             * collectionIntervalTime : 3
             * updateTime : 1563612194670
             * channalNums : 11,
             * channalNum : null
             * type : 0
             * channalType : 1
             * dataParsingParameters : :
             */

            private long id;
            private long equipmentId;
            private int equipmentAdress;
            private Object signalId;
            private Object channalParam;
            private int collectionIntervalTime;
            private long updateTime;
            private String channalNums;
            private Object channalNum;
            private int type;
            private int channalType;
            private String dataParsingParameters;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public long getEquipmentId() {
                return equipmentId;
            }

            public void setEquipmentId(long equipmentId) {
                this.equipmentId = equipmentId;
            }

            public int getEquipmentAdress() {
                return equipmentAdress;
            }

            public void setEquipmentAdress(int equipmentAdress) {
                this.equipmentAdress = equipmentAdress;
            }

            public Object getSignalId() {
                return signalId;
            }

            public void setSignalId(Object signalId) {
                this.signalId = signalId;
            }

            public Object getChannalParam() {
                return channalParam;
            }

            public void setChannalParam(Object channalParam) {
                this.channalParam = channalParam;
            }

            public int getCollectionIntervalTime() {
                return collectionIntervalTime;
            }

            public void setCollectionIntervalTime(int collectionIntervalTime) {
                this.collectionIntervalTime = collectionIntervalTime;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public String getChannalNums() {
                return channalNums;
            }

            public void setChannalNums(String channalNums) {
                this.channalNums = channalNums;
            }

            public Object getChannalNum() {
                return channalNum;
            }

            public void setChannalNum(Object channalNum) {
                this.channalNum = channalNum;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getChannalType() {
                return channalType;
            }

            public void setChannalType(int channalType) {
                this.channalType = channalType;
            }

            public String getDataParsingParameters() {
                return dataParsingParameters;
            }

            public void setDataParsingParameters(String dataParsingParameters) {
                this.dataParsingParameters = dataParsingParameters;
            }
        }
    }
}
