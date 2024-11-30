# plant_growth_environment_monitoring_system_using_arduino

## 项目名称

智能植物环境监测应用的实现

（选题来源：基于多种传感器的技术应用）

## 项目介绍

基于光敏电阻，土壤湿度传感器，温湿度传感器，报警器，舵机等元件，集合远程环境监测和提醒功能的应用。

### 硬件清单

<img src="README.assets/image-20221101112202158.png" alt="image-20221101112202158" style="zoom:50%;" />

5506光敏电阻

<img src="README.assets/image-20221101112222028.png" alt="image-20221101112222028" style="zoom:50%;" />

DHT11温湿度传感器模块

<img src="README.assets/image-20221101112338998.png" alt="image-20221101112338998" style="zoom:50%;" />

12095有源一体式蜂鸣器

<img src="README.assets/image-20221101112439037.png" alt="image-20221101112439037" style="zoom:50%;" />

电容式土壤湿度传感器

<img src="README.assets/SG90-D2.jpg" alt="img" style="zoom:50%;" />

9G舵机

![img](README.assets/forum.png)

arduino uno开发板

![img](README.assets/webp.webp)

esp8266 wifi模块

### 系统架构图

整个系统分为感知元件，通信元件，arduino，控制元件，上位机

```mermaid
graph TB
    subgraph 感知
        1[光敏电阻]
        2[土壤湿度传感器]
        3[DHT11温湿度传感器]
    end

    subgraph 通信
        4[esp12s-wifi模块]
    end

    subgraph 上位机PC
        7[spring boot后端]
        9[mysql数据库]
        7--数据-->9
        9--数据-->7
        10[vue+echarts前端]
        7--数据-->10
        10--请求-->7
        12[python脚本]
    end


    subgraph 光学信息模块
        11[光学摄像头]
    end

    1--数据-->8[arduino]
    2--数据-->8
    3--数据-->8
    8--数据-->4
    4--http请求-->7
    12 --控制--> 11
    11 --数据--> 7

```

## 技术路线

wifi技术，echarts组件数据可视化，vue+springboot web，sqlite数据库

## 开发计划

```mermaid
gantt
title 开发计划
dateFormat  YYYY-MM-DD
axisFormat  %m-%d
section 元件
    光敏,土壤湿度,DHT11温湿度: 2022-10-24,7d
    舵机,报警器: 7d
    usb光学摄像头: 14d
    wifi模块: 14d
section web
    数据库设计: 2022-11-13, 7d
    前端搭建: 14d
    数据可视化: 14d
    远程控制: 7d
    web框架: 2022-10-31, 42d 
```
