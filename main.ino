/**
 * @file main.ino
 * @author boer (1210169842@qq.com)
 * @brief 传感器主文件
 * @version 0.1
 * @date 2022-12-08
 *
 *
 * 接口规约：
 * |名称|接口|备注|
 * |:--:|:--:|:--:|
 * |SoftwareSerial|2,3|esp8266|
 * |DHT11 data|4|温湿度传感器|
 * |trig|12|超声波测距|
 * |echo|11|超声波测距|
 * |ledr|7|彩光指示灯红色|
 * |ledg|8|彩光指示灯绿色|
 * |ledb|9|彩光指示灯蓝色|
 * |soil|A0|土壤湿度，模拟型号输出|
 * |light|A1|光敏，模拟型号输出|
 * |inteval|A2|采集频率，电位器|
 * @copyright Copyright (c) 2022
 *
 */
String comdata = "";  // 字符串缓冲区

#include <SoftwareSerial.h>

// RX: 2, TX: 3 2为软串口接收引脚，3为软串口发送引脚
// SoftwareSerial(uint8_t receivePin, uint8_t transmitPin, bool inverse_logic =
// false);
SoftwareSerial Serial_esp(2, 3);



/**dht11温湿度传感器*/
#include "DHT.h"

#define DHTPIN 4
#define DHTTYPE DHT11

DHT dht(DHTPIN, DHTTYPE);

/**超声波测距*/
int trig = 12;
int echo = 11;

/**土壤*/
int soil = A0;

/**光敏*/
int light = A1;

/**电位器*/
int potentiometer = A2;

/**彩光指示灯*/
int ledr = 7;
int ledg = 8;
int ledb = 9;


/**
 * @brief 软串口执行
 * @param const char *str AT指令的字符串例如 "AT"
 */
inline void exe(const char* c_str, int exe_time = 4000) {
  Serial_esp.print(c_str);
  delay(exe_time);
  if (Serial_esp.available()) {
    Serial.println("{------");
    while (Serial_esp.available()) {
      //      comdata += char(Serial_esp.read());
      Serial.print(char(Serial_esp.read()));
      delay(2);
    }
    comdata = "";
    Serial.println("\n------}\n");
  } else {
    Serial.println("\n{no data}\n");
    comdata = "";
  }
}

inline void preExe() {
  exe("+++", 10000);  // 保证退出到AT指令模式,需要间隔更久再进行下一条AT指令
  exe("AT+CWMODE_DEF=3\r\n");
  exe("AT+CWJAP_DEF=\"WIFI_NAME\",\"WIFI_PASSWORD\"\r\n", 10000);
  exe("AT+CIPSTART=\"TCP\",\"DOMAIN_NAME\",PORT\r\n");
  exe("AT+CIPMODE=1\r\n", 6000);  // 开启透传模式
  exe("AT+CIPSEND\r\n");          // 开始输入传输数据
}


void setup() {
  pinMode(trig, OUTPUT);
  pinMode(echo, INPUT);
  pinMode(ledr, OUTPUT);
  pinMode(ledg, OUTPUT);
  pinMode(ledb, OUTPUT);

  Serial.begin(9600);      // 串口波特率设置为9600
  Serial_esp.begin(9600);  //< 波特率设置为9600, 是 arduino 的软串口
  delay(1000);             //< 启动时间延迟1秒
  Serial.println("DHT11");
  dht.begin();
  delay(1000);  //< 启动时间延迟1秒
}

void loop() {
  // 读取数据阶段，彩灯变为红色
  colorRGB(200, 0, 0);
  delay(1000);
  /**读取温度或者湿度要花费250ms左右的时间*/
  /**湿度*/
  float h = dht.readHumidity();
  /**读取摄氏温度*/
  float t = dht.readTemperature();
  /**检查是否读取失败*/
  if (isnan(h) || isnan(t)) {
    Serial.println(F("Failed to read from DHT sensor!"));
    return;
  }
  /**计算摄氏温度指数*/
  float hic = dht.computeHeatIndex(t, h, false);

  int moisture = analogRead(soil);
  int light_intensity = analogRead(light);

  Serial.print(F("humidity: "));
  Serial.println(h);
  Serial.print(F("temperature: "));
  Serial.println(t);
  Serial.print(F("heatIndex: "));
  Serial.println(hic);
  double dis = distance(13);
  Serial.print(F("distance: "));
  Serial.println(dis);
  Serial.print(F("moisture: "));
  Serial.println(moisture);
  Serial.print(F("light_intensity: "));
  Serial.println(light_intensity);

  // 传输数据阶段，彩灯变为绿色
  colorRGB(0, 200, 0);
  delay(1000);
  preExe();

  // 请求格式: POST
  // http://DOMAIN_NAME:PORT/environmentData/create?airHumidity=1.2&lightIntensity=12&soilMoisture=3.4&temperature=4.2
  String cmd = "POST http://DOMAIN_NAME:PORT/environmentData/create?airHumidity=" +
               String(h) + "&lightIntensity=" + String(light_intensity) +
               "&soilMoisture=" + String(moisture) + "&temperature=" + String(t) +
               "\r\n";
  /**发送post请求*/
  exe(cmd.c_str());
  exe("+++", 10000);
  exe("AT+CIPCLOSE\r\n", 6000);

  // 静默阶段，彩灯无色
  colorRGB(0, 0, 0);
  // delay(10000);
  delay(100);
  /**读取电位器读数作为静默时间*/
  int delay_time =
      analogRead(potentiometer) *
      3500;  // 模拟口读取的值范围是0-1023，乘以3500，得到的值范围是0-3500s，约0-1小时
  Serial.println(delay_time);
//  delay(delay_time);
  delay(3600000/3);
}

// 该函数用于显示颜色
void colorRGB(int red, int green, int blue) {
  analogWrite(ledr, constrain(red, 0, 255));
  analogWrite(ledg, constrain(green, 0, 255));
  analogWrite(ledb, constrain(blue, 0, 255));
}

// void sendData(double* ht) {
//   //
//   return;
// }

double distance(int led) {
  unsigned int x1, x2;
  digitalWrite(trig, LOW);   //< 使发出超声波的引脚低电平
  delayMicroseconds(2);      //< 延时2us
  digitalWrite(trig, HIGH);  //< 使发出超声波的引脚高电平
  delayMicroseconds(
      10);  //< 延时10us，一般至少10us，防止发出的超声波被自己的回波干扰
  digitalWrite(trig, LOW);  //< 使发出超声波的引脚低电平
  /**为什么除以58: 以340m/s声速记，可换算出1cm耗时29us, d = pulseIn(echo, HIGH)
   * / 29.0 * (1/2);回波两倍距离 */
  float dis =
      pulseIn(echo, HIGH) / 58.0;  //< 读取回波的时间，除以58得到距离，单位为cm
  x1 = dis * 100;    //< 距离的整数部分
  dis = x1 / 100.0;  //< 保留两位小数
  delay(150);
  return dis;
}
