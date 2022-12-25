'''
调用摄像头，每20分钟拍摄一张照片，保存在本地,并按照拍摄时间的UTC格式命名
'''

import cv2
import time

while True:
    # 调用摄像头
    cap = cv2.VideoCapture(0)
    # 读取摄像头
    ret, frame = cap.read()
    # 保存图片
    cv2.imwrite(time.strftime("%Y-%m-%dT%H_%M_%S", time.gmtime(time.time() + 8 * 60 * 60)) + ".jpg", frame)
    # 释放摄像头
    cap.release()
    # 休眠20分钟
    time.sleep(1200)