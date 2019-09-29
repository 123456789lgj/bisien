package com.bisien.dems.activity.bean;

import java.util.List;

public class CondiditioningBean {

    /**
     * code : 0
     * message :
     * data : [{"id":1022553740121758,"description":"","name":"交流欠压","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":34,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":34,"code":null,"signalValueToStatus":[]},{"id":1022553740121757,"description":"","name":"交流过压","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":33,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":33,"code":null,"signalValueToStatus":[]},{"id":1022553740121746,"description":"","name":"内风机故障","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":22,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":22,"code":null,"signalValueToStatus":[]},{"id":1022553740121725,"description":"","name":"内风机状态","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":1,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":1,"showPrecision":null,"updateTime":"2019-03-30 09:03:27","expression":"","storedPeriod":null,"communicationState":1,"visible":true,"displayIndex":1,"code":"d_fan","signalValueToStatus":[]},{"id":1022553740121797,"description":"","name":"内风机转速","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":73,"unit":"%","upperLimit":null,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":73,"code":null,"signalValueToStatus":[]},{"id":1022553740121782,"description":"","name":"内风机运行时间","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":58,"unit":"小时","upperLimit":null,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":"2019-03-29 13:12:26","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":58,"code":null,"signalValueToStatus":[]},{"id":1022553740121798,"description":"","name":"冷冻水1阀开度","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":74,"unit":"%","upperLimit":null,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":74,"code":null,"signalValueToStatus":[]},{"id":1022553740121796,"description":"","name":"制冷方式","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":72,"unit":null,"upperLimit":null,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":"2019-03-29 13:12:26","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":72,"code":null,"signalValueToStatus":[]},{"id":1022553740121735,"description":"","name":"制冷状态","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":11,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":true,"displayIndex":11,"code":"d_cold","signalValueToStatus":[]},{"id":1022553740121792,"description":"","name":"制冷范围","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":68,"unit":"℃","upperLimit":null,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":"2019-03-29 13:12:26","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":68,"code":null,"signalValueToStatus":[]},{"id":1022553740121791,"description":"","name":"制冷设点","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":67,"unit":"℃","upperLimit":null,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":"2019-03-29 13:12:26","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":67,"code":null,"signalValueToStatus":[]},{"id":1022553740121727,"description":"","name":"加湿器","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":3,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":true,"displayIndex":3,"code":null,"signalValueToStatus":[]},{"id":1022553740121750,"description":"","name":"加湿器电源故障","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":26,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":26,"code":null,"signalValueToStatus":[]},{"id":1022553740121785,"description":"","name":"加湿器运行时间","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":61,"unit":"小时","upperLimit":null,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":"2019-03-29 13:12:26","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":61,"code":null,"signalValueToStatus":[]},{"id":1022553740121737,"description":"","name":"加湿状态","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":13,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":true,"displayIndex":13,"code":"d_dehumi","signalValueToStatus":[]},{"id":1022553740121764,"description":"","name":"加湿过电流","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":40,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":40,"code":null,"signalValueToStatus":[]},{"id":1022553740121763,"description":"","name":"加湿高水位","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":39,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":39,"code":null,"signalValueToStatus":[]},{"id":1022553740121751,"description":"","name":"加热器故障","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":27,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":27,"code":null,"signalValueToStatus":[]},{"id":1022553740121736,"description":"","name":"加热状态","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":12,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":true,"displayIndex":12,"code":"d_humi","signalValueToStatus":[]},{"id":1022553740121743,"description":"","name":"回风低温报警","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":19,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":19,"code":null,"signalValueToStatus":[]},{"id":1022553740121788,"description":"","name":"回风低温报警值","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":64,"unit":"℃","upperLimit":null,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":"2019-03-29 13:12:26","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":64,"code":null,"signalValueToStatus":[]},{"id":1022553740121745,"description":"","name":"回风低湿报警","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":21,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":21,"code":null,"signalValueToStatus":[]},{"id":1022553740121790,"description":"","name":"回风低湿报警值","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":66,"unit":"%rH","upperLimit":null,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":"2019-03-29 13:12:26","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":66,"code":null,"signalValueToStatus":[]},{"id":1022553740121752,"description":"","name":"回风温度传感器","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":28,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":28,"code":null,"signalValueToStatus":[]},{"id":1022553740121780,"description":"","name":"回风温度校正值","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":56,"unit":"℃","upperLimit":null,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":"2019-03-29 13:12:26","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":56,"code":null,"signalValueToStatus":[]},{"id":1022553740121776,"description":"","name":"回风温度测量值","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":52,"unit":"℃","upperLimit":null,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":"2019-03-29 13:12:26","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":52,"code":null,"signalValueToStatus":[]},{"id":1022553740121753,"description":"","name":"回风湿度传感器","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":29,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":29,"code":null,"signalValueToStatus":[]},{"id":1022553740121781,"description":"","name":"回风湿度校正值","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":57,"unit":"%rH","upperLimit":null,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":"2019-03-29 13:12:26","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":57,"code":null,"signalValueToStatus":[]},{"id":1022553740121777,"description":"","name":"回风湿度测量值","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":53,"unit":"%rH","upperLimit":null,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":"2019-03-29 13:12:26","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":53,"code":null,"signalValueToStatus":[]},{"id":1022553740121742,"description":"","name":"回风高温报警","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":18,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":18,"code":null,"signalValueToStatus":[]},{"id":1022553740121787,"description":"","name":"回风高温报警值","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":63,"unit":"℃","upperLimit":null,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":"2019-03-29 13:12:26","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":63,"code":null,"signalValueToStatus":[]},{"id":1022553740121744,"description":"","name":"回风高湿报警","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":20,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":20,"code":null,"signalValueToStatus":[]},{"id":1022553740121789,"description":"","name":"回风高湿报警值","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":65,"unit":"%rH","upperLimit":null,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":"2019-03-29 13:12:26","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":65,"code":null,"signalValueToStatus":[]},{"id":1022553740121747,"description":"","name":"地板溢水","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":23,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":23,"code":null,"signalValueToStatus":[]},{"id":1022553740121733,"description":"","name":"强制制冷","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":9,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":true,"displayIndex":9,"code":null,"signalValueToStatus":[]},{"id":1022553740121734,"description":"","name":"手动控制","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":10,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-29 12:44:40","expression":"","storedPeriod":null,"communicationState":1,"visible":true,"displayIndex":10,"code":null,"signalValueToStatus":[]},{"id":1022553740121732,"description":"","name":"报警输出","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":8,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":true,"displayIndex":8,"code":null,"signalValueToStatus":[]},{"id":1022553740121730,"description":"","name":"排水","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":6,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":true,"displayIndex":6,"code":null,"signalValueToStatus":[]},{"id":1022553740121748,"description":"","name":"气流丢失","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":24,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":24,"code":null,"signalValueToStatus":[]},{"id":1022553740121767,"description":"","name":"水温失效","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":43,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":43,"code":null,"signalValueToStatus":[]},{"id":1022553740121783,"description":"","name":"水阀运行时间","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":59,"unit":"小时","upperLimit":null,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":"2019-03-29 13:12:26","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":59,"code":null,"signalValueToStatus":[]},{"id":1022553740121729,"description":"","name":"注水","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":5,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":true,"displayIndex":5,"code":null,"signalValueToStatus":[]},{"id":1022553740121794,"description":"","name":"湿度范围","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":70,"unit":"%rH","upperLimit":null,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":"2019-03-29 13:12:26","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":70,"code":null,"signalValueToStatus":[]},{"id":1022553740121793,"description":"","name":"湿度设点","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":69,"unit":"%rH","upperLimit":null,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":"2019-03-29 13:12:26","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":69,"code":null,"signalValueToStatus":[]},{"id":1022553740121749,"description":"","name":"滤网故障","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":25,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":25,"code":null,"signalValueToStatus":[]},{"id":1022553740121754,"description":"","name":"烟感","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":30,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":30,"code":null,"signalValueToStatus":[]},{"id":1022553740121726,"description":"","name":"电加热1","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":2,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":true,"displayIndex":2,"code":null,"signalValueToStatus":[]},{"id":1022553740121784,"description":"","name":"电加热1运行时间","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":60,"unit":"小时","upperLimit":null,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":"2019-03-29 13:12:26","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":60,"code":null,"signalValueToStatus":[]},{"id":1022553740121731,"description":"","name":"电加热2","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":7,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":true,"displayIndex":7,"code":null,"signalValueToStatus":[]},{"id":1022553740121760,"description":"","name":"电源丢失报警","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":36,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":36,"code":null,"signalValueToStatus":[]},{"id":1022553740121786,"description":"","name":"电网频率","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":62,"unit":"Hz","upperLimit":null,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":"2019-03-29 13:12:26","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":62,"code":null,"signalValueToStatus":[]},{"id":1022553740121739,"description":"","name":"监控关机","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":15,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":true,"displayIndex":15,"code":null,"signalValueToStatus":[]},{"id":1022553740121756,"description":"","name":"相序错误","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":32,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":32,"code":null,"signalValueToStatus":[]},{"id":1022553740121761,"description":"","name":"组网失败报警","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":37,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":37,"code":null,"signalValueToStatus":[]},{"id":1022553740121755,"description":"","name":"缺相报警","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":31,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":31,"code":null,"signalValueToStatus":[]},{"id":1022553740121759,"description":"","name":"远程关机报警","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":35,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":35,"code":null,"signalValueToStatus":[]},{"id":1022553740121766,"description":"","name":"送风低温","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":42,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":42,"code":null,"signalValueToStatus":[]},{"id":1022553740121741,"description":"","name":"送风温度故障","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":17,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-29 15:00:42","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":17,"code":null,"signalValueToStatus":[]},{"id":1022553740121795,"description":"","name":"送风温度校准值","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":71,"unit":"℃","upperLimit":null,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":"2019-03-29 13:12:26","expression":"","storedPeriod":1800,"communicationState":1,"visible":true,"displayIndex":71,"code":null,"signalValueToStatus":[]},{"id":1022553740121778,"description":"","name":"送风温度测量值","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":54,"unit":"℃","upperLimit":null,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":"2019-03-29 13:12:26","expression":"","storedPeriod":1800,"communicationState":1,"visible":false,"displayIndex":54,"code":null,"signalValueToStatus":[]},{"id":1022553740121762,"description":"","name":"送风湿度传感器","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":38,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":38,"code":null,"signalValueToStatus":[]},{"id":1022553740121779,"description":"","name":"送风湿度测量值","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":55,"unit":"%rH","upperLimit":null,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":"2019-03-29 13:12:26","expression":"","storedPeriod":1800,"communicationState":1,"visible":false,"displayIndex":55,"code":null,"signalValueToStatus":[]},{"id":1022553740121765,"description":"","name":"送风高温","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":41,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":41,"code":null,"signalValueToStatus":[]},{"id":1022553740121738,"description":"","name":"除湿状态","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":14,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":true,"displayIndex":14,"code":null,"signalValueToStatus":[]},{"id":1022553740121728,"description":"","name":"除湿阀","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":4,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":true,"displayIndex":4,"code":null,"signalValueToStatus":[]},{"id":1022553740121740,"description":"","name":"频偏报警","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":16,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":16,"code":null,"signalValueToStatus":[]},{"id":1022553740121768,"description":"","name":"风压失效","equipmentId":1019553738923988,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":44,"unit":"","upperLimit":1,"lowerLimit":0,"currentValue":0,"showPrecision":null,"updateTime":"2019-03-28 23:38:11","expression":"","storedPeriod":null,"communicationState":1,"visible":false,"displayIndex":44,"code":null,"signalValueToStatus":[]}]
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
         * id : 1022553740121758
         * description :
         * name : 交流欠压
         * equipmentId : 1019553738923988
         * parentId : null
         * samplerUnitId : null
         * channelType : 1
         * category : 1
         * channelNo : 34
         * unit :
         * upperLimit : 1.0
         * lowerLimit : 0.0
         * currentValue : 0.0
         * showPrecision : null
         * updateTime : 2019-03-28 23:38:11
         * expression :
         * storedPeriod : null
         * communicationState : 1
         * visible : false
         * displayIndex : 34
         * code : null
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
        private double lowerLimit;
        private double currentValue;
        private Object showPrecision;
        private String updateTime;
        private String expression;
        private Object storedPeriod;
        private int communicationState;
        private boolean visible;
        private int displayIndex;
        private Object code;
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

        public double getLowerLimit() {
            return lowerLimit;
        }

        public void setLowerLimit(double lowerLimit) {
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

        public Object getCode() {
            return code;
        }

        public void setCode(Object code) {
            this.code = code;
        }

        public List<?> getSignalValueToStatus() {
            return signalValueToStatus;
        }

        public void setSignalValueToStatus(List<?> signalValueToStatus) {
            this.signalValueToStatus = signalValueToStatus;
        }
    }
}
