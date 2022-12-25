import os
import cv2
import numpy as np 
from time import time
import re

dirpath_head = r"./"


imgs: list = []
for root, dirs, files in os.walk(dirpath_head):
  for i in files:
    if re.search(r".jpg$", i):
      imgs.append(os.path.join(root, i))
imgs.sort()
print(imgs)

# 创建视频写入对象
video = cv2.VideoWriter("video.mp4", cv2.VideoWriter_fourcc('m', 'p', '4', 'v'), 10, (640 , 480))

for i in imgs:
  img = cv2.imread(i)
  video.write(img)
video.release()
