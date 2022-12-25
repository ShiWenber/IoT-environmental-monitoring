# 对目录下所有文件重命名
import os
import re
y: int
m: int
d: int
h: int 
m: int
s: int
for root, dirs, files in os.walk(r'./'):
    for file in files:
        # print(os.path.join(root, file))
        # ./2022-12-18T00_05_01.jpg
        target = re.match(r'./(\d{4}-\d{2}-\d{2}T\d{2}_\d{2}_\d{2}).jpg', os.path.join(root, file))
        if ( target != None):
            y = int(target.group(1)[0:4])
            m = int(target.group(1)[5:7])
            d = int(target.group(1)[8:10])
            h = int(target.group(1)[11:13])
            m = int(target.group(1)[14:16])
            s = int(target.group(1)[17:19])
            print(y, m, d, h, m, s)
            # 全部增加8小时
            if (h + 8 >= 24):
                d += 1
                h = h + 8 - 24
            os.rename(os.path.join(root, file), os.path.join(root, f'{y}-{m}-{d}T{h}_{m}_{s}.jpg'))
        # os.rename